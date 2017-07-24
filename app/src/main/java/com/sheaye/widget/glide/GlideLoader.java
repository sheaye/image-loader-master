package com.sheaye.widget.glide;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import sheaye.com.widget.imageloader.ImageLoader;

/**
 * Created by yexinyan on 2017/7/24.
 */

public class GlideLoader implements ImageLoader {

    private ImageView mImageView;
    private RequestManager mGlideManager;
    private int mPlaceholderResId;
    private Uri mUri;
    private int mScaleType;
    private boolean mAsCircle;
    private float mRadius;
    private boolean doResize;

    public GlideLoader(Context context) {
        mImageView = new ImageView(context);
        mGlideManager = Glide.with(context);
    }

    @Override
    public View getInnerView() {
        return mImageView;
    }

    @Override
    public void setImageUri(Uri uri) {
        mUri = uri;
    }

    @Override
    public void setImageScaleType(int scaleType) {
        mScaleType = scaleType;
    }

    @Override
    public void setPlaceHolderImage(int resId) {
        mPlaceholderResId = resId;
    }

    @Override
    public void setRoundAsCircle(boolean asCircle) {
        mAsCircle = asCircle;
    }

    @Override
    public void setCornerRadius(float radius) {
        mRadius = radius;
    }

    @Override
    public void resize(int width, int height) {
        doResize = true;
    }

    @Override
    public void commit() {
        DrawableRequestBuilder<Uri> requestBuilder = mGlideManager.load(mUri).placeholder(mPlaceholderResId);
        if (mAsCircle) {
            requestBuilder.transform(new CircleTransformation(mImageView.getContext()));
        } else if (mRadius != 0) {
//            requestBuilder.
        }

    }
}
