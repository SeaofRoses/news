package com.news;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.news.View.Adapter.NewsAdapter;
import com.news.View.Fragment.NewsFragment;
import com.news.View.NewsViews;
import com.news.model.TopData;
import com.news.model.HttpRequset;
import com.news.model.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NewsViews{

    @BindView(R.id.tab_NewsFragment)TabLayout tabLayout;
    @BindView(R.id.news_pager)ViewPager mViewPager;

    private List<Fragment> mFragments;
    private List<String> mTitle;
    private String[] param=new String[]{"top","shehui","guonei","guoji","yule","tiyu","junshi","keji","caijing","shishang"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_layout);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    public void showNews(HttpRequset<Result<ArrayList<TopData>>> Result) {

    }

    @Override
    public void errormessage(Throwable e) {

    }

    public void setTitle(){
        mTitle=new ArrayList<String>();
        mTitle.add("头条");
        mTitle.add("社会");
        mTitle.add("国内");
        mTitle.add("国际");
        mTitle.add("娱乐");
        mTitle.add("体育");
        mTitle.add("军事");
        mTitle.add("科技");
        mTitle.add("财经");
        mTitle.add("时尚");
        mFragments=new ArrayList<Fragment>();
    }

    public void initView(){
        setTitle();
        for(int i=0;i<10;i++){
            NewsFragment newsFragment=new NewsFragment();
            newsFragment.setParam(param[i]);
            mFragments.add(newsFragment);
        }
        NewsAdapter newsAdapter=new NewsAdapter(getSupportFragmentManager(),mFragments,mTitle);
        mViewPager.setAdapter(newsAdapter);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabsFromPagerAdapter(newsAdapter);
    }

}
