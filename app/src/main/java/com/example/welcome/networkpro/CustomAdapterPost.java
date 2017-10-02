package com.example.welcome.networkpro;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by WELCOME on 01-10-2017.
 */

public class CustomAdapterPost extends ArrayAdapter<Post> {

    Context mContext;
    ArrayList<Post> mPosts;

    public CustomAdapterPost(@NonNull Context context, ArrayList<Post> posts) {

        super(context, 0);
        mContext = context;
        mPosts = posts;
    }

    @Override
    public int getCount() {
        return mPosts.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_row_layout,null);
            ViewHolder viewHolder = new ViewHolder();

            TextView userid = (TextView)convertView.findViewById(R.id.userId);
            TextView id = (TextView)convertView.findViewById(R.id.id);
            TextView title = (TextView)convertView.findViewById(R.id.title);
            TextView body = (TextView)convertView.findViewById(R.id.body);

            viewHolder.userid = userid;
            viewHolder.id = id;
            viewHolder.title = title;
            viewHolder.body = body;

            convertView.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        Post post = mPosts.get(position);
        holder.userid.setText(post.userid + "");
        holder.id.setText(post.id + "");
        holder.title.setText(post.title);
        holder.body.setText(post.body);

        return convertView;

    }

    static class ViewHolder{
        TextView userid;
        TextView id;
        TextView title;
        TextView body;
    }
}
