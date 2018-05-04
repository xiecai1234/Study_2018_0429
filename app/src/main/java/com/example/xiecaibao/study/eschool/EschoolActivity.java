package com.example.xiecaibao.study.eschool;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EschoolActivity extends AppCompatActivity {
    @BindView(R.id.iv_fanhui)
    ImageView ivFanhui;
    @BindView(R.id.rl_title_bar)
    RelativeLayout rlTitleBar;
    @BindView(R.id.tv_info)
    TextView tvInfo;
    @BindView(R.id.tv_school)
    TextView tvSchool;
    @BindView(R.id.tv_school_info)
    TextView tvSchoolInfo;
    @BindView(R.id.rl_school)
    RelativeLayout rlSchool;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_type_info)
    TextView tvTypeInfo;
    @BindView(R.id.rl_type)
    RelativeLayout rlType;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_name_info)
    TextView tvNameInfo;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.tv_phone_info)
    TextView tvPhoneInfo;
    @BindView(R.id.rl_phone)
    RelativeLayout rlPhone;
    @BindView(R.id.tv_ID)
    TextView tvID;
    @BindView(R.id.tv_ID_info)
    TextView tvIDInfo;
    @BindView(R.id.rl_ID)
    RelativeLayout rlID;
    @BindView(R.id.btn_next)
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eschool_auth);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        startActivity(new Intent(EschoolActivity.this, LoginActivity.class));
    }
}
