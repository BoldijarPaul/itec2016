package com.itec.order.data.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by bjz on 5/13/2016.
 */
public class RetrofitUtils {
    private static Retrofit retrofit;

    public static Retrofit getRetrofit() {
        if(retrofit!= null){ return retrofit; }
        retrofit= new Retrofit.Builder()
                .baseUrl("http://mobile.itec.ligaac.ro/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
