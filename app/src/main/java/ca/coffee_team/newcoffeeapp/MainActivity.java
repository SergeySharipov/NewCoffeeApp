package ca.coffee_team.newcoffeeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import ca.coffee_team.newcoffeeapp.dummy.DummyContent;
import ca.coffee_team.newcoffeeapp.model.Customer;

public class MainActivity extends AppCompatActivity implements
        CustomersFragment.OnListFragmentInteractionListener,
        OrdersFragment.OnListFragmentInteractionListener,
        ProductsFragment.OnListFragmentInteractionListener,
        SingleViewFragment.OnSingleViewFragmentCreateListener {
    private static final int FIRST_PAGE_FRAGMENT = 0;
    private static final int ORDERS_FRAGMENT = 1;
    private static final int PRODUCTS_FRAGMENT = 2;
    private String titleFirstPage = "";
    private SingleViewFragment mFirstPageFragment;
    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;
    private ActionBar mActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        mActionBar = getSupportActionBar();

        if (mFirstPageFragment == null) {
            mFirstPageFragment = new SingleViewFragment();
        }

        initViewPager();
        initBottomNavigation();
        setTitle(FIRST_PAGE_FRAGMENT);
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDisplayHomeAsUpEnabled(true);
    }

    void setDisplayHomeAsUpEnabled(boolean status){
        if(getTitle().equals(CustomersFragment.TAG))
            status = false;
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(status);
            mActionBar.setHomeButtonEnabled(status);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void initViewPager() {
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(sectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener());
        mViewPager.setOffscreenPageLimit(SectionsPagerAdapter.countSections);
    }

    private void initBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener());
    }

    boolean isFragmentIdCorrect(int fragmentId) {
        switch (fragmentId) {
            case FIRST_PAGE_FRAGMENT:
            case ORDERS_FRAGMENT:
            case PRODUCTS_FRAGMENT:
                return true;
            default:
                return false;
        }
    }

    public void setTitle(int fragmentId) {
        switch (fragmentId) {
            case FIRST_PAGE_FRAGMENT:
                super.setTitle(titleFirstPage);
                break;
            case ORDERS_FRAGMENT:
                super.setTitle(OrdersFragment.TAG);
                break;
            case PRODUCTS_FRAGMENT:
                super.setTitle(ProductsFragment.TAG);
                break;
        }
    }

    void setSelectedItemId(int fragmentId) {
        setTitle(fragmentId);
        if (isFragmentIdCorrect(fragmentId) && mBottomNavigationView != null) {
            switch (fragmentId) {
                case FIRST_PAGE_FRAGMENT:
                    setDisplayHomeAsUpEnabled(true);
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_customers);
                    break;
                case ORDERS_FRAGMENT:
                    setDisplayHomeAsUpEnabled(false);
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_orders);
                    break;
                case PRODUCTS_FRAGMENT:
                    setDisplayHomeAsUpEnabled(false);
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_products);
                    break;
            }
        }
    }

    void setFragment(int fragmentId) {
        setTitle(fragmentId);
        if (isFragmentIdCorrect(fragmentId) && mViewPager != null) {
            mViewPager.setCurrentItem(fragmentId);
        }
    }

    public Fragment getFragmentById(int fragmentId) {
        setTitle(fragmentId);
        switch (fragmentId) {
            case FIRST_PAGE_FRAGMENT:
                return mFirstPageFragment;
            case ORDERS_FRAGMENT:
                return OrdersFragment.newInstance(2);
            case PRODUCTS_FRAGMENT:
                return new ProductsFragment();
        }
        return new ProductsFragment();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        if (findViewById(R.id.container) != null
                && mBottomNavigationView.getSelectedItemId() == R.id.navigation_customers) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);

            if (getSupportFragmentManager().findFragmentByTag(OrdersFragment.TAG) == null) {
                setTitle(ORDERS_FRAGMENT);
                titleFirstPage = OrdersFragment.TAG;
                transaction.addToBackStack(OrdersFragment.TAG);
                transaction.replace(R.id.container, new OrdersFragment(), OrdersFragment.TAG).commit();
            } else if (getSupportFragmentManager().findFragmentByTag(ProductsFragment.TAG) == null) {
                setTitle(PRODUCTS_FRAGMENT);
                titleFirstPage = ProductsFragment.TAG;
                transaction.addToBackStack(ProductsFragment.TAG);
                transaction.replace(R.id.container, new ProductsFragment(), ProductsFragment.TAG).commit();
            }
            setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onResumeFragment(String tag) {
        super.setTitle(tag);
        titleFirstPage = tag;
        setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (mBottomNavigationView.getSelectedItemId() == R.id.navigation_customers
                && mFirstPageFragment != null) {
            super.onBackPressed();
            setDisplayHomeAsUpEnabled(true);
        } else
            finish();
    }

    @Override
    public void onFirstPageFragmentCreated() {
        if (findViewById(R.id.container) != null
                && getSupportFragmentManager().findFragmentByTag(CustomersFragment.TAG) == null) {
            mFirstPageFragment.setCurrentFragmentId(FIRST_PAGE_FRAGMENT);
            titleFirstPage = CustomersFragment.TAG;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, new CustomersFragment(), CustomersFragment.TAG).commit();
        }
    }

    private class SectionsPagerAdapter extends FragmentStatePagerAdapter {
        private static final int countSections = 3; // Show 3 total pages.

        SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragmentById(position);
        }

        @Override
        public int getCount() {
            return countSections;
        }
    }

    class OnNavigationItemSelectedListener
            implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_customers:
                    setFragment(FIRST_PAGE_FRAGMENT);
                    return true;
                case R.id.navigation_orders:
                    setFragment(ORDERS_FRAGMENT);
                    return true;
                case R.id.navigation_products:
                    setFragment(PRODUCTS_FRAGMENT);
                    return true;
            }
            return false;
        }
    }

    class OnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setSelectedItemId(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}