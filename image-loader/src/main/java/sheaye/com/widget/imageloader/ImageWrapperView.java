package sheaye.com.widget.imageloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.facebook.drawee.view.SimpleDraweeView;

import sheaye.com.widget.R;
import sheaye.com.widget.imageloader.fresco.FrescoLoader;

/**
 * Created by yexinyan on 2017/7/21.
 */

public class ImageWrapperView extends FrameLayout {

    private static final String TAG = "ImageWrapperView";
    private ImageLoader mImageLoader;

    private static class InnerType {
        static final int FRESCO = 1;
        static final int GLIDE = 2;
    }

    public ImageWrapperView(@NonNull Context context) {
        this(context, null, R.attr.imageLoader);
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
        int innerType = typedArray.getInt(R.styleable.ImageWrapperView_innerType, InnerType.FRESCO);
        switch (innerType) {
            case InnerType.FRESCO:
                addView(new SimpleDraweeView(context));
                mImageLoader = new FrescoLoader(this);
                break;
            case InnerType.GLIDE:
                break;
        }
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        if (width != 0 && height != 0) {
            Log.e(TAG, "width = " + width + ", height = " + height);
            if (getChildCount() > 0) {
                int childWidthSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY);
                int childHeightSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
                getChildAt(0).measure(childWidthSpec, childHeightSpec);
            }
        }
    }

    public void setImageUri(Uri uri) {
        mImageLoader.setImageUri(uri);
    }

    public void setImageUrl(String url) {
        mImageLoader.setImageUrl(url);
    }

}
