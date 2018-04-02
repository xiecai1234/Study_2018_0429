package com.example.xiecaibao.study.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.GridView;

import com.example.xiecaibao.study.R;

public class AdvancedPhotoActivity extends AppCompatActivity {
    private GridView gridView;
    private AdvancedPhotoWallAdapter adapter;
    private int mImageThumbSize;
    private int mImageThumbSpacing;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_photo);

        initView();
    }

    private void initView() {
        mImageThumbSize = getResources().getDimensionPixelSize(
                R.dimen.image_thumbnail_size);
        mImageThumbSpacing = getResources().getDimensionPixelSize(
                R.dimen.image_thumbnail_spacing);
        gridView = findViewById(R.id.photo_wall);
        adapter = new AdvancedPhotoWallAdapter(this, 0, Images.imageThumbUrls, gridView);
        gridView.setAdapter(adapter);

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                final int numColumns = (int) Math.floor(gridView
                        .getWidth()
                        / (mImageThumbSize + mImageThumbSpacing));
                if (numColumns > 0) {
                    int columnWidth = (gridView.getWidth() / numColumns)
                            - mImageThumbSpacing;
                    adapter.setItemHeight(columnWidth);
                    gridView.getViewTreeObserver()
                            .removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        adapter.fluchCache();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cancelAllTasks();
    }
}
