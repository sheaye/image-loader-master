package com.sheaye.widget;

import android.net.Uri;
import android.view.View;


/**
 * Created by yexinyan on 2017/7/21.
 */

public interface ImageLoader {

    interface ScaleIndex {
        int FIT_XY = 0;
        int FIT_START = 1;
        int FIT_CENTER = 2;
        int FIT_END = 3;
        int CENTER = 4;
        int CENTER_INSIDE = 5;
        int CENTER_CROP = 6;
        int FOCUS_CROP = 7;
    }

    View getInnerView();

    void setImageUri(Uri uri);

    void setImageScaleType(int scaleIndex);

    void setPlaceHolderImage(int resId);

    void setRoundAsCircle(boolean asCircle);

    void setCornerRadius(float radius);

    void resize(int width, int height);

    void commit();
}
