package com.example.barbets;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

public class HomePage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        
        final ActionBar bar = getActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_TITLE);

        Bundle videoStateB = new Bundle();
        Bundle videoRobotB = new Bundle();
        Bundle stateB = new Bundle();
        Bundle attempt = new Bundle();
        
        videoStateB.putInt("num", 1);
        videoRobotB.putInt("num", 2);
        stateB.putInt("num", 3);
        attempt.putInt("num", 4);
        
        Bundle example1 = new Bundle();
        example1.putInt("num", 5);
        Bundle example2 = new Bundle();
        example2.putInt("num", 6);
        Bundle example3 = new Bundle();
        example3.putInt("num", 7);
        Bundle example4 = new Bundle();
        example4.putInt("num", 8);
        
        bar.addTab(bar.newTab()
                .setText("Home")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, videoStateB)));
        bar.addTab(bar.newTab()
                .setText("Profile")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, videoRobotB)));
        bar.addTab(bar.newTab()
                .setText("Games")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, stateB)));
        bar.addTab(bar.newTab()
                .setText("Wagers")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, attempt)));
        bar.addTab(bar.newTab()
                .setText("Friends")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, example1)));
        bar.addTab(bar.newTab()
                .setText("Chats")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, example2)));
        bar.addTab(bar.newTab()
                .setText("Info")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, example3)));
        bar.addTab(bar.newTab()
                .setText("Unlockables")
                .setTabListener(new TabListener<FragmentStack.CountingFragment>(
                        this, "activity_main_viewer", FragmentStack.CountingFragment.class, example4)));




        if (savedInstanceState != null) {
            bar.setSelectedNavigationItem(savedInstanceState.getInt("tab", 0));
        }
        
 
        
    }
    
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab", getActionBar().getSelectedNavigationIndex());
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_page, menu);
        return true;
    }
    
    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;
        private final Bundle mArgs;
        private Fragment mFragment;

        public TabListener(Activity activity, String tag, Class<T> clz) {
            this(activity, tag, clz, null);
        }

        public TabListener(Activity activity, String tag, Class<T> clz, Bundle args) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
            mArgs = args;

            // Check to see if we already have a fragment for this tab, probably
            // from a previously saved state.  If so, deactivate it, because our
            // initial state is that a tab isn't shown.
            mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
            if (mFragment != null && !mFragment.isDetached()) {
                FragmentTransaction ft = mActivity.getFragmentManager().beginTransaction();
                ft.detach(mFragment);
                ft.commit();
            }
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            if (mFragment == null) {
                mFragment = Fragment.instantiate(mActivity, mClass.getName(), mArgs);
                ft.add(android.R.id.content, mFragment, mTag);
            } else {
                ft.attach(mFragment);
            }
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            if (mFragment != null) {
                ft.detach(mFragment);
            }
        }

        public void onTabReselected(Tab tab, FragmentTransaction ft) {
            Toast.makeText(mActivity, "Reselected!", Toast.LENGTH_SHORT).show();
        }
    }

}
