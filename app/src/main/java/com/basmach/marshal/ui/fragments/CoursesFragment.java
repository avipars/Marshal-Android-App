package com.basmach.marshal.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.basmach.marshal.R;
import com.basmach.marshal.entities.Course;
import com.basmach.marshal.entities.Cycle;
import com.basmach.marshal.entities.MaterialItem;
import com.basmach.marshal.localdb.DBConstants;
import com.basmach.marshal.localdb.interfaces.BackgroundTaskCallBack;
import com.basmach.marshal.ui.ShowAllCoursesActivity;
import com.basmach.marshal.ui.utils.CoursesRecyclerAdapter;
import com.basmach.marshal.ui.utils.InkPageIndicator;
import com.basmach.marshal.ui.utils.ViewPagerAdapter;
import com.basmach.marshal.utils.DateHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CoursesFragment extends Fragment {
//    public ArrayList<String> IMAGES;
//    public ArrayList<Course> COURSES;
    public ArrayList<Course> mCoursesList;

    private ViewPager mViewPager;
    private TimerTask mTimerTask;
    private Timer mTimer;
    private Handler mTimerTaskHandler = new Handler();

    private RecyclerView mRecyclerSoftware;
    private LinearLayoutManager mLinearLayoutManagerSoftware;
    private CoursesRecyclerAdapter mRecyclerAdapterSoftware;
    private Button mBtnShowAllSoftware;

    private RecyclerView mRecyclerCyber;
    private LinearLayoutManager mLinearLayoutManagerCyber;
    private CoursesRecyclerAdapter mRecyclerAdapterCyber;
    private Button mBtnShowAllCyber;

    private RecyclerView mRecyclerIT;
    private LinearLayoutManager mLinearLayoutManagerIT;
    private CoursesRecyclerAdapter mRecyclerAdapterIT;
    private Button mBtnShowAllIT;
    
    private View mRootView;
    private ArrayList<String> mCoursesImages;
    private InkPageIndicator mInkPageIndicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_courses, container, false);

//        IMAGES = new ArrayList<>();
//        IMAGES.add("https://www.hack2secure.com/images/Images/Training/Secure_SDLC.jpg");
//        IMAGES.add("https://www.dunebook.com/wp-content/uploads/2015/07/angular-dunebook.png");
//        IMAGES.add("http://www.wingnity.com/uploads/Courses/1396070428_android-course.png");
//        IMAGES.add("https://academy.mymagic.my/app/uploads/2015/08/FRONTEND_ma-01-700x400-c-default.jpg");
//        IMAGES.add("https://udemy-images.udemy.com/course/750x422/352132_74cf_2.jpg");
//
//        ////////////////////////////////////////////////////////////////////////
//
//        COURSES = new ArrayList<>();
//        Course courseCyber = new Course(getActivity());
//        courseCyber.setImageUrl(IMAGES.get(0));
//        courseCyber.setName("מתודולוגיות פיתוח מאובטח");
//        courseCyber.setIsMooc(false);
//        courseCyber.setDescription("פיתוח מערכות הוא אחת מנקודות הקשר המעניינות ביותר בין עולם הפיתוח לעולם אבטחת המידע. בעוד שאנשי הפיתוח ניצבים מול אתרים טכנולוגיים מורכבים, לו\"ז קשוח ועומס עבודה טבעי, הם נדרשים גם לעמוד לא פעם בפני אתגרים הנוגעים להיבטי אבטחת המידע של המערכת. פיתוח ההשתלמות החל במטרה לספק הכשרה רחבה יותר ומקיפה על יישום תכונות אבטחה ברמות שונות. ההשתלמות מכשירה להבנת האיומים על מערכת באמצעות ניתוח מודל איומים וזיהוי כשלי אבטחה עוד לפני כתיבת הקוד. בנוסף, נושא הגדרת מדיניות מאובטחת בארגון ואכיפתה נכנס במטרה להטמיע את אופן החשיבה המאובטח והצבת תשתית תפיסתית לפיה יעבדו התכניתנים.");
//        courseCyber.setPrerequisites("לחייל יש מקצוע מגן בסייבר או לחייל יש השכלה אקדמאי מוסמך או לחייל יש מקצוע תכניתן וגם ישנה יתרת שירות חצי שנה");
//        courseCyber.setTargetPopulation("תוכניתנים או בוגרי מדעי המחשב או בוגרי הנדסה.");
//        courseCyber.setSyllabus("* הצורך באפליקציה מאובטחת.\r\n* HTML Related Security Issues.\r\n* Input Validation and Output Sanitation.\r\n* Working with Databases.\r\n* User Authentication & Authorization.\r\n* Code Injection Methods.\r\n* Web Services Security. \r\n* אבטחת סודות.\r\n* Security by Design. \r\n* Secure Design Patterns.\r\n* Advanced Security Mechanisms.\r\n");
//        courseCyber.setDurationInDays(5);
//        courseCyber.setDurationInHours(40);
//        courseCyber.setDayTime("בוקר-צהריים");
//        courseCyber.setCourseCode("1951");
//        courseCyber.setComments("יש לעבור מבחן כניסה");
//        Cycle cyberCycle1 = new Cycle(getActivity());
//        cyberCycle1.setStartDate(DateHelper.stringToDate("23/06/16"));
//        cyberCycle1.setEndDate(DateHelper.stringToDate("01/07/16"));
//        cyberCycle1.setName(courseCyber.getName());
//        cyberCycle1.setDescription(courseCyber.getDescription());
//        courseCyber.addCycle(cyberCycle1);
//        Cycle cyberCycle2 = new Cycle(getActivity());
//        cyberCycle2.setStartDate(DateHelper.stringToDate("05/09/16"));
//        cyberCycle2.setEndDate(DateHelper.stringToDate("10/09/16"));
//        cyberCycle2.setName(courseCyber.getName());
//        cyberCycle2.setDescription(courseCyber.getDescription());
//        courseCyber.addCycle(cyberCycle2);
//        Cycle cyberCycle3 = new Cycle(getActivity());
//        cyberCycle3.setStartDate(DateHelper.stringToDate("08/05/17"));
//        cyberCycle3.setEndDate(DateHelper.stringToDate("13/05/17"));
//        cyberCycle3.setName(courseCyber.getName());
//        cyberCycle3.setDescription(courseCyber.getDescription());
//        courseCyber.addCycle(cyberCycle3);
//
//        courseCyber.addCycle(cyberCycle1);
//        courseCyber.addCycle(cyberCycle2);
//        courseCyber.addCycle(cyberCycle3);
//        courseCyber.addCycle(cyberCycle1);
//        courseCyber.addCycle(cyberCycle2);
//        courseCyber.addCycle(cyberCycle3);
//        courseCyber.addCycle(cyberCycle1);
//        courseCyber.addCycle(cyberCycle2);
//        courseCyber.addCycle(cyberCycle3);
//        courseCyber.addCycle(cyberCycle1);
//        courseCyber.addCycle(cyberCycle2);
//        courseCyber.addCycle(cyberCycle3);
//        COURSES.add(courseCyber);
//
//        Course courseAngular = new Course(getActivity());
//        courseAngular.setImageUrl(IMAGES.get(1));
//        courseAngular.setName("Angular JS");
//        courseAngular.setIsMooc(true);
//        Cycle angularCycle = new Cycle(getActivity());
//        angularCycle.setStartDate(DateHelper.stringToDate("17/05/16"));
//        courseAngular.addCycle(angularCycle);
//        COURSES.add(courseAngular);
//
//        Course courseAndroid = new Course(getActivity());
//        courseAndroid.setImageUrl(IMAGES.get(2));
//        courseAndroid.setName("Android Applications Development");
//        courseAndroid.setIsMooc(false);
//        Cycle androidCycle = new Cycle(getActivity());
//        androidCycle.setStartDate(DateHelper.stringToDate("04/09/16"));
//        courseAndroid.addCycle(angularCycle);
//        COURSES.add(courseAndroid);
//
//        Course courseFrontend = new Course(getActivity());
//        courseFrontend.setImageUrl(IMAGES.get(3));
//        courseFrontend.setName("Frontend - HTML5, CSS, JavaScript");
//        courseFrontend.setIsMooc(false);
//        Cycle frontendCycle = new Cycle(getActivity());
//        frontendCycle.setStartDate(DateHelper.stringToDate("27/07/16"));
//        courseFrontend.addCycle(frontendCycle);
//        COURSES.add(courseFrontend);
//
//        Course coursePhotshop = new Course(getActivity());
//        coursePhotshop.setImageUrl(IMAGES.get(4));
//        coursePhotshop.setName("Advanced Photoshop");
//        coursePhotshop.setIsMooc(true);
//        Cycle photoshopCycle = new Cycle(getActivity());
//        photoshopCycle.setStartDate(DateHelper.stringToDate("11/04/16"));
//        coursePhotshop.addCycle(photoshopCycle);
//        COURSES.add(coursePhotshop);

        //////////////////////////////////////////////////////////////////////////////////

        mCoursesImages = new ArrayList<>();
        mCoursesList = new ArrayList<>();

        mViewPager = (ViewPager) mRootView.findViewById(R.id.main_catalog_view_pager);

        Course.getAllInBackground(DBConstants.COL_NAME, Course.class, getActivity(), true,
                new BackgroundTaskCallBack() {
                    @Override
                    public void onSuccess(String result, List<Object> data) {
                        for(Object item:data) {
                            Log.i("GET COURSES "," ITEM: " + ((Course)item).getName());
                            mCoursesList.add((Course)item);
                            mCoursesImages.add(((Course) item).getImageUrl());
                        }
                        showImagesViewPager();
                        showData();
                    }

                    @Override
                    public void onError(String error) {

                    }
                });
        return mRootView;
    }

    private void showImagesViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(), mCoursesImages);
        mViewPager.setAdapter(adapter);
        mInkPageIndicator = (InkPageIndicator) mRootView.findViewById(R.id.main_catalog_indicator);
        mInkPageIndicator.setVisibility(View.VISIBLE);
        mInkPageIndicator.setViewPager(mViewPager);

        startViewPagerTimer();
        stopViewPagerTimerOnTouch();
    }

    private void showData() {
        initializeSoftwareComponents();
        initializeCyberComponents();
        initializeITComponents();
    }

    private void initializeSoftwareComponents() {
        mBtnShowAllSoftware = (Button) mRootView.findViewById(R.id.fragment_courses_software_seeAll);
        mBtnShowAllSoftware.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowAllCoursesActivity.class);
                intent.putParcelableArrayListExtra(ShowAllCoursesActivity.EXTRA_COURSES_LIST,mCoursesList);
                intent.putExtra(ShowAllCoursesActivity.EXTRA_COURSE_TYPE, getResources().getString(R.string.course_type_software));
                startActivity(intent);
            }
        });
        mRecyclerSoftware = (RecyclerView) mRootView.findViewById(R.id.fragment_courses_software_recyclerView);
        mLinearLayoutManagerSoftware = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerSoftware.setLayoutManager(mLinearLayoutManagerSoftware);
        mRecyclerAdapterSoftware = new CoursesRecyclerAdapter(getActivity(),mCoursesList, CoursesRecyclerAdapter.LAYOUT_TYPE_LIST);
        mRecyclerSoftware.setItemAnimator(new DefaultItemAnimator());
        mRecyclerSoftware.setAdapter(mRecyclerAdapterSoftware);
        mRootView.findViewById(R.id.fragment_courses_relativeLayout_software).setVisibility(View.VISIBLE);
    }

    private void initializeCyberComponents() {
        mBtnShowAllCyber = (Button) mRootView.findViewById(R.id.fragment_courses_cyber_seeAll);
        mBtnShowAllCyber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowAllCoursesActivity.class);
                intent.putParcelableArrayListExtra(ShowAllCoursesActivity.EXTRA_COURSES_LIST,mCoursesList);
                intent.putExtra(ShowAllCoursesActivity.EXTRA_COURSE_TYPE, getResources().getString(R.string.course_type_cyber));
                startActivity(intent);
            }
        });
        mRecyclerCyber = (RecyclerView) mRootView.findViewById(R.id.fragment_courses_cyber_recyclerView);
        mLinearLayoutManagerCyber = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerCyber.setLayoutManager(mLinearLayoutManagerCyber);
        mRecyclerAdapterCyber = new CoursesRecyclerAdapter(getActivity(),mCoursesList, CoursesRecyclerAdapter.LAYOUT_TYPE_LIST);
        mRecyclerCyber.setItemAnimator(new DefaultItemAnimator());
        mRecyclerCyber.setAdapter(mRecyclerAdapterCyber);
        mRootView.findViewById(R.id.fragment_courses_relativeLayout_cyber).setVisibility(View.VISIBLE);
    }

    private void initializeITComponents() {
        mBtnShowAllIT = (Button) mRootView.findViewById(R.id.fragment_courses_it_seeAll);
        mBtnShowAllIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ShowAllCoursesActivity.class);
                intent.putParcelableArrayListExtra(ShowAllCoursesActivity.EXTRA_COURSES_LIST, mCoursesList);
                intent.putExtra(ShowAllCoursesActivity.EXTRA_COURSE_TYPE, getResources().getString(R.string.course_type_it));
                startActivity(intent);
            }
        });
        mRecyclerIT = (RecyclerView) mRootView.findViewById(R.id.fragment_courses_it_recyclerView);
        mLinearLayoutManagerIT = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        mRecyclerIT.setLayoutManager(mLinearLayoutManagerIT);
        mRecyclerAdapterIT = new CoursesRecyclerAdapter(getActivity(),mCoursesList, CoursesRecyclerAdapter.LAYOUT_TYPE_LIST);
        mRecyclerIT.setItemAnimator(new DefaultItemAnimator());
        mRecyclerIT.setAdapter(mRecyclerAdapterIT);
        mRootView.findViewById(R.id.fragment_courses_relativeLayout_it).setVisibility(View.VISIBLE);
    }


    private void startViewPagerTimer() {
        mTimer = new Timer();
        mTimerTask = new TimerTask() {
            @Override
            public void run() {
                mTimerTaskHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (mViewPager.getCurrentItem() < mViewPager.getAdapter().getCount() - 1) {
                            mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                        } else {
                            mViewPager.setCurrentItem(0);
                        }
                    }
                });
            }
        };
        mTimer.schedule(mTimerTask, 8000, 8000);
    }

    private void stopViewPagerTimerOnTouch() {
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (mTimer != null) {
                    mTimer.cancel();
                    mTimer = null;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                        }
                        startViewPagerTimer();
                    }
                }, 2000);
                return false;
            }
        });
    }
}