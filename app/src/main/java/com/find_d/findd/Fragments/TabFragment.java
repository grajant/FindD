package com.find_d.findd.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.find_d.findd.R;

public class TabFragment extends Fragment {

    private String title;
    private String nameTab1 = "Discotecas";
    private String nameTab2 = "Filtrar";

    private int numberTabs = 0;

    private DiscotecasListFragment listDiscos;
    private FilterFragment filterFragment;

    private Bundle arg;

    public static TabLayout tabLayout;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public static ViewPager viewPager;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //zonesList.add(new Zone4ListFragment());
        /**
         *Inflate tab_layout and setup Views.
         */
        View x = inflater.inflate(R.layout.tab_layout, null);
        tabLayout = x.findViewById(R.id.tabs);
        viewPager = x.findViewById(R.id.view_pager);

        /**
         *Set an Apater for the View Pager
         */
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        /**
         * Now , this is a workaround ,
         * The setupWithViewPager dose't works without the runnable .
         * Maybe a Support Library Bug .
         */

        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        /**
         * Read arguments to determine which zone has to be
         *  initiated
         */
        //option = getArguments().getString("option");
        numberTabs = 2;
        listDiscos = new DiscotecasListFragment();
        filterFragment = new FilterFragment();
        return x;

    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * Return fragment with respect to Position .
         */

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            switch (position){
                case 0:
                    return listDiscos;
                case 1:
                    return filterFragment;
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return nameTab1;
                case 1:
                    return nameTab2;
            }
            return null;
        }
    }

    /** Method to set names to all string variables **/
    private void setStringNames(String _title, String _nameTab1, String _nameTab2, String _nameTab3){
        title = _title;
        nameTab1 = _nameTab1;
        nameTab2 = _nameTab2;
        //nameTab3 = _nameTab3;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}