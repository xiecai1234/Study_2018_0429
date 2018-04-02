package com.example.xiecaibao.study.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.xiecaibao.study.R;
import com.example.xiecaibao.study.disklrucache.test.DiskLruCacheTest;
import com.example.xiecaibao.study.utils.BitmapUtil;

import java.io.IOException;
import java.io.InputStream;

public class TestImageActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_imageview);
        ImageView imageViewOri = findViewById(R.id.imageview_ori);
        imageViewOri.setImageBitmap(BitmapUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.icon_400, 100, 100));
//        DiskLruCacheTest instance = new DiskLruCacheTest();
//        instance.testWrite(this);
//        imageView.setImageBitmap(instance.readCachedBitmap(this));

        ImageView imageViewRegion = findViewById(R.id.imageview_region);
        try
        {
            InputStream inputStream = getAssets().open("tangyan.jpg");

            //获得图片的宽、高
            BitmapFactory.Options tmpOptions = new BitmapFactory.Options();
            tmpOptions.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(inputStream, null, tmpOptions);
            int width = tmpOptions.outWidth;
            int height = tmpOptions.outHeight;

            //设置显示图片的中心区域
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            Bitmap bitmap = bitmapRegionDecoder.decodeRegion(new Rect(width / 2, height / 2, width / 2 + 100, height / 2 + 100), options);
            imageViewRegion.setImageBitmap(bitmap);


        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
