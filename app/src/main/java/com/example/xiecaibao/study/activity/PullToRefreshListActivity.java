package com.example.xiecaibao.study.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xiecaibao.study.R;

import java.util.LinkedList;

public class PullToRefreshListActivity extends AppCompatActivity {
    private LinkedList<String> mListItems;
    /**
     * 上拉刷新的控件
     */

    private ArrayAdapter<String> mAdapter;

    private int mItemCount = 9;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltorefresh);

        //初始化数据
        initDatas();
        //设置适配器
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mListItems);
    }

    private void initDatas()
    {
        // 初始化数据和数据源
        mListItems = new LinkedList<String>();

        for (int i = 0; i < mItemCount; i++)
        {
            mListItems.add("" + i);
        }
    }

    private class GetDataTask extends AsyncTask<Void, Void, String>
    {

        @Override
        protected String doInBackground(Void... params)
        {
            try
            {
                Thread.sleep(2000);
            } catch (InterruptedException e)
            {
            }
            return "" + (mItemCount++);
        }

        @Override
        protected void onPostExecute(String result)
        {
            mListItems.add(result);
            mAdapter.notifyDataSetChanged();
            // Call onRefreshComplete when the list has been refreshed.
        }
    }
}
