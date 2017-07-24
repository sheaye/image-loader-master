package com.sheaye.widget;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.SparseArrayCompat;
import android.view.View;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.drawable.ScalingUtils.ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by yexinyan on 2017/7/22.
 */

public class FrescoLoader implements ImageLoader {

    private SparseArrayCompat<ScaleType> mScaleTypes = new SparseArrayCompat<ScaleType>() {
        {
            append(ScaleIndex.FIT_XY, ScaleType.FIT_XY);
            append(ScaleIndex.FIT_START, ScaleType.FIT_START);
            append(ScaleIndex.FIT_CENTER, ScaleType.FIT_CENTER);
            append(ScaleIndex.FIT_END, ScaleType.FIT_END);
            append(ScaleIndex.CENTER, ScaleType.CENTER);
            append(ScaleIndex.CENTER_INSIDE, ScaleType.CENTER_INSIDE);
            append(ScaleIndex.CENTER_CROP, ScaleType.CENTER_CROP);
            append(ScaleIndex.FOCUS_CROP, ScaleType.FOCUS_CROP);
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
    public void setImageScaleType(int scaleIndex) {
        ScaleType scaleType = mScaleTypes.get(scaleIndex);
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
        if (mHierarchy != null) {
            mHierarchy.setRoundingParams(params);
        } else {
            mHierarchyBuilder.setRoundingParams(params);
        }
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
