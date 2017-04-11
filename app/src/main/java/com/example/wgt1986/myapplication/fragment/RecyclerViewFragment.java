package com.example.wgt1986.myapplication.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.*;
import android.widget.TextView;
import com.example.wgt1986.myapplication.R;
import com.example.wgt1986.myapplication.model.DataUtils;
import com.example.wgt1986.myapplication.model.MockModel;
import com.example.wgt1986.myapplication.sadapter.MultipleAdapter;
import com.example.wgt1986.myapplication.sadapter.SingleAdapter;
import com.example.wgt1986.myapplication.superadapter.DefaultDiffCallback;
import com.example.wgt1986.myapplication.superadapter.OnItemClickListener;
import com.example.wgt1986.myapplication.superadapter.SimpleMulItemViewType;
import com.example.wgt1986.myapplication.superadapter.SuperAdapter;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.wgt1986.myapplication.model.DataUtils.generateData;

/**
 * Fragment contains RecyclerView.
 * Created by Cheney on 15/12/19.
 */
public class RecyclerViewFragment extends Fragment {

    private int mType;
    private static final String TYPE = "type";

    private RecyclerView recyclerView;

    private SuperAdapter mAdapter;

    private TextView header, footer;

    public static RecyclerViewFragment newInstance(int type) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (getArguments() != null) {
            mType = getArguments().getInt(TYPE, 1);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_header:
                if (!mAdapter.hasHeaderView()) {
                    mAdapter.addHeaderView(header);
                    recyclerView.scrollToPosition(0);
                }
                return true;
            case R.id.action_remove_header:
                if (mAdapter.hasHeaderView())
                    mAdapter.removeHeaderView();
                return true;
            case R.id.action_add_footer:
                if (!mAdapter.hasFooterView())
                    mAdapter.addFooterView(footer);
                return true;
            case R.id.action_remove_footer:
                if (mAdapter.hasFooterView())
                    mAdapter.removeFooterView();
                return true;
            case R.id.action_insert_data:
                if (mType == 2) {
                    mAdapter.add(0, generateData().get(0));
                } else {
                    mAdapter.add(0, Arrays.asList(DataUtils.names).get(0));
                }
                return true;
            case R.id.action_addAll_data:
                if (mType == 2) {
                    mAdapter.addAll(generateData());
                } else {
                    mAdapter.addAll(new ArrayList<>(Arrays.asList(DataUtils.names)));
                }
                return true;
            case R.id.action_replaceAll_data:
                if (mAdapter != null && mType == 2) {
                    mAdapter.replaceAll(generateData());
                }
                return true;
            case R.id.action_diff:
                if (mAdapter != null && mType == 2) {
                    DefaultDiffCallback<MockModel> callback = new DefaultDiffCallback<MockModel>(
                            mAdapter.getData(), generateData()) {
                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            // If your items have unique ids, this method should check their id equality.
                            return true;
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            MockModel oldModel = getOldList().get(oldItemPosition);
                            MockModel newModel = getNewList().get(newItemPosition);

                            return oldModel.getName().equals(newModel.getName()) &&
                                    oldModel.getAge() == newModel.getAge();
                        }
                    };
                    mAdapter.diff(callback);
                }
                return true;
            case R.id.action_clear_data:
                mAdapter.clear();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        header = new TextView(getContext());
        header.setBackgroundColor(Color.YELLOW);
        header.setText("header");
        footer = new TextView(getContext());
        footer.setBackgroundColor(Color.BLUE);
        footer.setText("footer");
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            if (mType == 1) {
                mAdapter = new SingleAdapter(getContext(), new ArrayList<>(Arrays.asList(DataUtils.names)), R.layout.item_type1) {
                    @Override
                    public void diff(DefaultDiffCallback<String> callback) {

                    }
                };
                mAdapter.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(View itemView, int viewType, int position) {
                        Log.d("onItemClick", "" + position);
                    }
                });
                recyclerView.setAdapter(mAdapter);
            } else if (mType == 2) {
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                mAdapter = new MultipleAdapter(getContext(), generateData(),
                        new SimpleMulItemViewType<MockModel>() {
                            @Override
                            public int getItemViewType(int position, MockModel mockModel) {
                                if (position % 2 == 0) {
                                    return 0;
                                }
                                return 1;
                            }

                            @Override
                            public int getLayoutId(int viewType) {
                                if (viewType == 0) {
                                    return R.layout.item_type1;
                                }
                                return R.layout.item_type2;
                            }
                        }) {
                    @Override
                    public void diff(DefaultDiffCallback<MockModel> callback) {

                    }
                };
                recyclerView.setAdapter(mAdapter);
            }
        }
        return view;
    }

}
