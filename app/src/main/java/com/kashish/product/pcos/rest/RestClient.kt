package com.kashish.product.pcos.rest

import com.google.gson.GsonBuilder
import retrofit.RestAdapter
import retrofit.client.OkClient
import retrofit.converter.GsonConverter

internal class RestClient {
    companion object {
//        private val API_URL = "http://192.168.1.8/TheChineseBuffet/api/"
        private val API_URL = "http://9580f33b.ngrok.io/PCOS/phone/api/"
    }

    var apiService: RestInterface

    init {
        /*val gson = GsonBuilder()
                .create()*/
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val restAdapter = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .setClient(OkClient())
                .setConverter(GsonConverter(gson))
                .build()
        apiService = restAdapter.create<RestInterface>(RestInterface::class.java)
    }
}


    /*public RestClient()
    {
        *//*Gson gson = new GsonBuilder()
                .create();*//*
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()
        val restAdapter = RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(API_URL)
                .setClient(OkClient())
                .setConverter(GsonConverter(gson))
                .build()
        apiService = restAdapter.create(RestInterface::class.java)
}
*/