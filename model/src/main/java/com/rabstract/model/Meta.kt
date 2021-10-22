package com.rabstract.model

import com.google.gson.annotations.SerializedName

data class Meta(

    val status: Int,

    val msg: String,

    @SerializedName("response_id")
    val responseId: String

)
