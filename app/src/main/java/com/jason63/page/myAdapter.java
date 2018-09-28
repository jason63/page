package com.jason63.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

/**
 * Created by Jasonli on 2018/9/22;
 */
public class myAdapter extends ArrayAdapter {
    List<Item> mList ;
    public myAdapter(Context context, List<Item> list){
        super(context, R.layout.list_item, list ) ;
        mList = list ;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String str = mList.get(position).getTitle() ;
        String bodystr = mList.get(position).getBody();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false) ;
        TextView tv = view.findViewById(R.id.txt) ;
        TextView body = view.findViewById(R.id.body) ;
        tv.setText(str);
        body.setText(bodystr);
        return view;
    }
}
