package com.sheaye.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sheaye.widget.ImageWrapperView;

public class ImageLoaderTestActivity extends AppCompatActivity {

    private ImageWrapperView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image_loader_test);
        mImageView = ((ImageWrapperView) findViewById(R.id.image_view));
        mImageView
                .setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1500781557&di=417e7dd82937d32797c0ba2e30031f31&src=http://www.pp3.cn/uploads/201502/2015021111.jpg")
                .commit();
    }
}
