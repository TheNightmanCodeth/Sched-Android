package android.nightman.sched.views;

import android.nightman.sched.R;
import android.nightman.sched.views.classview.ClassesFragment;
import android.nightman.sched.views.util.ViewPagerAdapter;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainTabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(toolbar);
        initViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClassesFragment(), "Classes");
        adapter.addFragment(new ClassesFragment(), "Assignments");
        pager.setAdapter(adapter);
    }
}
