package com.sheaye.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.sheaye.widget.fresco.FrescoLoader;
import com.sheaye.widget.glide.GlideLoader;

/**
 * Created by yexinyan on 2017/7/21.
 */

public class ImageWrapperView extends FrameLayout {

    private static final String TAG = "ImageWrapperView";
    public static final int FRESCO = 0;
    public static final int GLIDE = 1;
    public static final int PICASSO = 2;
    private ImageLoader mImageLoader;

    public ImageWrapperView(@NonNull Context context) {
        this(context, null, 0);
    }

    public ImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.imageLoader);
    }

    public ImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ImageWrapperView, defStyleAttr, 0);

        int loaderType = typedArray.getInt(R.styleable.ImageWrapperView_loaderType, 0);
        int actualScaleType = typedArray.getInt(R.styleable.ImageWrapperView_scaleType, -1);
        int placeholderImageId = typedArray.getResourceId(R.styleable.ImageWrapperView_placeholderImage, NO_ID);
        boolean asCircle = typedArray.getBoolean(R.styleable.ImageWrapperView_roundAsCircle, false);
        int cornerRadius = typedArray.getDimensionPixelSize(R.styleable.ImageWrapperView_cornerRadius, 0);
        typedArray.recycle();
        switch (loaderType) {
            case FRESCO:
                mImageLoader = new FrescoLoader(context);
                break;
            case GLIDE:
                mImageLoader = new GlideLoader(context);
                break;
            case PICASSO:
                break;
        }
        addView(mImageLoader.getInnerView());
        if (actualScaleType != -1) {
            setImageScaleType(actualScaleType);
        }
        if (placeholderImageId != NO_ID) {
            setPlaceHolderImage(placeholderImageId);
        }
        if (asCircle) {
            setRoundAsCircle(asCircle);
        } else if (cornerRadius != 0) {
            setCornerRadius(cornerRadius);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        if (width != 0 && height != 0) {
            if (getChildCount() > 0) {
                int childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
                int childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                getChildAt(0).measure(childWidthSpec, childHeightSpec);
            }
        }
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setImageUri(Uri uri) {
        if (mImageLoader != null) {
            mImageLoader.setImageUri(uri);
        }
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setImageUrl(String url) {
        Uri uri = TextUtils.isEmpty(url) ? Uri.EMPTY : Uri.parse(url);
        setImageUri(uri);
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setImageScaleType(int scaleType) {
        if (mImageLoader != null) {
            mImageLoader.setImageScaleType(scaleType);
        }
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setPlaceHolderImage(int resId) {
        if (mImageLoader != null) {
            mImageLoader.setPlaceHolderImage(resId);
        }
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setCornerRadius(float radius) {
        if (mImageLoader != null) {
            mImageLoader.setCornerRadius(radius);
        }
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView setRoundAsCircle(boolean round) {
        if (mImageLoader != null) {
            mImageLoader.setRoundAsCircle(round);
        }
        return this;
    }

    /**
     * ensure it works, please call commit() in the end.
     */
    public ImageWrapperView resize(int resizedWidth, int resizedHeight) {
        if (mImageLoader != null) {
            mImageLoader.resize(resizedWidth, resizedHeight);
        }
        return this;
    }

    /**
     * ensure some set work, please call commit() in the end.
     */
    public void commit() {
        if (mImageLoader != null) {
            mImageLoader.commit();
        }
    }

}
