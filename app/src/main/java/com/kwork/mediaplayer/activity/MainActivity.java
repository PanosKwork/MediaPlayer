package com.kwork.mediaplayer.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.kwork.mediaplayer.R;
import com.kwork.mediaplayer.base.BasePager;
import com.kwork.mediaplayer.pager.AudioPager;
import com.kwork.mediaplayer.pager.NetAudioPager;
import com.kwork.mediaplayer.pager.NetVideoPager;
import com.kwork.mediaplayer.pager.VideoPager;

import java.util.ArrayList;

/**
 * Author: PanosKwork
 * Time: 2017/12/19 13:09
 * Description: 主界面
 */
public class MainActivity extends FragmentActivity {

    private FrameLayout fl_main_content;
    private RadioGroup rg_bottom_tag;
    /**
     * 选中的位置
     */
    private static int position;
    /**
     * 页面的集合
     */
    private static ArrayList<BasePager> basePagers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fl_main_content = findViewById(R.id.fl_main_content);
        rg_bottom_tag = findViewById(R.id.rg_bottom_tag);
        //rg_bottom_tag.check(R.id.rb_video);

        basePagers = new ArrayList<>();
        //添加各个页面，对应位置0，1，2，3
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        //设置底部VideoGroup的监听
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        //设置监听之后再设置默认选中，第一个页面的textview才会生效
        rg_bottom_tag.check(R.id.rb_video);
    }

    /**
     * Author: PanosKwork
     * Time: 2017/12/24 11:08
     * Description: 定义一个RadioGroup监听类,根据位置得到不同的实例BasePager
     */
    public class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                default:
                    position = 0;
                    break;
                case R.id.rb_audio:
                    position = 1;
                    break;
                case R.id.rb_net_video:
                    position = 2;
                    break;
                case R.id.rb_net_audio:
                    position = 3;
                    break;
            }

            setFragment();
        }
    }


    public static class ContentFragment extends android.support.v4.app.Fragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            BasePager basePager = getBasePager();
            if (basePager != null) {
                //各个页面的视图
                return basePager.rootView;
            }
            return null;
        }
    }

    /**
     * 根据位置得到对应的页面
     *
     * @return
     */
    public static BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if (basePager != null) {
            basePager.initData();
        }
        return basePager;
    }

    /**
     * 把页面添加到Fragment中
     */
    private void setFragment() {
        //1.得到FragmentManager
        FragmentManager manager = getSupportFragmentManager();
        //2.开启事务
        FragmentTransaction ft = manager.beginTransaction();
        //3.替换
        ContentFragment contentFragment = new ContentFragment();
        ft.replace(R.id.fl_main_content, contentFragment);
        //4.提交事务
        ft.commit();
    }

}

