package com.shahal.assignmentproject.networkmanager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shahal on 23-04-2018.
 */

public class ServiceGenerator {

    private static final String BASE_URL ="http://94.56.199.34";
    private static Retrofit.Builder builder = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    private static OkHttpClient.Builder httpClient =
            new OkHttpClient.Builder();


    public static <S> S createService(Class<S> serviceClass) {
        return builder
                .client(httpClient.build())
                .build()
                .create(serviceClass);
    }
}
