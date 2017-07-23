package sheaye.com.widget.imageloader;

import android.net.Uri;
import android.view.View;


/**
 * Created by yexinyan on 2017/7/21.
 */

public interface ImageLoader {

    void setImageUri(Uri uri);

    void setImageUrl(String url);

    View getInnerView();

}
