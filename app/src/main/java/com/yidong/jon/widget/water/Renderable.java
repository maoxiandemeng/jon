package com.yidong.jon.widget.water;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Administrator on 2016/10/8.
 */
public class Renderable {
    protected Bitmap bitmap;
    //绘制bitmap放置的x y坐标
    protected float x;
    protected float y;

    public Renderable(Bitmap bitmap, float x, float y) {
        this.bitmap = bitmap;
        this.x = x;
        this.y = y;
    }

    public void draw(Canvas canvas){
        canvas.save();
        canvas.drawBitmap(bitmap, x, y, null);
        canvas.restore();
    }

    public void update(float deltayTime){

    }
}
