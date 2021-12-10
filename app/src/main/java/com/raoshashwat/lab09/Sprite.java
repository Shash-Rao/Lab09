package com.raoshashwat.lab09;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Picture;
import android.graphics.Rect;
import android.graphics.RectF;

public class Sprite extends RectF
{
    private float dx, dy;
    private int color;
    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap)
    {
        this.bitmap = bitmap;
    }

    private final int ROWS = 4, COLS = 4;
    private int iconWidth, iconHeight;
    private final int UP = 3, DOWN = 0, LEFT = 1, RIGHT = 2;
    private int currentFrame = 0;
    private int count;

    public void setDx(float dx)
    {
        this.dx = dx;
    }

    public void setDy(float dy)
    {
        this.dy = dy;
    }

    public Sprite(float left, float top, float right, float bottom, int dx, int dy, int color)
    {
        super(left, top, right, bottom);
        this.dx = dx;
        this.dy = dy;
        this.color = color;
    }

    public Sprite(float left, float top, float right, float bottom)
    {
        this(left, top, right, bottom, 0, 0, Color.BLACK);
    }

    public Sprite(int dx, int dy, int color)
    {
        this(1, 1, 11, 11, dx, dy, color);
    }

    public Sprite()
    {
        this(0, 0, Color.BLACK);
    }

    public void update()
    {
        offset(dx, dy);
    }

    public void draw(Canvas canvas)
    {
        iconWidth = bitmap.getWidth() / COLS;
        iconHeight = bitmap.getHeight() / ROWS;

        if (dx == 0 && dy == 0)
            currentFrame = 0;

        int srcX = currentFrame * iconWidth;
        int temp = getAnimationRow();
        int srcY = temp * iconHeight;

        float tempd;
        if (temp == LEFT || temp == RIGHT)
            tempd = dx;
        else
            tempd = dy;

        Rect src = new Rect(srcX, srcY, srcX + iconWidth, srcY + iconHeight);
        canvas.drawBitmap(bitmap, src, this,null);
        if (count % 10 == 0)
            currentFrame = (currentFrame + 1) % COLS;
        count ++;
    }

    private int getAnimationRow()
    {
        if (Math.abs(dx)>Math.abs(dy))
        {
            if(dx > 0)
                return RIGHT;
            else
                return LEFT;
        }
        else if (dy >= 0)
            return DOWN;
        else return UP;
    }

    public void grow(int i)
    {
        right += i;
        left += i;
    }
}
