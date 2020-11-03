package com.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class BaseCreditResponse<T>(
    @SerializedName("cast") val cast: List<T>?
)