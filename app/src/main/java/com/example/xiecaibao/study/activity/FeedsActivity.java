
package com.example.xiecaibao.study.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.adapter.FeedAdapter;

import java.util.ArrayList;
import java.util.List;


public class FeedsActivity extends Activity {

    protected ListView feedsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feed_item_activity);

        feedsListView = (ListView) findViewById(R.id.feeds_lv);
        setupFeedListView();
    }
    
    protected void setupFeedListView() {
        feedsListView.setAdapter(new FeedAdapter(mockFeeds()));
    }

    protected List<Feed> mockFeeds() {
        List<Feed> feeds = new ArrayList<Feed>();
        for (int i = 0; i < 20; i++) {
            feeds.add(new Feed("UserName - " + i, "这是Feed的文本消息啊 --> " + i));
        }
        return feeds;
    }

    public static class Feed {
        public Feed(String aName, String aText) {
            creatorName = aName;
            text = aText;
        }

        public String creatorName;
        public String text;
    }
}
