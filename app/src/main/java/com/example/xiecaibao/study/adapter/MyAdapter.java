package com.example.xiecaibao.study.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xiecaibao.study.Data;
import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.activity.FeedsActivity;

import java.util.List;

/**
 * Created by xcb on 2018/3/25.
 */

public class MyAdapter extends BaseAdapter {
    private List<Data> mData;

    public MyAdapter(List<Data> data){
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Data getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FeedAdapter.FeedViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.feed_item_relative,
                    parent, false);
            viewHolder = new FeedAdapter.FeedViewHolder(convertView);
        } else {
            viewHolder = (FeedAdapter.FeedViewHolder) convertView.getTag();
        }
        final Data dataItem = getItem(position);
        viewHolder.nameTextView.setText(dataItem.creatorName);
        viewHolder.msgTextView.setText(dataItem.text);
        return convertView;
    }

    public static class FeedViewHolder {

        ImageView profileImageView;
        TextView nameTextView;
        TextView msgTextView;

        public FeedViewHolder(View itemView) {
            itemView.setTag(this);

            profileImageView = (ImageView) itemView.findViewById(R.id.profile_img);
            nameTextView = (TextView) itemView.findViewById(R.id.name_textview);
            msgTextView = (TextView) itemView.findViewById(R.id.msg_textview);
        }
    }
}
