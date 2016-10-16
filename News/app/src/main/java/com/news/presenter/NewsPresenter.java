package com.news.presenter;

import android.util.Log;

import com.news.View.NewsViews;
import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;
import com.news.service.imp.NewsImp;

import java.util.ArrayList;

import rx.Subscriber;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by root on 16-10-14.
 */

public class NewsPresenter {
    private Subscriber subscriber;

    private NewsViews newsViews;

    public NewsPresenter(NewsViews newsViews){
        this.newsViews=newsViews;
    }

    public void getNews(String type){
        subscriber=new Subscriber<HttpRequset<Result<ArrayList<TopData>>>>() {
            @Override
            public void onCompleted() {
                Log.i("test","");
            }

            @Override
            public void onError(Throwable e) {
                newsViews.errormessage(e);
                Log.i("test",e+"");
            }

            @Override
            public void onNext(HttpRequset<Result<ArrayList<TopData>>> result) {
                newsViews.showNews(result);
                Log.i("test",result.getResult()+"");

            }
        };
        NewsImp.getInstance().getNews(subscriber,type);
    }

}
