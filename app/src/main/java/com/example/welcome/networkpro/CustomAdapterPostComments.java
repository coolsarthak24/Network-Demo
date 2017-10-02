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

public class CustomAdapterPostComments extends ArrayAdapter<PostComments> {

    Context mContext;
    ArrayList<PostComments> mPostComments;

    public CustomAdapterPostComments(@NonNull Context context,ArrayList<PostComments> commentses) {
        super(context, 0);
        mContext = context;
        mPostComments = commentses;

    }

    @Override
    public int getCount() {
        return mPostComments.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.post_comment_row_layout,null);
            ViewHolder viewHolder = new ViewHolder();

            TextView postid = (TextView)convertView.findViewById(R.id.postid);
            TextView id = (TextView)convertView.findViewById(R.id.id);
            TextView name = (TextView)convertView.findViewById(R.id.name);
            TextView email = (TextView)convertView.findViewById(R.id.email);
            TextView body = (TextView)convertView.findViewById(R.id.body);

            viewHolder.postid = postid;
            viewHolder.id = id;
            viewHolder.name = name;
            viewHolder.email = email;
            viewHolder.body = body;

            convertView.setTag(viewHolder);

        }

        ViewHolder holder = (ViewHolder) convertView.getTag();

        PostComments postComment = mPostComments.get(position);
        holder.postid.setText(postComment.postid + "");
        holder.id.setText(postComment.id + "");
        holder.name.setText(postComment.name);
        holder.email.setText(postComment.email);
        holder.body.setText(postComment.body);


        return convertView;

    }

    static class ViewHolder{
        TextView postid;
        TextView id;
        TextView name;
        TextView email;
        TextView body;
    }

}
