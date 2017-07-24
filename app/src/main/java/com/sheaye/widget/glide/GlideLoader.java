package com.sheaye.widget.glide;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import com.sheaye.widget.ImageLoader;

/**
 * Created by yexinyan on 2017/7/24.
 */

public class GlideLoader implements ImageLoader {

    private SparseArrayCompat<ScaleType> mScaleTypes = new SparseArrayCompat<ScaleType>() {
        {
            append(ScaleIndex.FIT_XY, ScaleType.FIT_XY);
            append(ScaleIndex.FIT_START, ScaleType.FIT_START);
            append(ScaleIndex.FIT_CENTER, ScaleType.FIT_CENTER);
            append(ScaleIndex.FIT_END, ScaleType.FIT_END);
            append(ScaleIndex.CENTER, ScaleType.CENTER);
            append(ScaleIndex.CENTER_INSIDE, ScaleType.CENTER_INSIDE);
            append(ScaleIndex.CENTER_CROP, ScaleType.CENTER_CROP);
        }
    };

    private ImageView mImageView;
    private DrawableTypeRequest<Uri> mRequestBuilder;
    private int mPlaceholderResId;
    private Uri mUri;
    private boolean mAsCircle;
    private float mRadius;
    private boolean doResize;
    private int mResizedWidth;
    private int mResizedHeight;

    public GlideLoader(Context context) {
        mImageView = new ImageView(context);
        mRequestBuilder = Glide.with(context).fromUri();
    }

    @Override
    public View getInnerView() {
        return mImageView;
    }

    @Override
    public void setImageUri(Uri uri) {
//        mUri = uri;
        mRequestBuilder.load(uri);
    }

    @Override
    public void setImageScaleType(int scaleIndex) {
        mImageView.setScaleType(mScaleTypes.get(scaleIndex));
    }

    @Override
    public void setPlaceHolderImage(int resId) {
        mRequestBuilder.placeholder(resId);
//        mPlaceholderResId = resId;
    }

    @Override
    public void setRoundAsCircle(boolean asCircle) {
//        mAsCircle = asCircle;
        if (asCircle) {
            mRequestBuilder.transform(new CircleTransformation(mImageView.getContext()));
        }
    }

    @Override
    public void setCornerRadius(float radius) {
//        mRadius = radius;
        mRequestBuilder.transform(new RoundCornerTransformation(mImageView.getContext(), radius));
    }

    @Override
    public void resize(int width, int height) {
//        doResize = true;
//        mResizedWidth = width;
//        mResizedHeight = height;
        mRequestBuilder.override(width, height);
    }

    @Override
    public void commit() {
        mRequestBuilder.into(mImageView);
        /*DrawableRequestBuilder<Uri> requestBuilder =
                mRequestBuilder
                        .load(mUri)
                        .placeholder(mPlaceholderResId);
        if (mAsCircle) {
            requestBuilder.transform(new CircleTransformation(mImageView.getContext()));
        } else if (mRadius != 0) {
            requestBuilder.transform(new RoundCornerTransformation(mImageView.getContext(), mRadius));
        }
        if (doResize) {
            requestBuilder.override(mResizedWidth, mResizedHeight);
        }
*/
    }
}
