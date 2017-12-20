package com.kwork.mediaplayer.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.kwork.mediaplayer.R;

/**
 * Author: PanosKwork
 * Time: 2017/12/19 13:09
 * Description: 主界面
 */
public class MainActivity extends Activity {
    private FrameLayout frameLayout;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        frameLayout = findViewById(R.id.fl_main_content);
        radioGroup = findViewById(R.id.rg_bottom_tag);
        radioGroup.check(R.id.rb_video);
    }
}

