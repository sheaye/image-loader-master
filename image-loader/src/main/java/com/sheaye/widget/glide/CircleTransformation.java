package com.sheaye.widget.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by yexinyan on 2017/7/24.
 */

public class CircleTransformation extends BitmapTransformation {

    public CircleTransformation(Context context) {
        super(context);
    }

    @Override
    public String getId() {
        return "CropCircleTransformation()";
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        int diameter = Math.min(toTransform.getWidth(), toTransform.getHeight());

        int translateX = (toTransform.getWidth() - diameter) / 2;
        int translateY = (toTransform.getHeight() - diameter) / 2;

        Bitmap bitmap = pool.get(diameter, diameter, Bitmap.Config.ARGB_8888);
        if (bitmap == null) {
            bitmap = Bitmap.createBitmap(diameter, diameter, Bitmap.Config.ARGB_8888);
        }

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint();
        BitmapShader shader =
                new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
        if (translateX != 0 || translateY != 0) {
            // toTransform isn't square, move viewport to center
            Matrix matrix = new Matrix();
            matrix.setTranslate(-translateX, -translateY);
            shader.setLocalMatrix(matrix);
        }
        paint.setShader(shader);
        paint.setAntiAlias(true);

        float r = diameter / 2f;
        canvas.drawCircle(r, r, r, paint);
        return bitmap;
    }
}
