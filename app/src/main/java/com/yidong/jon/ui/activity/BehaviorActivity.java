package com.yidong.jon.ui.activity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import butterknife.OnTouch;

public class BehaviorActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_behavior;
    }

    @OnTouch(R.id.button)
    public boolean btnTouch(View v, MotionEvent event){
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                v.setX(event.getRawX() - v.getWidth()/2);
                v.setY(event.getRawY() - v.getHeight()/2);
                break;
        }
        return false;
    }
}
