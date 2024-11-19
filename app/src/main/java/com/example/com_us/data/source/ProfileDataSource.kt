package com.example.com_us.data.source

import com.example.com_us.data.model.BaseResponse
import com.example.com_us.data.model.question.response.question.ResponseProfileDto

interface ProfileDataSource {
    suspend fun getProfileData() : BaseResponse<ResponseProfileDto>
}