package com.example.xiecaibao.study.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;

import com.example.xiecaibao.study.R;

public class TestLayoutActivity extends AppCompatActivity {
    private EditText editExtra1;
    private EditText editExtra2;
    private EditText editExtra3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Button btn_more = findViewById(R.id.more);
        btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注意这里我对ViewStub的实例进行了一个非空判断，这是因为ViewStub在XML中定义的id只在一开始有效，
                // 一旦ViewStub中指定的布局加载之后，这个id也就失败了，那么此时findViewById()得到的值也会是空。

                //另外需要提醒大家一点，ViewStub所加载的布局是不可以使用<merge>标签的，
                // 因此这有可能导致加载出来的布局存在着多余的嵌套结构，具体如何去取舍就要根据各自的实际情况来决定了，
                // 对于那些隐藏的布局文件结构相当复杂的情况，
                // 使用ViewStub还是一种相当不错的选择的，即使增加了一层无用的布局结构，仍然还是利大于弊。
                ViewStub viewStub = findViewById(R.id.view_stub);
                if (null != viewStub) {
                    viewStub.inflate();
                    editExtra1 = findViewById(R.id.edit_extra1);
                    editExtra2 = findViewById(R.id.edit_extra2);
                    editExtra3 = findViewById(R.id.edit_extra3);
                }
            }
        });
    }
}
