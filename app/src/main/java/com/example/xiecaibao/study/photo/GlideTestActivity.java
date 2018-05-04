package com.example.xiecaibao.study.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.xiecaibao.study.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GlideTestActivity extends AppCompatActivity {
    @BindView(R.id.iv_glide)
    ImageView ivGlide;
    @BindView(R.id.iv_picasso)
    ImageView ivPicasso;
    String url = "http://img.my.csdn.net/uploads/201407/26/1406383299_1976.jpg";
    String url2 = "http://img.my.csdn.net/uploads/201407/26/1406383291_6518.jpg";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide_picasso);
        ButterKnife.bind(this);

        Glide.with(this).load(url)
                .override(400, 400)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.back)
                .animate(R.anim.item_alpha_in)
                .into(ivGlide);

        Picasso.with(this).load(url2).resize(600, 600).centerCrop().into(ivPicasso);
    }
}
