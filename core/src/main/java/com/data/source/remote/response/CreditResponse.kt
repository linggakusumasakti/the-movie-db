package com.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("cast_id") val castId: Int?,
    @SerializedName("character") val character: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("profile_path") val profilePath: String?
)