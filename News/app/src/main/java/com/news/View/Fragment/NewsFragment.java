package com.news.View.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.aphidmobile.flip.FlipViewController;
import com.news.View.Adapter.FlipAdapter;
import com.news.View.Adapter.OthAdapter;
import com.news.View.NewsViews;
import com.news.model.OthData;
import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;
import com.news.presenter.NewsPresenter;

import java.util.ArrayList;

/**
 * Created by root on 16-10-15.
 */

public class NewsFragment extends Fragment implements NewsViews {
    String param="";

    private ArrayList<TopData> topData;


    private FlipViewController flipView;

    public void setParam(String param) {
        this.param = param;
    }

    View view;
    TextView textView;
    FlipAdapter flipAdapter;
    OthAdapter othAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        flipView = new FlipViewController(inflater.getContext());
        flipAdapter=new FlipAdapter(getContext());
        othAdapter=new OthAdapter(getContext());
        flipView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(topData.get(position).getUrl());
                intent.setData(content_url);
                startActivity(intent);

            }
        });

        NewsPresenter newsPresenter=new NewsPresenter(this);
        newsPresenter.getNews(param);
        return flipView;
    }

    @Override
    public void showNews(HttpRequset<Result<ArrayList<TopData>>> Result) {
        if(param=="top"){
            topData =Result.getResult().getData();
            flipAdapter.setTopDatas(topData);
            flipView.setAdapter(flipAdapter);

            //flipAdapter.notifyDataSetChanged();
        }else {
            topData =Result.getResult().getData();
            othAdapter.setTopDatas(topData);
            flipView.setAdapter(othAdapter);

            //othAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void errormessage(Throwable e) {

    }

    @Override
    public void onResume() {
        super.onResume();
        flipView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        flipView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        flipView=null;
    }

    public String getParam() {
        return param;
    }
}
