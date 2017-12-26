package com.kwork.mediaplayer.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import com.kwork.mediaplayer.R;


public class SplashActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private static final String Tag =  SplashActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //不能直接new Handler().postDelayed()，否则会在启动应用跳转到MainActivity立即返回后
        //还会弹出MainActivity,应该将Handler设置为类的成员变量，并在Destroy()方法中移除所有回掉和消息
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //2秒后执行到这里
                //执行在主线程中
                startMainActivity();
                Log.e(Tag,"当前线程名称=="+Thread.currentThread().getName());
            }
        },2000);
    }

    //跳转到主页面，并把当前页面关闭掉
    private void startMainActivity() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }



    @Override
    /**
    *
    * Author: PanosKwork
    * Time: 2017/12/19 14:42
    * Param: [event]
    * Return: boolean
    * Description: 触摸屏幕立即跳转到主界面
    */
    public boolean onTouchEvent(MotionEvent event) {
        Log.e(Tag,"onTouchEvent==action"+event.getAction());
        startMainActivity();
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}