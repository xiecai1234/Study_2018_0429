package com.example.xiecaibao.study.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.activity.MainActivity;
import com.example.xiecaibao.study.utils.LogX;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ContentFragment extends Fragment {
    @BindView(R.id.btn_begin)
    Button btnBegin;
    Unbinder unbinder;
    @BindView(R.id.rl_fragment)
    RelativeLayout rlFragment;
    private int[] bgRes = {R.drawable.bg1, R.drawable.bg2, R.drawable.bg3};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_guide_content, null);
        unbinder = ButterKnife.bind(this, view);

        int index = getArguments().getInt("index");
        LogX.d("xcb", "index:" + index);
        rlFragment.setBackgroundResource(bgRes[index]);
        LogX.d("xcb", "bgRes[index]:" + bgRes[index]);
        btnBegin.setVisibility(2 == index?View.VISIBLE:View.GONE);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_begin)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MainActivity.class));
        getActivity().finish();
    }
}
