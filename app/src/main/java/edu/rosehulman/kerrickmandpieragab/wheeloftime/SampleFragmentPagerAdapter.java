package edu.rosehulman.kerrickmandpieragab.wheeloftime;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by kerrickm on 1/16/2017.
 */

public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab 1", "Tab 2", "Tab 3" };

    public SampleFragmentPagerAdapter(FragmentManager fm, Context c) {
        super(fm);
        context = c;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
