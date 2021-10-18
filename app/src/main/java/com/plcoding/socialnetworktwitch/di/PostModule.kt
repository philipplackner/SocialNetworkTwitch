package com.plcoding.socialnetworktwitch.di

import com.google.gson.Gson
import com.plcoding.socialnetworktwitch.core.data.remote.PostApi
import com.plcoding.socialnetworktwitch.feature_post.data.repository.PostRepositoryImpl
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository
import com.plcoding.socialnetworktwitch.feature_post.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    ): PostRepository {
        return PostRepositoryImpl(api, gson)
    }

    @Provides
    @Singleton
    fun providePostUseCases(repository: PostRepository): PostUseCases {
        return PostUseCases(
            getPostsForFollowsUseCase = GetPostsForFollowsUseCase(repository),
            createPostUseCase = CreatePostUseCase(repository),
            getPostDetails = GetPostDetailsUseCase(repository),
            getCommentsForPost = GetCommentsForPostUseCase(repository)
        )
    }
}