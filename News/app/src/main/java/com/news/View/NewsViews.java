package com.news.View;

import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;

import java.util.ArrayList;

/**
 * Created by root on 16-10-15.
 */

public interface NewsViews {
    public void showNews(HttpRequset<Result<ArrayList<TopData>>> Result);
    public void errormessage(Throwable e);
}
