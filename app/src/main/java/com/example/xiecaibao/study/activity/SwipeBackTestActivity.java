package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.Button;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.utils.LogX;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

public class SwipeBackTestActivity extends SwipeBackActivity {
    @BindView(R.id.btn1)
    Button btn1;
    private SwipeBackLayout mSwipeBackLayout;
    private String TAG = "xcb";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSwipeBackLayout = getSwipeBackLayout();

        int flag1 =   SwipeBackLayout.EDGE_LEFT ;   //左滑
        int flag2 =   SwipeBackLayout.EDGE_RIGHT ;  //右滑
        int flag3 =   SwipeBackLayout.EDGE_BOTTOM ; //下滑
        int flag4 =   SwipeBackLayout.EDGE_ALL ;    //全部

        //设置滑动模式
        mSwipeBackLayout.setEdgeTrackingEnabled(flag4);
        //设置是否可以滑动
        mSwipeBackLayout.setEnableGesture(true);

        //获取屏幕的宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int  phoneWidth  = dm.widthPixels ;
        LogX.d(TAG, "phoneWidth:" + phoneWidth);
        //设置侧滑的区域为屏幕宽度的1/3，如果不设置系统默认为50dip
        mSwipeBackLayout.setEdgeSize( phoneWidth / 3  );
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
//        scrollToFinishActivity();
    }
}
