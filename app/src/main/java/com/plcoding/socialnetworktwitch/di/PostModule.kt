package com.plcoding.socialnetworktwitch.di

import android.content.Context
import com.google.gson.Gson
import com.plcoding.socialnetworktwitch.feature_post.data.data_source.remote.PostApi
import com.plcoding.socialnetworktwitch.feature_post.data.repository.PostRepositoryImpl
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.CreatePostUseCase
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.GetPostsForFollowsUseCase
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.PostUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PostModule {

    @Provides
    @Singleton
    fun providePostApi(client: OkHttpClient): PostApi {
        return  Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PostApi.BASE_URL)
            .client(client)
            .build()
            .create(PostApi::class.java)
    }

    @Provides
    @Singleton
    fun providePostRepository(
        api: PostApi,
        gson: Gson,
        @ApplicationContext appContext: Context
    ): PostRepository {
        return PostRepositoryImpl(api, gson, appContext)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollowsUseCase = GetPostsForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository)
        )
    }
}