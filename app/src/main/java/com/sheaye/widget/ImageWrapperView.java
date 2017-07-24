package com.sheaye.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.sheaye.widget.glide.GlideLoader;

/**
 * Created by yexinyan on 2017/7/23.
 */

public class ImageWrapperView extends AbstractImageWrapperView {
    public ImageWrapperView(@NonNull Context context) {
        super(context);
    }

    public ImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public ImageLoader getImageLoader() {
        return new GlideLoader(getContext());
    }
}
