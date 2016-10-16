package com.news.View.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by root on 16-10-15.
 */

public class NewsAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragments;

    private List<String> list_Title;

    public NewsAdapter(FragmentManager fm,List<Fragment> mFragments,List<String> list_Title){
        super(fm);
        this.mFragments=mFragments;
        this.list_Title=list_Title;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list_Title.get(position);
    }
}
