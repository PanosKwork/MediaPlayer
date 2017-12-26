package com.kwork.mediaplayer.base;

import android.content.Context;
import android.view.View;

/**
*
* Author: PanosKwork
* Time: 2017/12/20 12:51
* Description: 各页面的基类（实现数据和视图相分离，让架构更灵活）
*
*/
public abstract class BasePager {
    public final Context context;
    public final View rootView;

    public BasePager(Context context) {
        this.context = context;
        rootView = initView();

    }

    /**
     * 强制子类实现此方法
     * @return
     */
    public abstract View initView();


    /**
     * 当子页面需要初始化数据，联网请求数据，或者绑定本地数据的时候需要重写此方法
     */
    public  void initData(){

    }
}
