package sheaye.com.widget.imageloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.AttrRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import sheaye.com.widget.R;

/**
 * Created by yexinyan on 2017/7/21.
 */

public abstract class AbstractImageWrapperView extends FrameLayout {

    private static final String TAG = "ImageWrapperView";
    private ImageLoader mImageLoader;

    public AbstractImageWrapperView(@NonNull Context context) {
        this(context, null, 0);
    }

    public AbstractImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AbstractImageWrapperView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs, defStyleAttr);
    }

    private void initView(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.AbstractImageWrapperView, defStyleAttr, 0);
        mImageLoader = getImageLoader();
        addView(mImageLoader.getInnerView());
        int scaleType = typedArray.getInt(R.styleable.AbstractImageWrapperView_scaleType, -1);
        mImageLoader.setActualImageScaleType(scaleType);
        typedArray.recycle();
    }

    public abstract ImageLoader getImageLoader();

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
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setImageUri(Uri uri) {
        if (mImageLoader != null) {
            mImageLoader.setImageUri(uri);
        }
        return this;
    }

    /**
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setImageUrl(String url) {
        if (mImageLoader != null) {
            mImageLoader.setImageUrl(url);
        }
        return this;
    }

    /**
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setActualImageScaleType(int scaleType) {
        if (mImageLoader != null) {
            mImageLoader.setActualImageScaleType(scaleType);
        }
        return this;
    }

    /**
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setPlaceHolderScaleType(int scaleType) {
        if (mImageLoader != null) {
            mImageLoader.setPlaceHolderScaleType(scaleType);
        }
        return this;
    }

    /**
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setPlaceHolderImage(int resId) {
        if (mImageLoader != null) {
            mImageLoader.setPlaceHolderImage(resId);
        }
        return this;
    }

    /**
     * ensure this works, please call commit() in the end.
     */
    public AbstractImageWrapperView setRoundAsCircle(boolean round) {
        if (mImageLoader != null) {
            mImageLoader.setRoundAsCircle(round);
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
