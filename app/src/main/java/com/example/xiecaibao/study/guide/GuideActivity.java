package com.example.xiecaibao.study.guide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.fragment.ContentFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GuideActivity extends AppCompatActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.ll_indicator)
    LinearLayout llIndicator;

    List<Fragment> fragmentList = new ArrayList<Fragment>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        for (int i = 0; i<3; i++) {
            ContentFragment fragment = new ContentFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        initIndicator();

        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragmentList.size();
            }

            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }
        };

        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i=0; i<fragmentList.size();i++) {
                    llIndicator.getChildAt(i).setBackgroundResource(position == i?R.drawable.dot_focus: R.drawable.dot_normal);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    /**
     * 绘制小圆点
     */
    private void initIndicator() {
        //dp转px
        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, getResources().getDisplayMetrics());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width, width);
        lp.rightMargin = 2 * width;
        for (int i =0; i<fragmentList.size();i++) {
            View view = new View(this);
            view.setId(i);
            view.setBackgroundResource(i == 0?R.drawable.dot_focus:R.drawable.dot_normal);
            view.setLayoutParams(lp);
            llIndicator.addView(view, i);
        }

    }
}
