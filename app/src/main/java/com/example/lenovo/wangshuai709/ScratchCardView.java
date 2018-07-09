package com.example.lenovo.wangshuai709;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


public class ScratchCardView extends View {

    private static final String TAG ="ScratchCardView" ;

    private int width;
    private int height;
    private String text = "猜猜她是谁";
    private Paint paint;
    private Path path;
    private float downX,downY;
    private float tempX,tempY;
    private Canvas myCanvas;
    private Bitmap myBitmap;

    private Bitmap bitmapa;

    private Bitmap bitmapb;

    private Bitmap bitmapc;

    private Bitmap bitmapd;

    private Bitmap bitmap;

    public ScratchCardView(Context context) {

        this(context,null);

    }



    public ScratchCardView(Context context, AttributeSet attrs) {

        this(context, attrs,0);

    }



    public ScratchCardView(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);

        initBitmap();

        initPaint();

    }

    private void initBitmap() {

        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.san5);

        bitmapa = BitmapFactory.decodeResource(getResources(),R.drawable.san4);

        bitmapb = BitmapFactory.decodeResource(getResources(),R.drawable.san3);

        bitmapc = BitmapFactory.decodeResource(getResources(),R.drawable.san1);

        bitmapd = BitmapFactory.decodeResource(getResources(),R.drawable.san2);

    }

    private void initPaint() {

        paint = new Paint();

        paint.setAntiAlias(true);

        paint.setStyle(Paint.Style.STROKE);

        paint.setStrokeWidth(20);

        paint.setTextSize(24);

        paint.setDither(true);

        path = new Path();

    }



    @Override

    protected void onSizeChanged(int w, int h, int oldw, int oldh) {

        super.onSizeChanged(w, h, oldw, oldh);

        width = w;

        height = h;

        myBitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);

        myCanvas = new Canvas(myBitmap);

        myCanvas.drawColor(Color.GREEN);

    }

    @Override

    protected void onDraw(Canvas canvas) {

        canvas.drawBitmap(bitmap,0,0,null);//底层图

        drawPath();//这个path是绘制在myvanvas画布上的

        canvas.drawBitmap(myBitmap,0,0,null);

        super.onDraw(canvas);

    }

    private void drawPath() {

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        myCanvas.drawPath(path,paint);

    }

    @Override

    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:

                downX = event.getX();

                downY = event.getY();

                path.moveTo(downX,downY);

                invalidate();

                tempX = downX;

                tempY = downY;

                break;

            case MotionEvent.ACTION_MOVE:

                float moveX = event.getX();

                float moveY = event.getY();

                path.quadTo(tempX,tempY,moveX,moveY);

                invalidate();

                tempX = moveX;

                tempY = moveY;

                break;

        }

        return true;

    }

}

