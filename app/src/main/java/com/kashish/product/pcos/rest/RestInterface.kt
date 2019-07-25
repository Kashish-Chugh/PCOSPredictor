package com.kashish.product.pcos.rest

import com.kashish.product.pcos.entity.Input
import retrofit.Callback
import retrofit.http.*

internal interface RestInterface {

    @POST("/getResult")
    fun getResult(@Body input: Input, response: Callback<String>)
}