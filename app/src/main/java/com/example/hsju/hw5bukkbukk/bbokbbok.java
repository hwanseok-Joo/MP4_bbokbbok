package com.example.hsju.hw5bukkbukk;

/**
 * this class is bbokbbok class for this program
 * 만들어진 bbokbbokView에 bbokbbok을 좌표값에 맞추어 넣어준다.
 * author : hwanseok, Ju
 * e-mail : rokmctkd6@gmail.com
 * last_update : 2015. 06.03
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;

public class bbokbbok {
    boolean flag = true;
    Bitmap bbok;
    Bitmap bbokAfter;
    RectF rectF;
    int bbokX, bbokY;

    public bbokbbok(Context c, int x, int y, int wid, int hei) {
        bbokX = x;
        bbokY = y;
        bbok = BitmapFactory.decodeResource(c.getResources(), R.drawable.bubble1);
        bbokAfter = BitmapFactory.decodeResource(c.getResources(), R.drawable.bubble2);
        bbok = Bitmap.createScaledBitmap(bbok, wid / 5, wid / 5, true);
        bbokAfter = Bitmap.createScaledBitmap(bbokAfter, wid / 5, wid / 5, true);
        rectF = new RectF(bbokX, bbokY, bbokX + bbok.getWidth(), bbokY + bbok.getHeight());
    }
}


