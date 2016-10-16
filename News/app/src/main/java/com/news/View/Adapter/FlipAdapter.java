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

import com.aphidmobile.utils.AphidLog;
import com.aphidmobile.utils.UI;
import com.news.R;
import com.news.model.TopData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by root on 16-10-15.
 */

public class FlipAdapter extends BaseAdapter{
    private ArrayList<TopData> topDatas =new ArrayList<TopData>();

    private Context context;

    public FlipAdapter(Context context) {
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
    TextView title,date,type,author_name;
    ImageView imageView;
    private int url_index;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        url_index=position;
        if(convertView==null){
            final Context context = parent.getContext();
            view = LayoutInflater.from(context).inflate(R.layout.news_list_flip, null);
        }else {
            view=convertView;
        }
        UI
                .<TextView>findViewById(view,R.id.title)
            .setText(AphidLog.format(topDatas.get(position).getTitle()));
        UI
                .<TextView>findViewById(view,R.id.date)
            .setText(AphidLog.format(topDatas.get(position).getDate()));
        UI
                .<TextView>findViewById(view,R.id.type)
            .setText(AphidLog.format(topDatas.get(position).getRealtype()));
        UI
                .<TextView>findViewById(view,R.id.author_name)
        .setText(topDatas.get(position).getAuthor_name());
        imageView=(ImageView) view.findViewById(R.id.imageView);
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
        Picasso.with(context)
                .load(topDatas.get(position).getThumbnail_pic_s())
               .into(imageView);
        return view;
    }

    public ArrayList<TopData> getTopDatas() {
        return topDatas;
    }

    public void setTopDatas(ArrayList<TopData> topDatas) {
        this.topDatas = topDatas;
    }
}
