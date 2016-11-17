package com.demo.roundchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hecl on 2016/11/8.
 */

public class RoundChartView extends View {

    private Paint shadePaint;//最外面阴影的画笔
    private Paint dishPaint;//每个盘快的画笔
    private Paint textPaint;//文字的画笔
    private Paint centerPaint;//绘制中心的圆的画笔
    private Paint centerTextPaint;//绘制中心文字的画笔
    private Paint centerBelowTextPaint;//总和下面的字体的画笔


    private int height;
    private int width;
    private int roundRadius;//圆饼半径

    private int[] arcColor = { Color.rgb(202,113,153)
            , Color.parseColor("#E15A4F"), Color.parseColor("#1C8CEC")
            , Color.parseColor("#FFCE43"), Color.parseColor("#CE00FB")};

    private String[] textStr = {
            "盘快1", "盘快2", "盘快3", "盘快4", "盘快5",
    };
    private Double[] percentage = {
            0.2, 0.2, 0.2, 0.2, 0.2
    };


    public RoundChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        shadePaint = new Paint();
        shadePaint.setColor(Color.parseColor("#33868990"));
        shadePaint.setAntiAlias(true);
        shadePaint.setStyle(Paint.Style.STROKE);
        shadePaint.setStrokeWidth(20);//设置阴影部分的宽度

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.parseColor("#FFFFFF"));//设置每个盘快字体的颜色

        dishPaint = new Paint();
        dishPaint.setAntiAlias(true);

        centerPaint = new Paint();
        centerPaint.setAntiAlias(true);
        centerPaint.setColor(Color.parseColor("#FEFEF2"));//中心圆的颜色


        centerTextPaint = new Paint();
        centerTextPaint.setAntiAlias(true);
        centerTextPaint.setColor(Color.parseColor("#2B2B2B"));//设置中心字体的颜色

        centerBelowTextPaint = new Paint();
        centerBelowTextPaint.setAntiAlias(true);
        centerBelowTextPaint.setColor(Color.parseColor("#5D5955"));//设置中心字体的颜色


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        if (height == 0||width == 0) {
            roundRadius = 0;
        		}else {
            roundRadius = Math.min(height,width)/3;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.width = getWidth();
        if (roundRadius != 0&&arcColor != null && textStr != null && percentage != null
                && arcColor.length == textStr.length
                && textStr.length == percentage.length) {
            canvas.drawCircle(width / 2, height / 2, roundRadius, shadePaint);//画圆饼的外圈颜色
            setArc(canvas);//绘制每个盘快
        }

    }

    /**
     * 绘制每个盘快
     */
    private void setArc(Canvas canvas) {
        int startAngle = 0;//设置盘快的起始角度
        for (int i = 0; i < arcColor.length; i++) {
            int angle;
            if (i == arcColor.length - 1) {
                angle = 360-startAngle;
            }else {
                angle = (int) (360 * percentage[i]);//设置每个盘快的角度
            }
            RectF rect = new RectF(width / 2 - roundRadius + 5, height / 2 - roundRadius + 5
                    , width / 2 + roundRadius - 5, height / 2 + roundRadius - 5);//设置盘快的区域
            dishPaint.setColor(arcColor[i]);//设置每个盘快的颜色
            canvas.drawArc(rect, startAngle, angle, true, dishPaint);//绘制盘快
            drawText(canvas, textStr[i], rect, startAngle, angle, percentage[i]);
            startAngle += angle;

        }
        drawCenterRound(canvas);//绘制中心圆
    }

    /**
     * 绘制每个盘快的文字
     * str 板块的名字
     * rect 绘制板块的范围
     * startAngle 开始的角度
     * angle 旋转的角度
     * percentage 盘快所占的百分比
     */
    private void drawText(Canvas canvas, String str, RectF rect, int startAngle, int angle, Double percentage) {
        Path path = new Path();//绘制每个盘快文字的路径
        path.addArc(rect, startAngle, angle);//设置路径的位置以及长度
        textPaint.setTextSize(25);//设置每个盘快字体的大小为每个弧度大小的十分之一
        int textLe = (int) textPaint.measureText(str);//文字的长度
        int hOffset = (int) (((roundRadius * 2) * Math.PI) * percentage - textLe) / 2;//水平偏移长度
        int vOffset = roundRadius / 5;//垂直偏移长度
        canvas.drawTextOnPath(str, path
                , hOffset, vOffset, textPaint);
    }

    /**
     * 绘制中心的圆和中心圆的文字
     */
    private void drawCenterRound(Canvas canvas) {
        int centerRoundRadius = roundRadius / 3;//中心圆的半径
        centerTextPaint.setTextSize(centerRoundRadius / 3);//设置中心字体的大小，为中心小圆的3分之一
        centerBelowTextPaint.setTextSize(centerRoundRadius / 4);//设置中心字体的大小，为中心小圆的4分之一
        int centerTextWidth = (int) centerTextPaint.measureText("总和");
        int centerBlowTextWidth = (int) centerBelowTextPaint.measureText("520");
        canvas.drawCircle(width / 2, height / 2, centerRoundRadius, centerPaint);
        canvas.drawText("总和",
                (width / 2 - centerRoundRadius) + (centerRoundRadius * 2 - centerTextWidth) / 2
                , height / 2, centerTextPaint);
        canvas.drawText("520",
                (width / 2 - centerRoundRadius) + (centerRoundRadius * 2 - centerBlowTextWidth) / 2
                , height / 2 + centerRoundRadius / 3 + 5, centerBelowTextPaint);
    }

    /**
     * 设置盘快的颜色
     */
    public void setDishPieceColors(int[] arcColor) {
        this.arcColor = arcColor;
        invalidate();

    }

    /**
     * 设置盘快的字体
     */
    public void setDishPieceText(String[] text) {
        this.textStr = text;
        invalidate();
    }

    /**
     * 设置每个板块的百分比
     */
    public void setPercentage(Double[] percentage) {
        this.percentage = percentage;
        invalidate();
    }

}
