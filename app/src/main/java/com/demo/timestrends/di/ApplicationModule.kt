package com.demo.timestrends.di;

import com.demo.timestrends.api.TimesAPI
import com.demo.timestrends.util.BASE_URL
import dagger.Module;
import dagger.Provides
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return  Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    }

    @Singleton
    @Provides
    fun provideTimesAPI(retrofit: Retrofit): TimesAPI {
        return retrofit.create(TimesAPI::class.java)
    }

}
