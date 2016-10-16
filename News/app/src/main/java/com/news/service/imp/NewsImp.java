package com.news.service.imp;

import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;
import com.news.service.NewsService;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by root on 16-10-14.
 */

public class NewsImp {
    public static final String BASE_URL = "http://v.juhe.cn/";

    public static final String KEY="ea8414c0ed177a0587ff4420355494bc";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;

    private NewsService newsService;

    public NewsImp(){
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        newsService=retrofit.create(NewsService.class);
    }

    public void getNews(Subscriber<HttpRequset<Result<ArrayList<TopData>>>> subscriber, String type){
        newsService.getNews(KEY,type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private static class SingletonHolder{
        private static final NewsImp INSTANCE=new NewsImp();
    }
    public static NewsImp getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
