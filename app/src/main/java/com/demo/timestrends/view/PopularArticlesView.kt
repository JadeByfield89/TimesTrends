package com.demo.timestrends.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.demo.timestrends.R
import com.demo.timestrends.model.TimesArticle
import com.demo.timestrends.viewmodel.PopularArticlesViewModel
import java.net.URLDecoder
import java.net.URLEncoder


@Composable
fun PopularArticlesView(viewModel: PopularArticlesViewModel) {

    val navController = rememberNavController()
    val articles = viewModel.popularArticlesFlow.collectAsState(initial = emptyList())
    val isLoading = viewModel.isLoading.collectAsState(initial = true)
    val hasError = viewModel.errorFlow.collectAsState(initial = "")
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = hasError.value) {
        if (hasError.value.isNotEmpty()) {
            scaffoldState.snackbarHostState.showSnackbar(message = hasError.value)
        }
    }

    if (isLoading.value) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(modifier = Modifier.size(200.dp).testTag("ArticlesLoadingIndicator"))
        }
    } else {
        FullScreenTopBar(title = "TimesTrends", navController, showBackButton = false) {
            Box(modifier = Modifier.background(Color.White)) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    items(articles.value) { article ->
                        ArticleCell(article, navController)
                    }
                }
            }
        }
    }

    NavHost(navController, startDestination = "articles") {
        composable("articles") {

        }
        composable(
            "article/{url}/{title}",
            arguments = listOf(
                navArgument("url") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            ArticleWebView(
                navController = navController,
                url = URLDecoder.decode(backStackEntry.arguments?.getString("url") ?: "", "UTF-8"),
                title = URLDecoder.decode(backStackEntry.arguments?.getString("title") ?: "", "UTF-8")
            )
        }
    }
}

@Composable
fun ArticleCell(article: TimesArticle, navController: NavController) {
    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
    val mediaMetadata = article.media.firstOrNull()?.metadata?.lastOrNull()

    Column(modifier = Modifier
        .height(300.dp)
        .width(screenWidth / 2)
        .clickable {
            navController.navigate(
                "article/${
                    URLEncoder.encode(
                        article.url,
                        "UTF-8"
                    )
                }/${URLEncoder.encode(article.title, "UTF-8")}"
            )
        }) {
        Card(
            modifier = Modifier
                .height(200.dp)
                .width(screenWidth / 2)
                .padding(8.dp)
        ) {
            AsyncImage(
                model = if (!mediaMetadata?.url.isNullOrBlank()) mediaMetadata?.url else null,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.drawable.article_placeholder),
                fallback = painterResource(id = R.drawable.article_placeholder),
                error = painterResource(id = R.drawable.article_placeholder)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 8.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = article.byline,
            fontSize = 10.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}