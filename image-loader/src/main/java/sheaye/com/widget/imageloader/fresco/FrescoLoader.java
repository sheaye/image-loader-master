package sheaye.com.widget.imageloader.fresco;

import android.net.Uri;

import com.facebook.drawee.view.SimpleDraweeView;

import sheaye.com.widget.imageloader.ImageLoader;
import sheaye.com.widget.imageloader.ImageWrapperView;

/**
 * Created by yexinyan on 2017/7/22.
 */

public class FrescoLoader implements ImageLoader {

    private SimpleDraweeView mDraweeView;

    public FrescoLoader(ImageWrapperView imageWrapperView) {
        if (imageWrapperView.getChildCount() > 0) {
            mDraweeView = ((SimpleDraweeView) imageWrapperView.getChildAt(0));
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
