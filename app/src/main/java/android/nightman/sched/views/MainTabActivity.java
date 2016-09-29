package android.nightman.sched.views;

import android.nightman.sched.R;
import android.nightman.sched.views.assignmentview.AssignmentFragment;
import android.nightman.sched.views.classview.ClassesFragment;
import android.nightman.sched.views.util.RxBus;
import android.nightman.sched.views.util.ViewPagerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

public class MainTabActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FloatingActionButton fab;

    private RxBus _rxBus = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        setSupportActionBar(toolbar);
        initViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (getRxBusSingleton().hasObservers()) {
                            getRxBusSingleton().send(viewPager.getAdapter().getPageTitle(position).toString());
                        }
                    }
                });
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public RxBus getRxBusSingleton() {
        if (_rxBus == null) {
            _rxBus = new RxBus();
        }

        return _rxBus;
    }

    private void initViewPager(ViewPager pager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ClassesFragment(), "Classes");
        adapter.addFragment(new AssignmentFragment(), "Assignments");
        pager.setAdapter(adapter);
    }
}
