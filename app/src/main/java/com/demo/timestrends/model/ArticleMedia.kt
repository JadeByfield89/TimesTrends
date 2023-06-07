package com.demo.timestrends.model

import com.google.gson.annotations.SerializedName

data class ArticleMedia(@SerializedName("media-metadata") val metadata: List<ArticleMediaMetadata>)
