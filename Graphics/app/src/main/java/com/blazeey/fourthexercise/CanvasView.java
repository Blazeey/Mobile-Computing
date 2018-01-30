package com.blazeey.fourthexercise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by venki on 6/1/18.
 */

public class CanvasView extends View {

    private Paint paint;
    private int x,y,radius;
    private RectF rectF,drawRect;
    private Canvas canvas;
    private Context context;
    private float scaleX = 1,scaleY = 1,rotate = 0;
    private boolean incrementFlag = true;

    public CanvasView(Context context) {
        super(context);
        this.context = context;
        paint = new Paint();
        rectF = new RectF();
        drawRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        scaleAndRotate();
        if(incrementFlag) {
            scaleX += 0.002;
            scaleY += 0.002;
        }
        else {
            scaleX -= 0.002;
            scaleY -= 0.002;
        }
        canvas.save();
        if(scaleX>2&&scaleY>2){
            incrementFlag = false;
        }
        if(scaleX<1&&scaleY<1){
            incrementFlag = true;
        }
        rotate += 0.6;

    }

    public void draw(){

        setLayerType(LAYER_TYPE_SOFTWARE,paint);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.CYAN);

        x = canvas.getWidth()/2;
        y = canvas.getHeight()/2;
        radius = canvas.getWidth()/3;

        paint.setAntiAlias(false);
        paint.setColor(Color.CYAN);
        drawRect.set(0,y/2-100,x*2,y+y/2+100);
        paint.setStyle(Paint.Style.FILL);
//        canvas.drawRect(drawRect,paint);
        canvas.drawPaint(paint);

        paint.setColor(Color.YELLOW);
        canvas.drawCircle(x,y,radius,paint);

        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(x,y,radius,paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        canvas.drawCircle(x-x/3,y-y/3,100,paint);
        canvas.drawCircle(x+x/3,y-y/3,100,paint);

        rectF.set(x-200,y-450,x+200,y+150);
        canvas.drawArc(rectF,45,90,false,paint);

    }

    public void scaleAndRotate(){
        canvas.save();
        canvas.scale(scaleX,scaleY,getWidth()/2,getHeight()/2);
        canvas.rotate(rotate,getWidth()/2,getHeight()/2);
        draw();
//        Log.v("Canvas","Scale");
    }
}
