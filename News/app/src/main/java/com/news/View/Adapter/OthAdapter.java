package com.news.View.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.R;
import com.news.model.TopData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by root on 16-10-15.
 */

public class OthAdapter extends BaseAdapter {
    private ArrayList<TopData> topDatas =new ArrayList<TopData>();

    private Context context;
    public OthAdapter(Context context) {
        super();
        this.context=context;
    }

    @Override
    public int getCount() {
        return topDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    View view;
    TextView title,date,type,from;
    ImageView imageView;
    private int url_index;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_flip_oth, null);
        }else {
            view=convertView;
        }
        title=(TextView) view.findViewById(R.id.title);
        date=(TextView)view.findViewById(R.id.date);
        type=(TextView)view.findViewById(R.id.type);
        from=(TextView)view.findViewById(R.id.from);
        imageView=(ImageView) view.findViewById(R.id.imageView);
        Picasso.with(context)
                .load(topDatas.get(position).getThumbnail_pic_s())
                .into(imageView);
        title.setText(topDatas.get(position).getTitle());
        date.setText(topDatas.get(position).getDate());
        type.setText(topDatas.get(position).getCategory());
        from.setText(topDatas.get(position).getAuthor_name());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri content_url = Uri.parse(topDatas.get(url_index-1).getUrl());
                intent.setData(content_url);
                context.startActivity(intent);
            }
        });
        url_index=position;
        return view;
    }

    public ArrayList<TopData> getTopDatas() {
        return topDatas;
    }

    public void setTopDatas(ArrayList<TopData> topDatas) {
        this.topDatas = topDatas;
    }
}
