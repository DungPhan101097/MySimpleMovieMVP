package com.example.lap10715.mysimpleappmovie.View;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.lap10715.mysimpleappmovie.R;
import com.example.lap10715.mysimpleappmovie.View.adapters.MyPagerAdapter;
import com.example.lap10715.mysimpleappmovie.Presenter.eventbus.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tbTop = findViewById(R.id.tb_top);
        setSupportActionBar(tbTop);

        ViewPager vpTabs = findViewById(R.id.vp_tabs);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        vpTabs.setAdapter(pagerAdapter);

        vpTabs.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        EventBus.getDefault().post(0);
                        break;
                    case 1:
                        EventBus.getDefault().post(1);
                        break;
                    case 2:
                        EventBus.getDefault().post(2);
                        break;
                    case 3:
                        EventBus.getDefault().post(3);
                        break;
                        default:
                            EventBus.getDefault().post(0);

                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setupWithViewPager(vpTabs);
        EventBus.getDefault().postSticky(0);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setMessage(MessageEvent event){
        Toast.makeText(this, event.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
