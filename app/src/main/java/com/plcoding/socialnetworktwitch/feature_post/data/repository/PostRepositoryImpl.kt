package com.plcoding.socialnetworktwitch.feature_post.data.repository

import android.net.Uri
import androidx.core.net.toFile
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.google.gson.Gson
import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.domain.models.Post
import com.plcoding.socialnetworktwitch.core.util.Constants
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.core.data.remote.PostApi
import com.plcoding.socialnetworktwitch.core.domain.models.Comment
import com.plcoding.socialnetworktwitch.feature_post.data.remote.request.CreatePostRequest
import com.plcoding.socialnetworktwitch.feature_activity.data.paging.ActivitySource
import com.plcoding.socialnetworktwitch.feature_post.data.paging.PostSource
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository
import com.plcoding.socialnetworktwitch.feature_profile.data.remote.request.FollowUpdateRequest
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.IOException

class PostRepositoryImpl(
    private val api: PostApi,
    private val gson: Gson
) : PostRepository {

    override val posts: Flow<PagingData<Post>>
        get() = Pager(PagingConfig(pageSize = Constants.DEFAULT_PAGE_SIZE)) {
            PostSource(api, PostSource.Source.Follows)
        }.flow

    override suspend fun createPost(
        description: String,
        imageUri: Uri
    ): SimpleResource {
        val request = CreatePostRequest(description)
        val file = imageUri.toFile()
        return try {
            val response = api.createPost(
                postData = MultipartBody.Part
                    .createFormData(
                        "post_data",
                        gson.toJson(request)
                    ),
                postImage = MultipartBody.Part
                    .createFormData(
                        name = "post_image",
                        filename = file.name,
                        body = file.asRequestBody()
                    )
            )
            if(response.successful) {
                Resource.Success(Unit)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getPostDetails(postId: String): Resource<Post> {
        return try {
            val response = api.getPostDetails(postId = postId)
            if(response.successful) {
                Resource.Success(response.data)
            } else {
                response.message?.let { msg ->
                    Resource.Error(UiText.DynamicString(msg))
                } ?: Resource.Error(UiText.StringResource(R.string.error_unknown))
            }
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }

    override suspend fun getCommentsForPost(postId: String): Resource<List<Comment>> {
        return try {
            val comments = api.getCommentsForPost(postId = postId).map {
                it.toComment()
            }
            Resource.Success(comments)
        } catch(e: IOException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.error_couldnt_reach_server)
            )
        } catch(e: HttpException) {
            Resource.Error(
                uiText = UiText.StringResource(R.string.oops_something_went_wrong)
            )
        }
    }
}