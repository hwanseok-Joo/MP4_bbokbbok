package com.example.hsju.hw5bukkbukk;

/**
 * this class is bbokbbokView class.
 * 여기에선 2차원어레이를 사용하여 화면에 맞게 bbokbbok들을 만들어주고
 * 이것들을 onDraw 매서드를 이용하여 그려준다.
 * 또한 터치하는 좌표값을 얻어 bbokbbok의 범위 내에서 터치이벤트가 발생한다면 그림을 바꾸어준다.
 * 그림을 바뀌는 bbokbbok에대해서는 카운트를 세어준다.
 * 만약 전부 바뀌엇을경우 다이얼로그창을 띄워서 리셋해주거나 끝내기를 선택할수 있게 한다.
 * author : hwanseok, Ju
 * e-mail : rokmctkd6@gmail.com
 * last_update : 2015. 06.03
 */

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class bbokbbokView extends View {
    bbokbbok bbok[][] = new bbokbbok[5][5];
    int wid;
    int hei;
    String msg;
    AlertDialog dialBox = createDialogBox();
    Context c;

    public bbokbbokView(Context c) {
        super(c);
    }

    public bbokbbokView(Context c, AttributeSet a) {
        super(c, a);
        this.c = c;
        init();
    }

    // create the bbokbbok
    public void init() {
        int mWidth = getContext().getResources().getDisplayMetrics().widthPixels;
        int mHeight = getContext().getResources().getDisplayMetrics().heightPixels;
        wid = mWidth - 100;
        hei = mHeight - 512;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bbok[i][j] = new bbokbbok(c, wid * i / 5, hei * j / 5, wid, hei);
            }
        }
    }

    //draw the bbok image. if bbok[][].flag is true, show the normal bubble.
    //else bbok[][].flag is false, show poped bubble.
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                if (bbok[i][j].flag == true)
                    canvas.drawBitmap(bbok[i][j].bbok, bbok[i][j].bbokX, bbok[i][j].bbokY, null);
                else
                    canvas.drawBitmap(bbok[i][j].bbokAfter, bbok[i][j].bbokX, bbok[i][j].bbokY, null);
            }
    }

    // when you touch the screen, gain the x,y coordinate.
    //then if bbok[][].flag is true, change the boolean value.
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float eventX = event.getX();
            float eventY = event.getY();
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (bbok[i][j].rectF.contains(eventX, eventY)) {
                        if (bbok[i][j].flag == true)
                            bbok[i][j].flag = false;
                        invalidate();
                    }
                }
            }
            int cnt = 0;
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (bbok[i][j].flag == false) {
                        cnt++;
                    }
                    if (cnt == 25) {
                        dialBox.show();
                    }
                }
            }
        }
        return true;
    }

    // when show the dialgobox, you can select the button as a action.
    // if you select the Again button, bboks are reset.
    //else if you select the finish, program is closed.
    private AlertDialog createDialogBox() {
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(getContext())
                .setTitle("Finish")
                .setMessage("Are you sure that you want to quit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        System.exit(0);
                    }
                })
                .setNegativeButton("Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        init();
                        invalidate();
                    }
                }).create();
        return myQuittingDialogBox;
    }
}
