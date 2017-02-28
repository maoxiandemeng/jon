package com.yidong.jon.nestedscroll;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.yidong.jon.R;
import com.yidong.jon.base.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class NestedScroll2Activity extends BaseActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.nested_scroll)
    NestedScrollLayout nestedScroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i));
        }
        NestedAdapter adapter = new NestedAdapter(this, list);
        recyclerView.setAdapter(adapter);

        nestedScroll.setListener(new NestedScrollLayout.Listener() {
            @Override
            public void scrollChange(float f) {
                Log.i("scrollChange", "f: "+f);
                title.setAlpha(f);
            }
        });

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_nested_scroll2;
    }
}
