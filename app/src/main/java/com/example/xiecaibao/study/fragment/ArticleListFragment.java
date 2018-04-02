package com.example.xiecaibao.study.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.xiecaibao.study.Data;
import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.activity.FeedsActivity;
import com.example.xiecaibao.study.adapter.MyAdapter;
import com.example.xiecaibao.study.utils.LogX;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2018/3/25.
 */

public class ArticleListFragment extends Fragment {
    private static final String TAG = "ArticleListFragment";
    private static String ARG_PARAM = "param_key";
    private String mParam;
    private Activity mActivity;

    public static ArticleListFragment newInstance(String str) {
        LogX.d(TAG, "newInstance");
        ArticleListFragment fragment = new ArticleListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_PARAM, str);
        fragment.setArguments(bundle);   //设置参数
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        LogX.d(TAG, "onAttach,param is: ");
        super.onAttach(context);
        mActivity = (Activity) context;
        mParam = getArguments().getString(ARG_PARAM);  //获取参数
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        LogX.i("TAG", "onCreate");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        LogX.i(TAG, "onCreateView");
        View rootView = inflater.inflate(R.layout.feed_item_activity, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        LogX.i(TAG, "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        LogX.i(TAG, "onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        LogX.i(TAG, "onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        LogX.i(TAG, "onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        LogX.i(TAG, "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        LogX.i(TAG, "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        LogX.i(TAG, "onDestroy");
        super.onDestroy();
    }


    @Override
    public void onDetach() {
        LogX.i(TAG, "onDetach");
        super.onDetach();
    }

    private void initView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.feeds_lv);
        listView.setAdapter(new MyAdapter(buildData()));
    }

    protected List<Data> buildData() {
        List<Data> data = new ArrayList<Data>();
        for (int i = 0; i < 20; i++) {
            data.add(new Data("UserName - " + i, "这是Feed的文本消息啊 --> " + i));
        }
        return data;
    }
}
