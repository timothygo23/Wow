package com.agorda.wow.ui.scale;

import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by Timothy on 15/12/2017.
 */

public class ScaleBitmap {
    public static Bitmap scaleBitmap(Bitmap bitmap, float width, float height){
        int bmWidth = bitmap.getWidth();
        int bmHeight = bitmap.getHeight();

        float scaleWidth = width / bmWidth;
        float scaleHeight = height / bmHeight;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, bmWidth, bmHeight, matrix, false);
        return newBitmap;
    }
}
