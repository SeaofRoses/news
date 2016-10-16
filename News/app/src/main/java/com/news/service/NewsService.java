package com.news.service;

import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;

import java.util.ArrayList;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by root on 16-10-14.
 */

public interface NewsService {
    @GET("toutiao/index")
    Observable<HttpRequset<Result<ArrayList<TopData>>>> getNews(@Query("key") String key, @Query("type") String type);
}
