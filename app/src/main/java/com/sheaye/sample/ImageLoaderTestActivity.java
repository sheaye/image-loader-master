package com.sheaye.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.sheaye.sample.fresco.ImageWrapperView;

import sheaye.com.widget.imageloader.AbstractImageWrapperView;

public class ImageLoaderTestActivity extends AppCompatActivity {

    private ImageWrapperView mImage1View;
    private ImageWrapperView mImage3View;
    private ImageWrapperView mImage2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_image_loader_test);
        mImage1View = ((ImageWrapperView) findViewById(R.id.image1_view));
        mImage2View = ((ImageWrapperView) findViewById(R.id.image2_view));
        mImage3View = ((ImageWrapperView) findViewById(R.id.image3_view));

        mImage1View.setPlaceHolderImage(R.drawable.ic_place_holder).commit();

        mImage2View.setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1500781557&di=417e7dd82937d32797c0ba2e30031f31&src=http://www.pp3.cn/uploads/201502/2015021111.jpg");

        mImage3View.setRoundAsCircle(true)
                .setImageUrl("https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1500781557&di=417e7dd82937d32797c0ba2e30031f31&src=http://www.pp3.cn/uploads/201502/2015021111.jpg")
                .commit();

    }
}
