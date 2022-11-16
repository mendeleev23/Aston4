package com.example.aston42;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;


public class MyClock extends View {

    Paint circle, circleDash, seconds, minutes, hours;
    int second = 210;
    int minute = 282;
    int hour = 54;

    public MyClock(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        circle = new Paint();
        circle.setColor(Color.BLACK);
        circle.setStrokeWidth(25);
        circle.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getRight()/2,
                getBottom()/2,500, circle);
        DashPathEffect dashPath = new DashPathEffect(new float[]{24,212}, 1);
        circleDash = new Paint();
        circleDash.setColor(Color.BLACK);
        circleDash.setStyle(Paint.Style.STROKE);
        circleDash.setStrokeWidth(80);
        circleDash.setPathEffect(dashPath);
        canvas.drawCircle(getRight()/2,
                getBottom()/2,450, circleDash);
        second+=6;
        if (second==366){
            second-=360;
        }
        if((second % 60==0)&&(second!=0)) {
            minute += 1;
        }
        if (minute==366){
            minute-=360;
        }
        if ((minute % 60==0)&&(minute!=0)){
            hour +=1;
        }
        if (hour==366){
            hour-=360;
        }
        // Нарисовать часовую стрелку
        hours = new Paint();
        hours.setColor(Color.BLACK);
        hours.setStyle(Paint.Style.STROKE);
        hours.setStrokeWidth(30);
        int f = canvas.save();
        canvas.rotate(hour,getRight()/2,getBottom()/2);
        canvas.drawLine(getRight()/2,getBottom()/2+150,getRight()/2,getBottom()/2-350,hours);
        // Нарисовать минутную стрелку
        canvas.restoreToCount(f);
        minutes = new Paint();
        minutes.setColor(Color.RED);
        minutes.setStrokeWidth(25);
        f = canvas.save();
        canvas.rotate(minute,getRight()/2,getBottom()/2); // Поворот на угол минутной стрелки
        canvas.drawLine(getRight()/2,getBottom()/2+100,getRight()/2,getBottom()/2-250,minutes);
        // Нарисовать минутную стрелку;
        canvas.restoreToCount(f);
        seconds = new Paint();
        seconds.setStrokeWidth(15);
        seconds.setColor(Color.BLUE);
        canvas.rotate(second,getRight()/2,getBottom()/2); // Поворот на угол минутной стрелки
        canvas.drawLine(getRight()/2,getBottom()/2+75,getRight()/2,getBottom()/2-150,seconds);
        postInvalidateDelayed(1000);
    }
}