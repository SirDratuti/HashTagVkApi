package ru.sirdratuti.hashtag.network.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASEURL = "https://api.vk.com/method/"

private val retrofit = Retrofit.Builder()
    .baseUrl(BASEURL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

internal val service = retrofit.create(VKApi::class.java)
