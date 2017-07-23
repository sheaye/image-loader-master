package sheaye.com.widget.imageloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.net.Uri;
import android.support.annotation.AttrRes;
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
        typedArray.recycle();
        mImageLoader = getImageLoader();
        addView(mImageLoader.getInnerView());
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

    public void setImageUri(Uri uri) {
        if (mImageLoader != null) {
            mImageLoader.setImageUri(uri);
        }
    }

    public void setImageUrl(String url) {
        if (mImageLoader != null) {
            mImageLoader.setImageUrl(url);
        }
    }

}
