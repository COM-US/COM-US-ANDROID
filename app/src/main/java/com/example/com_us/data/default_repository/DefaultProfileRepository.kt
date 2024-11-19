package com.example.com_us.data.default_repository

import com.example.com_us.data.model.question.response.question.ResponseProfileDto
import com.example.com_us.data.repository.ProfileRepository
import com.example.com_us.data.default_source.DefaultProfileDataSource
import javax.inject.Inject

class DefaultProfileRepository @Inject constructor(
    private val profileRemoteDataSource: DefaultProfileDataSource
)  : ProfileRepository {
   override suspend fun getProfileData(): Result<ResponseProfileDto> {
        return runCatching { profileRemoteDataSource.getProfileData().data!! }
    }
}