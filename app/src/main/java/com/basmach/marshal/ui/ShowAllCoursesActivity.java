package com.basmach.marshal.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.basmach.marshal.Constants;
import com.basmach.marshal.R;
import com.basmach.marshal.entities.Course;
import com.basmach.marshal.ui.adapters.CoursesRecyclerAdapter;
import com.basmach.marshal.ui.utils.LocaleUtils;
import com.basmach.marshal.ui.utils.ThemeUtils;

import java.util.ArrayList;

public class ShowAllCoursesActivity extends AppCompatActivity {

    private BroadcastReceiver mAdaptersBroadcastReceiver;

    RecyclerView mRecyclerView;
    GridLayoutManager mGridLayoutManager;
    CoursesRecyclerAdapter mAdapter;
    Toolbar mToolbar;

    String mCoursesType;
    ArrayList<Course> mCourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.updateTheme(this);
        super.onCreate(savedInstanceState);
        LocaleUtils.updateLocale(this);
        setContentView(R.layout.activity_show_all_courses);

        mCoursesType = getIntent().getStringExtra(Constants.EXTRA_COURSE_TYPE);
        mCourses = getIntent().getParcelableArrayListExtra(Constants.EXTRA_COURSES_LIST);

        mToolbar = (Toolbar) findViewById(R.id.showAllCourses_activity_toolbar);

        if (mCoursesType != null && mToolbar != null) mToolbar.setTitle(mCoursesType);

        setSupportActionBar(mToolbar);

        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if (mCourses != null) {
            if (mCourses.size() > 0) {
                mRecyclerView = (RecyclerView) findViewById(R.id.showAllCourses_activity_recyclerView);
                mGridLayoutManager = new GridLayoutManager(ShowAllCoursesActivity.this, 3);
                mRecyclerView.setLayoutManager(mGridLayoutManager);
                mRecyclerView.setItemAnimator(new DefaultItemAnimator());
                mRecyclerView.setHasFixedSize(true);
                mAdapter = new CoursesRecyclerAdapter(ShowAllCoursesActivity.this, mCourses,
                        CoursesRecyclerAdapter.LAYOUT_TYPE_GRID);
                mRecyclerView.setAdapter(mAdapter);
            }
        }
        mAdaptersBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                mAdapter.notifyDataSetChanged();
            }
        };

        registerReceiver(mAdaptersBroadcastReceiver, new IntentFilter(CoursesRecyclerAdapter.ACTION_ITEM_DATA_CHANGED));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        LocaleUtils.updateLocale(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mAdaptersBroadcastReceiver);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.RC_COURSE_ACTIVITY) {
            if (resultCode == MainActivity.RESULT_SHOW_COURSE_MATERIALS) {

                String courseCode = data.getStringExtra(Constants.EXTRA_COURSE_CODE);

                if (courseCode != null && !(courseCode.equals(""))) {

                    Intent intent = new Intent(MainActivity.ACTION_SHOW_COURSE_MATERIALS);
                    intent.putExtra(Constants.EXTRA_COURSE_CODE, courseCode);
                    setResult(MainActivity.RESULT_SHOW_COURSE_MATERIALS, intent);
                    finish();
                }
            }
        }
    }
}
