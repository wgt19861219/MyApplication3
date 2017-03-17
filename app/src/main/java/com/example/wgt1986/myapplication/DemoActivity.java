package com.example.wgt1986.myapplication;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.example.wgt1986.myapplication.widget.FlowTagLayout;
import com.example.wgt1986.myapplication.widget.OnTagSelectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yexiuliang on 2016/7/11.
 */

public class DemoActivity extends AppCompatActivity {

    private FlowTagLayout mMobileFlowTagLayout;
    private TagAdapter<String> mMobileTagAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        mMobileFlowTagLayout = (FlowTagLayout) findViewById(R.id.multi_select_flow_layout);

        //多选
        mMobileTagAdapter = new TagAdapter<>(this);
        mMobileFlowTagLayout.setTagCheckedMode(FlowTagLayout.FLOW_TAG_CHECKED_MULTI);
        mMobileFlowTagLayout.setAdapter(mMobileTagAdapter);
        mMobileFlowTagLayout.setOnTagSelectListener(new OnTagSelectListener() {
            @Override
            public void onItemSelect(FlowTagLayout parent, int positon, List<Integer> selectedList) {
                if (selectedList != null && selectedList.size() > 0) {
                    StringBuilder sb = new StringBuilder();

                    for (int i : selectedList) {
                        sb.append(parent.getAdapter().getItem(i));
                        sb.append(":");
                    }
                    Toast.makeText(DemoActivity.this, "O(∩_∩)O哈哈哈~:" + sb.toString(), 0).show();
                } else {
                    Toast.makeText(DemoActivity.this, "没有选择标签", 0).show();
                }
            }
        });

        initMobileData();
    }

    private void initMobileData() {
        List<String> dataSource = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataSource.add("多选" + i);


        }
        mMobileTagAdapter.onlyAddAll(dataSource);
    }

}
