package com.sheaye.widget.glide;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.util.Log;
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

    static {
        Log.e("ImageLoader","image library is glide");
    }

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

    public GlideLoader(Context context) {
        mImageView = new ImageView(context);
        mRequestBuilder = Glide.with(context).fromUri();
        mRequestBuilder.load(null);
    }

    @Override
    public View getInnerView() {
        return mImageView;
    }

    @Override
    public void setImageUri(Uri uri) {
        mRequestBuilder.load(uri);
    }

    @Override
    public void setImageScaleType(int scaleIndex) {
        mImageView.setScaleType(mScaleTypes.get(scaleIndex));
    }

    @Override
    public void setPlaceHolderImage(int resId) {
        mRequestBuilder.placeholder(resId);
    }

    @Override
    public void setRoundAsCircle(boolean asCircle) {
        if (asCircle) {
            mRequestBuilder.transform(new CircleTransformation(mImageView.getContext()));
        }
    }

    @Override
    public void setCornerRadius(float radius) {
        mRequestBuilder.transform(new RoundCornerTransformation(mImageView.getContext(), radius));
    }

    @Override
    public void resize(int width, int height) {
        mRequestBuilder.override(width, height);
    }

    @Override
    public void commit() {
        mRequestBuilder.into(mImageView);
    }
}
