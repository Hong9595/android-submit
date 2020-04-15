package com.example.arteum.data

import com.example.arteum.data.api.ArteumApi
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiManager {

    val gson =
        GsonBuilder()
            .setLenient()
            .create()

    private val okHttpClientBuilder =
        OkHttpClient.Builder()
            .addNetworkInterceptor(StethoInterceptor())
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })



    private val arteumAdapter by lazy {
        Retrofit.Builder()
            // TODO: 요청으로 인해 baseUrl을 삭제한 상태입니다
            .client(okHttpClientBuilder
                .addInterceptor { chain ->
                    val requestBuilder = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer "+ "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdWQiOiIxIiwianRpIjoiNDMxNTA3NDRjMzRiNDUyODI0OGM3OTQ0NDA0NWI4NWNkNzRiNmRkYzkxYmY1NzA4NjA5MDA4ODM4MDBkZTI4NjY1ZDAzY2NlNTQ1NDkyODAiLCJpYXQiOjE1NzkyMjg1NTIsIm5iZiI6MTU3OTIyODU1MiwiZXhwIjoxNjEwODUwOTUyLCJzdWIiOiIzIiwic2NvcGVzIjpbXX0.kaKSEiquuwQhY5cTTSf2haCTSCK8cC2RxL2IaxSzxgnRx94Fdl-TrKkglfarT8LaJN68o0GqtvzGhVi265DBn4kV26zz-1QqS9p9pU1c8z0t7tgnfM5Bm6hGKM7AF9azlLWY0KhtrUPz6zdWgX9OcbGvrAdntNLHKfTxF6g6Xm6WRLnZZbLLXZncuJzF2YOV-p9Uf85w75pJJz_aXTW91t6W5dctjobmlckKL4-gdIjCkO6yYElWasqtFpLCb8ovJgzM5xo3MwgFnCuRY61X-smwF1eHtpSYjryHr-nsE-rtMaOayJN7_JR4pyifXzTKbg_MjWTTsvv795fIFmcB0839r5uR7l4gGzBiVy5ewN1-sfXEJyEuhr4yy-k9pFwzUjkCdUvJe985Cs-qnjz5CcmovTihkztL5mmba_W8kkbBFXyJnRrnkoKH-CWTxw-bVeSSyheqoxE5I1dOkzEGh4h5sw9khf5n463IG91oPYXNHoYkR0YWx6SCYuvuxue6TG3fQy-2gKB14BvHZ--C9xguX_ZZVPNDdwJAP3cRbiWYwnYDRa-r_FYQqLwFKM7YQkQyf8pNr8u5dOIrE70QS920MLhRUaAaxqaM6al8YI-BPq63gQ7DJ4oggXIAZtpTpzMFwPfUuGez9QLcp_27fXIVZKNjcO56ij7Vrp2p1zM")
                    chain.proceed(requestBuilder.build())
                }
                .build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    val arteumApi by lazy {
        arteumAdapter.create(ArteumApi::class.java)
    }


}