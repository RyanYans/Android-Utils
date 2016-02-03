package com.zone.banner_zonelib.indicator.type;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.zone.banner_zonelib.indicator.type.abstarct.AbstractIndicator;

/**
 * Created by Administrator on 2016/1/27.
 */
public class CircleIndicator extends AbstractIndicator {
    private Paint paint = null;
    private Bitmap defaultBitmap, selectedBitmap;
    private float radius;
    private ShapeEntity defaultCircleStyle,topCircleStyle;

    private CircleIndicator(int width, int height) {
        super(2 * width, 2 * height);
        this.radius = 1F * width;
        paint = new Paint();
        setCircleEntity (new ShapeEntity().setStrokeColor(Color.WHITE),new ShapeEntity().setFillColor(Color.RED));
    }

    public CircleIndicator setCircleEntity(ShapeEntity defaultCircleStyle, ShapeEntity topCircleStyle){
        this.defaultCircleStyle=defaultCircleStyle;
        this.topCircleStyle=topCircleStyle;

        createDefalutBitmap();
        createSelectedBitmap();
        setBetweenMargin(width);
        return this;
    }

    private void initPaint() {
        paint.reset();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setFilterBitmap(true);
    }

    public CircleIndicator(int radius) {
        this(radius, radius);
    }

    private void createDefalutBitmap() {
        defaultBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(defaultBitmap);
        //画fill
        initPaint();
        paint.setStyle(Paint.Style.FILL);
        if (!defaultCircleStyle.isHaveFillColor())
            paint.setColor(Color.TRANSPARENT);
        else
            paint.setColor(defaultCircleStyle.getFillColor());
        canvas.drawCircle(radius, radius, radius-defaultCircleStyle.getStrokeWidthHalf()*2, paint);
        //画stroke
        initPaint();
        paint.setStyle(Paint.Style.STROKE);
        if (!defaultCircleStyle.isHaveStrokeColor())
            paint.setColor(Color.TRANSPARENT);
        else
            paint.setColor(defaultCircleStyle.getStrokeColor());
        paint.setStrokeWidth(defaultCircleStyle.getStrokeWidthHalf()*2);
        canvas.drawCircle(radius, radius, radius-topCircleStyle.getStrokeWidthHalf(), paint);
    }

    private void createSelectedBitmap() {
        selectedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        Canvas canvas = new Canvas(selectedBitmap);
        //画fill
        initPaint();
        paint.setStyle(Paint.Style.FILL);
        if (!topCircleStyle.isHaveFillColor())
            paint.setColor(Color.TRANSPARENT);
        else
            paint.setColor(topCircleStyle.getFillColor());
        canvas.drawCircle(radius, radius, radius-topCircleStyle.getStrokeWidthHalf()*2, paint);
        //画stroke
        initPaint();
        paint.setStyle(Paint.Style.STROKE);
        if (!topCircleStyle.isHaveStrokeColor())
            paint.setColor(Color.TRANSPARENT);
        else
            paint.setColor(topCircleStyle.getStrokeColor());
        paint.setStrokeWidth(topCircleStyle.getStrokeWidthHalf()*2);
        canvas.drawCircle(radius, radius, radius-topCircleStyle.getStrokeWidthHalf(), paint);
    }

    @Override
    public Bitmap getDefaultBitmap(int position) {
        return defaultBitmap;
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //TODO 注意 第一次加载的时候  走这个而不走 onPageSelected
        if(positionOffsetPixels==0)
            ivTop.setImageBitmap(selectedBitmap);
        //TODO 这个方法也可以处理图像透明度渐变等特效
    }





}
