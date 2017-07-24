package com.sheaye.widget;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.text.TextUtils;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import sheaye.com.widget.imageloader.ImageLoader;

/**
 * Created by yexinyan on 2017/7/22.
 */

public class FrescoLoader implements ImageLoader {

    private SparseArrayCompat<ScalingUtils.ScaleType> mScaleTypes = new SparseArrayCompat<ScalingUtils.ScaleType>() {
        {
            append(ScaleType.FIT_XY, ScalingUtils.ScaleType.FIT_XY);
            append(ScaleType.FIT_START, ScalingUtils.ScaleType.FIT_START);
            append(ScaleType.FIT_CENTER, ScalingUtils.ScaleType.FIT_CENTER);
            append(ScaleType.FIT_END, ScalingUtils.ScaleType.FIT_END);
            append(ScaleType.CENTER, ScalingUtils.ScaleType.CENTER);
            append(ScaleType.CENTER_INSIDE, ScalingUtils.ScaleType.CENTER_INSIDE);
            append(ScaleType.CENTER_CROP, ScalingUtils.ScaleType.CENTER_CROP);
            append(ScaleType.FOCUS_CROP, ScalingUtils.ScaleType.FOCUS_CROP);
        }
    };

    private SimpleDraweeView mDraweeView;
    private GenericDraweeHierarchyBuilder mHierarchyBuilder;
    private GenericDraweeHierarchy mHierarchy;
    private int mResizedWidth;
    private int mResizedHeight;
    private Uri mUri = Uri.EMPTY;
    private boolean doResize;

    public FrescoLoader(Context context) {
        mDraweeView = new SimpleDraweeView(context);
        mHierarchyBuilder = new GenericDraweeHierarchyBuilder(context.getResources());
    }

    @Override
    public View getInnerView() {
        return mDraweeView;
    }

    @Override
    public void setImageScaleType(int index) {
        ScalingUtils.ScaleType scaleType = mScaleTypes.get(index);
        if (mHierarchy != null) {
            mHierarchy.setActualImageScaleType(scaleType);
        } else {
            mHierarchyBuilder.setActualImageScaleType(scaleType);
        }
    }

    @Override
    public void setPlaceHolderImage(int resId) {
        if (mHierarchy != null) {
            mHierarchy.setPlaceholderImage(resId);
        } else {
            mHierarchyBuilder.setPlaceholderImage(resId);
        }
    }

    @Override
    public void setCornerRadius(float radius) {
        RoundingParams params = new RoundingParams();
        params.setCornersRadius(radius);
    }

    @Override
    public void setRoundAsCircle(boolean asCircle) {
        RoundingParams params = new RoundingParams();
        params.setRoundAsCircle(asCircle);
        if (mHierarchy != null) {
            mHierarchy.setRoundingParams(params);
        } else {
            mHierarchyBuilder.setRoundingParams(params);
        }
    }

    @Override
    public void resize(int width, int height) {
        doResize = true;
        mResizedWidth = width;
        mResizedHeight = height;
    }

    @Override
    public void commit() {
        if (mHierarchy == null) {
            mHierarchy = mHierarchyBuilder.build();
            mDraweeView.setHierarchy(mHierarchy);
        }
        ImageRequestBuilder requestBuilder = ImageRequestBuilder.newBuilderWithSource(mUri);
        if (doResize) {
            requestBuilder.setResizeOptions(new ResizeOptions(mResizedWidth, mResizedHeight));
        }
        AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                .setOldController(mDraweeView.getController())
                .setImageRequest(requestBuilder.build())
                .build();
        mDraweeView.setController(controller);

    }

    @Override
    public void setImageUri(Uri uri) {
        if (uri == null) {
            return;
        }
        mUri = uri;
    }

}
