package com.plcoding.socialnetworktwitch.feature_post.domain.use_case

import com.plcoding.socialnetworktwitch.R
import com.plcoding.socialnetworktwitch.core.util.Resource
import com.plcoding.socialnetworktwitch.core.util.SimpleResource
import com.plcoding.socialnetworktwitch.core.util.UiText
import com.plcoding.socialnetworktwitch.feature_post.domain.repository.PostRepository

class CreateCommentUseCase(
    private val repository: PostRepository
) {

    suspend operator fun invoke(postId: String, comment: String): SimpleResource {
        if(comment.isBlank()) {
            return Resource.Error(UiText.StringResource(R.string.error_field_empty))
        }
        if(postId.isBlank()) {
            return Resource.Error(UiText.unknownError())
        }
        return repository.createComment(postId, comment)
    }
}