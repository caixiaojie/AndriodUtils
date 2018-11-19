package com.yjhs.cbsd_commonlibrary.widget.img;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.yjhs.cbsd_commonlibrary.R;


/**
 * 圆角的ImageView
 *
 * @author yuyh.
 * @date 16/4/11.
 */
public class RounderImageView extends ImageView {

    private Paint paint;
    private int roundWidth = 5; // 默认圆角大小
    private int roundHeight = 5;
    private Paint paint2;
    private Boolean lefuUp, leftDown, rightUp, rightDown;

    public RounderImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public RounderImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RounderImageView(Context context) {
        this(context, null);
    }

    private void init(Context context, AttributeSet attrs) {

        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RounderImageView);
            roundWidth = a.getDimensionPixelSize(R.styleable.RounderImageView_roundWidth, roundWidth);  // xml属性里配置
            roundHeight = a.getDimensionPixelSize(R.styleable.RounderImageView_roundHeight, roundHeight);
            lefuUp = a.getBoolean(R.styleable.RounderImageView_leftUp, true);
            leftDown = a.getBoolean(R.styleable.RounderImageView_leftDown, true);
            rightUp = a.getBoolean(R.styleable.RounderImageView_rightUp, true);
            rightDown = a.getBoolean(R.styleable.RounderImageView_rightDown, true);
        } else {
            float density = context.getResources().getDisplayMetrics().density;
            roundWidth = (int) (roundWidth * density);
            roundHeight = (int) (roundHeight * density);
            lefuUp = true;
            leftDown = true;
            rightUp = true;
            rightDown = true;
        }

        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_OUT));

        paint2 = new Paint();
        paint2.setXfermode(null);
    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas2 = new Canvas(bitmap);
        super.draw(canvas2);
        if (lefuUp)
            drawLeftUp(canvas2);
        if (rightUp)
            drawRightUp(canvas2);
        if (leftDown)
            drawLeftDown(canvas2);
        if (rightDown)
            drawRightDown(canvas2);
        canvas.drawBitmap(bitmap, 0, 0, paint2);
        bitmap.recycle();
    }

    private void drawLeftUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, roundHeight);
        path.lineTo(0, 0);
        path.lineTo(roundWidth, 0);
        path.arcTo(new RectF(0, 0, roundWidth * 2, roundHeight * 2), -90, -90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawLeftDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(0, getHeight() - roundHeight);
        path.lineTo(0, getHeight());
        path.lineTo(roundWidth, getHeight());
        path.arcTo(new RectF(0, getHeight() - roundHeight * 2, 0 + roundWidth * 2, getHeight()), 90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightDown(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth() - roundWidth, getHeight());
        path.lineTo(getWidth(), getHeight());
        path.lineTo(getWidth(), getHeight() - roundHeight);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, getHeight() - roundHeight * 2, getWidth(), getHeight()), 0, 90);
        path.close();
        canvas.drawPath(path, paint);
    }

    private void drawRightUp(Canvas canvas) {
        Path path = new Path();
        path.moveTo(getWidth(), roundHeight);
        path.lineTo(getWidth(), 0);
        path.lineTo(getWidth() - roundWidth, 0);
        path.arcTo(new RectF(getWidth() - roundWidth * 2, 0, getWidth(), 0 + roundHeight * 2), -90, 90);
        path.close();
        canvas.drawPath(path, paint);
    }
}
