package com.sheaye.sample.fresco;

import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.view.SimpleDraweeView;

import sheaye.com.widget.imageloader.ImageLoader;

/**
 * Created by yexinyan on 2017/7/22.
 */

public class FrescoLoader implements ImageLoader {

    private SimpleDraweeView mDraweeView;

    public FrescoLoader(Context context) {
        mDraweeView = new SimpleDraweeView(context);
    }

    @Override
    public View getInnerView() {
        return mDraweeView;
    }

    @Override
    public void setImageScaleType(int scaleType) {
        switch (scaleType){
            case ScaleType.FIT_XY:
                break;
            case ScaleType.FIT_START:
                break;
            case ScaleType.FIT_CENTER:
                break;
            case ScaleType.FIT_END:
                break;
            case ScaleType.CENTER:
                break;
            case ScaleType.CENTER_INSIDE:
                break;
            case ScaleType.CENTER_CROP:
                break;
            case ScaleType.FOCUS_CROP:
                break;
            default:
                break;
        }
    }

    @Override
    public void setImageUri(Uri uri) {
        uri = uri == null ? Uri.parse("") : uri;
        if (mDraweeView != null) {
            mDraweeView.setImageURI(uri);
        }
    }

    @Override
    public void setImageUrl(String url) {
        if (mDraweeView != null) {
            mDraweeView.setImageURI(Uri.parse(url));
        }
    }

}
