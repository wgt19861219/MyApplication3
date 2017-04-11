package com.example.wgt1986.myapplication.superadapter;

import android.support.v7.widget.RecyclerView;
import com.example.wgt1986.myapplication.superadapter.animation.BaseAnimation;


/**
 * Animation interface for adapter.
 * <p>
 * Created by Cheney on 16/6/28.
 */
interface IAnimation {

    void enableLoadAnimation();

    void enableLoadAnimation(long duration, BaseAnimation animation);

    void cancelLoadAnimation();

    void setOnlyOnce(boolean onlyOnce);

    void addLoadAnimation(RecyclerView.ViewHolder holder);

}
