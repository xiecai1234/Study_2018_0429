package com.example.xiecaibao.study.activity;

import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiecaibao.study.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ToolbarTest extends AppCompatActivity {
    Toast mToast;
    PopupWindow mPopupWindow;

    @BindView(R.id.btn_diy)
    Button btnDiy;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.toolbar_test);
        ButterKnife.bind(this);

        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        mToast.setGravity(Gravity.CENTER, 0, 0);

        // App Logo
//        mToolbar.setLogo(R.drawable.app_icon);
        // 主标题,默认为app_label的名字
        mToolbar.setTitle("Title");
        mToolbar.setTitleTextColor(Color.YELLOW);
        // 副标题
        mToolbar.setSubtitle("Sub title");
        mToolbar.setSubtitleTextColor(Color.parseColor("#80ff0000"));
        //侧边栏的按钮
        mToolbar.setNavigationIcon(R.drawable.back);
        //取代原本的actionbar
        setSupportActionBar(mToolbar);
        //设置NavigationIcon的点击事件,需要放在setSupportActionBar之后设置才会生效,
        //因为setSupportActionBar里面也会setNavigationOnClickListener
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mToast.setText("click NavigationIcon");
                mToast.show();
            }
        });
        //设置toolBar上的MenuItem点击事件
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_edit:
                        mToast.setText("click edit");
                        break;
                    case R.id.action_share:
                        mToast.setText("click share");
                        break;
                    case R.id.action_overflow:
                        //弹出自定义overflow
                        popUpMyOverflow();
                        return true;
                }
                mToast.show();
                return true;
            }
        });
    }

    @OnClick({R.id.btn_diy, R.id.tv_title, R.id.toolbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_diy:
                mToast.setText("点击自定义按钮");
                mToast.show();
                break;
            case R.id.tv_title:
                mToast.setText("点击自定义标题");
                mToast.show();
                break;
        }
    }

    /**
     * 弹出自定义的popWindow
     */
    public void popUpMyOverflow() {
        //获取状态栏高度
        Rect frame = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        //状态栏高度+toolbar的高度
        int yOffset = frame.top + mToolbar.getHeight();
        if (null == mPopupWindow) {
            //初始化PopupWindow的布局
            View popView = getLayoutInflater().inflate(R.layout.action_overflow_popwindow, null);
            //popView即popupWindow的布局，ture设置focusAble.
            mPopupWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
            //必须设置BackgroundDrawable后setOutsideTouchable(true)才会有效
            mPopupWindow.setBackgroundDrawable(new ColorDrawable());
            //点击外部关闭。
            mPopupWindow.setOutsideTouchable(true);
            //设置一个动画。
            mPopupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
            //设置Gravity，让它显示在右上角。
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
            //设置item的点击监听
            popView.findViewById(R.id.ll_item1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mToast.setText("哈哈");
                    //点击PopWindow的item后,关闭此PopWindow
                    if (null != mPopupWindow && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    mToast.show();
                }
            });
//            popView.findViewById(R.id.ll_item2).setOnClickListener(this);
//            popView.findViewById(R.id.ll_item3).setOnClickListener(this);
        } else {
            mPopupWindow.showAtLocation(mToolbar, Gravity.RIGHT | Gravity.TOP, 0, yOffset);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }
}
