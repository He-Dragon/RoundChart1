package com.demo.roundchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RoundChartView roundChartView;
    private RoundChartView roundChartView1;
//    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        roundChartView = (RoundChartView) findViewById(R.id.roundChartView);
        roundChartView1 = (RoundChartView) findViewById(R.id.roundChartView1);
//        mView = (View) findViewById(R.id.mView);
        int[] cor = {
                Color.parseColor("#7ABBCA"),
                Color.parseColor("#939393"),
                Color.parseColor("#97CAE5"),
                Color.parseColor("#5E567D"),
        };
        String[] strings = {
                "刘德华", "古巨基", "苏有朋", "李闯王"
        };
        Double[] percentage = {
                0.25, 0.18, 0.42, 0.15
        };

        roundChartView.setDishPieceColors(cor);
        roundChartView.setDishPieceText(strings);
        roundChartView.setPercentage(percentage);


        /**
         * 动画
         * */

//        ObjectAnimator animator = ObjectAnimator.ofInt(roundChartView1, "backgroundColor", 0xFFFF0000, 0xFFFF00FF);
//        ObjectAnimator animator1 = ObjectAnimator.ofFloat(roundChartView1, "translationX", 0, 200);//按照X轴方向移动
//        ObjectAnimator animator5 = ObjectAnimator.ofFloat(roundChartView1, "translationY", 0, 200);//按照Y轴方向移动
//        ObjectAnimator animator2 = ObjectAnimator.ofFloat(roundChartView1, "scaleX", 1.0f, 2.0f,1.0f);//渐变效果，
//        ObjectAnimator animator3 = ObjectAnimator.ofFloat(roundChartView1, "rotationX", 0.0f, 180.0f);//按照X轴方向翻滚180，再回到原来的位置
////        ObjectAnimator animator4 = ObjectAnimator.ofFloat(roundChartView1, "alpha", 1.0f, 0.2f, 1.0F);
//
        /**
         * 组合动画方式
         * play（）开始的动画  ，
         * with（）有几个with就几个with动画一起进行
         * before（）在之前的动画结束后播放
         * setDuration（）设置动画播放时间
         * */
//        AnimatorSet set = new AnimatorSet();
//        set.play(animator).with(animator5)
//                .with(animator1).before(animator2).before(animator3)
//
////                .before(animator2))
////                .before(animator3))
////                .after(animator4)
//        ;
//        set.setDuration(5000);
//        set.start();



    }
}
