package com.mehmetkaya.scorpion.data.model

import com.mehmetkaya.scorpion.data.model.Response.FetchError
import com.mehmetkaya.scorpion.data.model.Response.FetchResponse

data class ProcessResult<T>(
    val fetchResponse: FetchResponse<T>?,
    val fetchError: FetchError?,
    val waitTime: Double
)
