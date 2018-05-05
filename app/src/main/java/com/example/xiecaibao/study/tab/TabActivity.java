package com.example.xiecaibao.study.tab;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.fragment.ArticleListFragment;
import com.example.xiecaibao.study.fragment.Fragment2;
import com.example.xiecaibao.study.fragment.Fragment3;
import com.example.xiecaibao.study.fragment.Fragment4;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends AppCompatActivity {
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.iv_weixin)
    ImageButton ivWeixin;
    @BindView(R.id.ll_weixin)
    LinearLayout llWeixin;
    @BindView(R.id.iv_friend)
    ImageButton ivFriend;
    @BindView(R.id.ll_friend)
    LinearLayout llFriend;
    @BindView(R.id.iv_address)
    ImageButton ivAddress;
    @BindView(R.id.ll_address)
    LinearLayout llAddress;
    @BindView(R.id.iv_settings)
    ImageButton ivSettings;
    @BindView(R.id.ll_settings)
    LinearLayout llSettings;

    private List<Fragment> list;
    private FragmentPagerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        initView();
        setSelected(0);
    }


    private void initView() {
        list = new ArrayList<Fragment>();
        list.add(ArticleListFragment.newInstance("test"));
        list.add(new Fragment2());
        list.add(new Fragment3());
        list.add(new Fragment4());

        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        };


        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onPageSelected(int position) {
                resetImage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.ll_weixin, R.id.ll_friend, R.id.ll_address, R.id.ll_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_weixin:
                setSelected(0);
                resetImage(0);
                break;
            case R.id.ll_friend:
                setSelected(1);
                resetImage(1);
                break;
            case R.id.ll_address:
                setSelected(2);
                resetImage(2);
                break;
            case R.id.ll_settings:
                setSelected(3);
                resetImage(3);
                break;
        }
    }

    private void setSelected(int i) {
        viewPager.setCurrentItem(i);
    }

    private void resetImage(int i) {
        ivWeixin.setImageResource(R.drawable.fanhui);
        ivFriend.setImageResource(R.drawable.ic_heart_4);
        ivAddress.setImageResource(R.drawable.guanbi);
        ivSettings.setImageResource(R.drawable.share);

        if (0 == i) {
            ivWeixin.setImageResource(R.drawable.edit);
        } else if (1 == i) {
            ivFriend.setImageResource(R.drawable.edit);
        }else if (2 == i) {
            ivAddress.setImageResource(R.drawable.edit);
        }else if (3 == i) {
            ivSettings.setImageResource(R.drawable.edit);
        }
    }
}
