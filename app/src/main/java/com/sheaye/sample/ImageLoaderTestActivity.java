package com.sheaye.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;

import sheaye.com.widget.imageloader.AbstractImageWrapperView;

public class ImageLoaderTestActivity extends AppCompatActivity {

    protected LinearLayout mLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image_loader_test);
        mLinearLayout = ((LinearLayout) findViewById(R.id.linear_layout));
        for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
            AbstractImageWrapperView view = (AbstractImageWrapperView) mLinearLayout.getChildAt(i);
            view.setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1500781557&di=417e7dd82937d32797c0ba2e30031f31&src=http://www.pp3.cn/uploads/201502/2015021111.jpg");
        }

    }
}
