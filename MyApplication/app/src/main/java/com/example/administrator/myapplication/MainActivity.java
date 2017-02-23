package com.example.administrator.myapplication;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LinearLayout container;
    private ViewPager viewpager;
    private VpAdapter adapter;
    public List<View> viewlist;

    private int screenWidth;
    private String[] data = {"第一页", "第二页", "第三页", "第四页", "第五页", "第六页"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        initdate();
        initview();

    }

    private void initview() {
        container = (LinearLayout) this.findViewById(R.id.container);
        viewpager = (ViewPager) this.findViewById(R.id.viewpager);
        viewpager.setOffscreenPageLimit(3); // viewpager缓存页数
        viewpager.setPageMargin(30); // 设置各页面的间距

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                screenWidth / 3, ViewPager.LayoutParams.WRAP_CONTENT);
        viewpager.setLayoutParams(params);

        adapter = new VpAdapter();
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(1);

        // 将父节点Layout事件分发给viewpager，否则只能滑动中间的一个view对象
        container.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return viewpager.dispatchTouchEvent(event);
            }
        });

    }

    private void initdate() {
        viewlist = new ArrayList<View>();
        int size = data.length;
        for (int i = 0; i < size; i++) {
            View view = View.inflate(MainActivity.this, R.layout.viewitem, null);
            TextView textView = (TextView) view.findViewById(R.id.textView1);
            textView.setText(data[i]);
            viewlist.add(view);
        }

    }

    public class VpAdapter extends PagerAdapter {


        @Override
        public int getCount() {
            return viewlist.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewlist.get(position), 0);
            return viewlist.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
			System.out.println("11111111111");
        }
    }

}
