package ca.coffee_team.newcoffeeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ca.coffee_team.newcoffeeapp.fragment.CustomerFragment;
import ca.coffee_team.newcoffeeapp.fragment.CustomersFragment;
import ca.coffee_team.newcoffeeapp.fragment.FragmentContainer;
import ca.coffee_team.newcoffeeapp.fragment.OnListItemClickListener;
import ca.coffee_team.newcoffeeapp.fragment.OrderFragment;
import ca.coffee_team.newcoffeeapp.fragment.OrdersFragment;
import ca.coffee_team.newcoffeeapp.fragment.ProductFragment;
import ca.coffee_team.newcoffeeapp.fragment.ProductsFragment;
import ca.coffee_team.newcoffeeapp.fragment.StandardFragment;
import ca.coffee_team.newcoffeeapp.model.Customer;
import ca.coffee_team.newcoffeeapp.model.ModelObject;
import ca.coffee_team.newcoffeeapp.model.Order;
import ca.coffee_team.newcoffeeapp.model.Product;
import ca.coffee_team.newcoffeeapp.util.StackNavigationPageSelected;

public class MainActivity extends AppCompatActivity implements
        BottomNavigationView.OnNavigationItemSelectedListener,
        FragmentContainer.OnFragmentContainerManageListener,
        OnListItemClickListener, BottomNavigationView.OnNavigationItemReselectedListener {

    private static final int FIRST_PAGE = 0;
    private static final int SECOND_PAGE = 1;
    private static final int THIRD_PAGE = 2;
    private static final String STACK = "ca.coffee_team.newcoffeeapp.STACK";

    private StackNavigationPageSelected mStackNavigationPageSelected;
    private FragmentContainer mFragmentContainer1;
    private FragmentContainer mFragmentContainer2;
    private FragmentContainer mFragmentContainer3;
    private BottomNavigationView mBottomNavigationView;
    private ActionBar mActionBar;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActionBar = getSupportActionBar();

        mStackNavigationPageSelected = new StackNavigationPageSelected(SectionsPagerAdapter.countSections);

        if (savedInstanceState != null) {
            mStackNavigationPageSelected.fromIntArrayList(savedInstanceState.getIntegerArrayList(STACK));

            mFragmentContainer1 = (FragmentContainer) getSupportFragmentManager().getFragment(savedInstanceState, FIRST_PAGE + "");
            mFragmentContainer2 = (FragmentContainer) getSupportFragmentManager().getFragment(savedInstanceState, SECOND_PAGE + "");
            mFragmentContainer3 = (FragmentContainer) getSupportFragmentManager().getFragment(savedInstanceState, THIRD_PAGE + "");
        } else {
            mStackNavigationPageSelected.pushPage(FIRST_PAGE);
        }
        if (mFragmentContainer1 == null)
            mFragmentContainer1 = FragmentContainer.newInstance(FIRST_PAGE);
        if (mFragmentContainer2 == null)
            mFragmentContainer2 = FragmentContainer.newInstance(SECOND_PAGE);
        if (mFragmentContainer3 == null)
            mFragmentContainer3 = FragmentContainer.newInstance(THIRD_PAGE);

        initBottomNavigationView();
        initViewPager();
    }

    @Override
    public StandardFragment getFirstFragment(int position) {
        switch (position) {
            case FIRST_PAGE:
                return new CustomersFragment();
            case SECOND_PAGE:
                return new OrdersFragment();
            case THIRD_PAGE:
                return new ProductsFragment();
        }
        return new CustomersFragment();
    }

    @Override
    public String getFirstFragmentTitle(int position) {
        switch (position) {
            case FIRST_PAGE:
                return CustomersFragment.TITLE;
            case SECOND_PAGE:
                return OrdersFragment.TITLE;
            case THIRD_PAGE:
                return ProductsFragment.TITLE;
        }
        return CustomersFragment.TITLE;
    }

    @Override
    public void onListItemClick(ModelObject item) {
        StandardFragment standardFragment = new ProductFragment();

        if (item instanceof Customer)
            standardFragment = CustomerFragment.newInstance(item.getId());
        else if (item instanceof Order)
            standardFragment = OrderFragment.newInstance(item.getId());
        else if (item instanceof Product)
            standardFragment = ProductFragment.newInstance(item.getId());

        switch (getSelectedBottomNavigationItemPosition()) {
            case FIRST_PAGE:
                mFragmentContainer1.showNextFragment(standardFragment);
                break;
            case SECOND_PAGE:
                mFragmentContainer2.showNextFragment(standardFragment);
                break;
            case THIRD_PAGE:
                mFragmentContainer3.showNextFragment(standardFragment);
                break;
        }
    }

    @Override
    public void updateActionBar() {
        setActionBarTitle(getSelectedBottomNavigationItemPosition());
        setBackButtonStatus(getSelectedBottomNavigationItemPosition());
    }

    public void updateActionBar(int position) {
        setActionBarTitle(position);
        setBackButtonStatus(position);
    }

    private void initViewPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(sectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener());
        mViewPager.setOffscreenPageLimit(SectionsPagerAdapter.countSections);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntegerArrayList(STACK, mStackNavigationPageSelected.toIntArrayList());

        if (mFragmentContainer1.isAdded())
            getSupportFragmentManager().putFragment(outState, FIRST_PAGE + "", mFragmentContainer1);
        if (mFragmentContainer2.isAdded())
            getSupportFragmentManager().putFragment(outState, SECOND_PAGE + "", mFragmentContainer2);
        if (mFragmentContainer3.isAdded())
            getSupportFragmentManager().putFragment(outState, THIRD_PAGE + "", mFragmentContainer3);
    }


    private void initBottomNavigationView() {
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);
        mBottomNavigationView.setOnNavigationItemReselectedListener(this);
    }

    private void setSelectedBottomNavigationItem(int position) {
        mStackNavigationPageSelected.pushPage(position);
        updateActionBar(position);
        Menu menu = mBottomNavigationView.getMenu();
        switch (position) {
            case FIRST_PAGE:
                menu.findItem(R.id.navigation_first_page).setChecked(true);
                break;
            case SECOND_PAGE:
                menu.findItem(R.id.navigation_second_page).setChecked(true);
                break;
            case THIRD_PAGE:
                menu.findItem(R.id.navigation_third_page).setChecked(true);
                break;
        }
    }

    private void selectBottomNavigationItem(int position) {
        switch (position) {
            case FIRST_PAGE:
                mBottomNavigationView.setSelectedItemId(R.id.navigation_first_page);
                break;
            case SECOND_PAGE:
                mBottomNavigationView.setSelectedItemId(R.id.navigation_second_page);
                break;
            case THIRD_PAGE:
                mBottomNavigationView.setSelectedItemId(R.id.navigation_third_page);
                break;
        }
        updateActionBar(position);
    }

    private int getSelectedBottomNavigationItemPosition() {
        return getSelectedBottomNavigationItemPosition(mBottomNavigationView.getSelectedItemId());
    }

    private int getSelectedBottomNavigationItemPosition(int itemId) {
        switch (itemId) {
            case R.id.navigation_first_page:
                return FIRST_PAGE;
            case R.id.navigation_second_page:
                return SECOND_PAGE;
            case R.id.navigation_third_page:
                return THIRD_PAGE;
        }
        return 0;
    }

    private FragmentContainer getFragmentContainer(int position) {
        switch (position) {
            case FIRST_PAGE:
                return mFragmentContainer1;
            case SECOND_PAGE:
                return mFragmentContainer2;
            case THIRD_PAGE:
                return mFragmentContainer3;
        }
        return mFragmentContainer1;
    }

    @Override
    public void onBackPressed() {
        int position = getSelectedBottomNavigationItemPosition();
        FragmentContainer fragmentContainer = getFragmentContainer(position);

        if (!fragmentContainer.isBackStackEmpty()) {
            fragmentContainer.showPreviousFragment();
        } else if (mStackNavigationPageSelected.size() > 1) {
            int previousPage = mStackNavigationPageSelected.popPage();
            selectBottomNavigationItem(previousPage);
        } else {
            super.onBackPressed();
        }

        updateActionBar();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int page = getSelectedBottomNavigationItemPosition(item.getItemId());
        mViewPager.setCurrentItem(page);
        updateActionBar(page);
        return false;
    }

    @Override
    public void onNavigationItemReselected(@NonNull MenuItem item) {
        int page = getSelectedBottomNavigationItemPosition(item.getItemId());
        mViewPager.setCurrentItem(page);
        getFragmentContainer(page).showFirstFragment();
        setBackButtonStatus(false);
        setActionBarTitle(page);
    }

    public void setActionBarTitle(int position) {
        String title = getFragmentContainer(position).getTitle();
        setTitle(title);
    }

    public void setBackButtonStatus(boolean status) {
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(status);
            mActionBar.setHomeButtonEnabled(status);
        }
    }

    public void setBackButtonStatus(int position) {
        switch (position) {
            case FIRST_PAGE:
                setBackButtonStatus(mFragmentContainer1.isBackButtonStatus());
                break;
            case SECOND_PAGE:
                setBackButtonStatus(mFragmentContainer2.isBackButtonStatus());
                break;
            case THIRD_PAGE:
                setBackButtonStatus(mFragmentContainer3.isBackButtonStatus());
                break;
        }
    }

    public Fragment getPageFragmentByPosition(int position) {
        switch (position) {
            case FIRST_PAGE:
                return mFragmentContainer1;
            case SECOND_PAGE:
                return mFragmentContainer2;
            case THIRD_PAGE:
                return mFragmentContainer3;
        }
        return new ProductsFragment();
    }

    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private static final int countSections = 3; // Show 3 total pages.

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getPageFragmentByPosition(position);
        }

        @Override
        public int getCount() {
            return countSections;
        }
    }

    private class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setSelectedBottomNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}