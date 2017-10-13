package br.com.edu.ifsc.ajinhocompatas.view.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wilson on 13/10/2017.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTituloList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    @Override
    public Fragment getItem(int position) {
        return this.mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return this.mFragmentList.size();
    }

    public void addFragment(Fragment aFragment, String aTitulo) {
        mFragmentList.add(aFragment);
        mFragmentTituloList.add(aTitulo);
    }

    @Override
    public CharSequence getPageTitle(int mPosition) {
        return this.mFragmentTituloList.get(mPosition);
    }
}
