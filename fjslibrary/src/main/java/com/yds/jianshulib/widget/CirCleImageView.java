package com.yds.jianshulib.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Created by yds
 * on 2020/3/10.
 */
public class CirCleImageView extends AppCompatImageView {
    private int mSize;
    private Paint mPaint;
    public CirCleImageView(Context context) {
        this(context,null);
    }

    public CirCleImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CirCleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setDither(true);//防抖动
        mPaint.setAntiAlias(true);//抗锯齿
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        mSize = Math.min(width,height);
        setMeasuredDimension(mSize,mSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        Bitmap sourceBitmap = ((BitmapDrawable)getDrawable()).getBitmap();
        if (sourceBitmap != null) {
            Bitmap bitmap =resizeBitmap(sourceBitmap,getHeight(),getWidth());
            drawCircleBitmapByShader(canvas,bitmap);
        }
    }

    private Bitmap resizeBitmap(Bitmap sourceBitmap,int desHeight,int desWidth){
        int width = sourceBitmap.getWidth();
        int height = sourceBitmap.getHeight();

        float widthScale = ((float)desWidth)/width;
        float heightScale = ((float)desHeight)/height;

        float scale = Math.max(widthScale,heightScale);
        Matrix matrix = new Matrix();
        matrix.postScale(scale,scale);
        return Bitmap.createBitmap(sourceBitmap,0,0,width,height,matrix,true);
    }

    private void drawCircleBitmapByShader(Canvas canvas,Bitmap bitmap){
        BitmapShader shader = new BitmapShader(bitmap,BitmapShader.TileMode.CLAMP,BitmapShader.TileMode.CLAMP);
        mPaint.setShader(shader);
        canvas.drawCircle(mSize/2,mSize/2,mSize/2,mPaint);
    }
}
