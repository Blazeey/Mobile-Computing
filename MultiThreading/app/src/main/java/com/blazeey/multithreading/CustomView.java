package com.blazeey.multithreading;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import java.util.Random;

/**
 * Created by venki on 12/2/18.
 */

public class CustomView extends View {

    private int[] colors = {Color.RED,Color.CYAN,Color.BLUE,Color.GREEN,Color.MAGENTA,Color.YELLOW};
    private Paint paint;
    private Rect rectangle;
    int y = 10,x = 10;
    private Canvas canvas = new Canvas();
    Random random = new Random();

    public CustomView(Context context) {
        super(context);
        paint = new Paint();
        paint.setColor(Color.BLACK);
        rectangle = new Rect();
        rectangle.set(x,y,getWidth()-100,getHeight()/5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        setLayerType(View.LAYER_TYPE_SOFTWARE,paint);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(rectangle,paint);
        paint.setTextSize(50f);
        paint.setColor(Color.BLACK);
//        Log.v("X,Y",x+","+y);
        canvas.drawText("("+x+","+y+")",x,y+50,paint);
        canvas.save();
        updateColor();
//        updatePosition();
    }

    public void updatePosition(){
        rectangle.set(x,y,getWidth()-10,getHeight()/5);
        if(y>=getHeight())
            y = 10;
        else
            y++;
    }

    public void updateColor(){
        paint.setColor(colors[random.nextInt(6)]);
    }
}
