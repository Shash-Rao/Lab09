package com.raoshashwat.lab09;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class DrawView extends View
{
    Sprite sprite = new Sprite();
    double eventX;
    double eventY;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.bluejeans);
        sprite = new Sprite(1, 1,  131, 195);
        sprite.setBitmap(image);
    }

    public DrawView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if (Math.abs(eventX - sprite.centerX()) < 10 && Math.abs(eventY - sprite.centerY()) < 10)
        {
            sprite.setDx(0);
            sprite.setDy(0);
        }
        sprite.update();
        sprite.draw(canvas);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
         if (event.getAction() == MotionEvent.ACTION_MOVE)
         {
                eventX = event.getX();
                eventY = event.getY();
                double x = eventX - sprite.centerX();
                double y = eventY - sprite.centerY();
                double mag = Math.sqrt(x * x + y * y);
                x = x / mag;
                y = y / mag;
                //System.out.println(sprite.centerX() + " " + sprite.centerY() + " " + event.getX() + " " + event.getY());
                sprite.setDx((float) (2.5 * x));
                sprite.setDy((float) (2.5 * y));
        }
        return true;
    }
}
