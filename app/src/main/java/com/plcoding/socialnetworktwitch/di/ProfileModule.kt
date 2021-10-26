package com.plcoding.socialnetworktwitch.di

import com.google.gson.Gson
import com.plcoding.socialnetworktwitch.core.domain.use_case.ToggleFollowStateForUserUseCase
import com.plcoding.socialnetworktwitch.feature_post.data.remote.PostApi
import com.plcoding.socialnetworktwitch.feature_profile.data.remote.ProfileApi
import com.plcoding.socialnetworktwitch.core.data.repository.ProfileRepositoryImpl
import com.plcoding.socialnetworktwitch.core.domain.repository.ProfileRepository
import com.plcoding.socialnetworktwitch.core.domain.use_case.GetOwnUserIdUseCase
import com.plcoding.socialnetworktwitch.feature_profile.domain.use_case.*
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
object ProfileModule {

    @Provides
    @Singleton
    fun provideProfileApi(client: OkHttpClient): ProfileApi {
        return Retrofit.Builder()
            .baseUrl(ProfileApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ProfileApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProfileRepository(profileApi: ProfileApi, postApi: PostApi, gson: Gson): ProfileRepository {
        return ProfileRepositoryImpl(profileApi, postApi, gson)
    }

    @Provides
    @Singleton
    fun provideProfileUseCases(repository: ProfileRepository): ProfileUseCases {
        return ProfileUseCases(
            getProfile = GetProfileUseCase(repository),
            getSkills = GetSkillsUseCase(repository),
            updateProfile = UpdateProfileUseCase(repository),
            setSkillSelected = SetSkillSelectedUseCase(),
            getPostsForProfile = GetPostsForProfileUseCase(repository),
            searchUser = SearchUserUseCase(repository),
            toggleFollowStateForUser = ToggleFollowStateForUserUseCase(repository)
        )
    }

    @Provides
    @Singleton
    fun provideToggleFollowForUserUseCase(repository: ProfileRepository): ToggleFollowStateForUserUseCase {
        return ToggleFollowStateForUserUseCase(repository)
    }
}