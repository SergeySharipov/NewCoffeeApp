package ca.coffee_team.newcoffeeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import ca.coffee_team.newcoffeeapp.adapter.CustomersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.adapter.OrdersRecyclerViewAdapter;
import ca.coffee_team.newcoffeeapp.dummy.DummyContent;

public class MainActivity extends AppCompatActivity implements
        CustomersRecyclerViewAdapter.OnListFragmentInteractionListener,
        OrdersRecyclerViewAdapter.OnListFragmentInteractionListener,
        SingleViewFragment.OnSingleViewFragmentCreateListener {
    private static final int CUSTOMERS_FRAGMENT = 0;
    private static final int ORDERS_FRAGMENT = 1;
    private static final int PRODUCTS_FRAGMENT = 2;
    private SingleViewFragment mFirstPageFragment;
    private Fragment mFragment;
    private ViewPager mViewPager;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        initViewPager();
        initBottomNavigation();
        setTitle(CUSTOMERS_FRAGMENT);
        if(mFirstPageFragment==null) {
            mFirstPageFragment = new SingleViewFragment();
            mFragment=new CustomersFragment();
        }
    }

    private void initViewPager() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener());
    }

    private void initBottomNavigation() {
        mBottomNavigationView = findViewById(R.id.navigation);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new OnNavigationItemSelectedListener());
    }

    boolean isFragmentIdCorrect(int fragmentId) {
        switch (fragmentId) {
            case CUSTOMERS_FRAGMENT:
            case ORDERS_FRAGMENT:
            case PRODUCTS_FRAGMENT:
                return true;
            default:
                return false;
        }
    }

    public void setTitle(int fragmentId) {
        switch (fragmentId) {
            case CUSTOMERS_FRAGMENT:
                setTitle("CUSTOMERS_FRAGMENT");
                break;
            case ORDERS_FRAGMENT:
                setTitle("ORDERS_FRAGMENT");
                break;
            case PRODUCTS_FRAGMENT:
                setTitle("PRODUCTS_FRAGMENT");
                break;
        }
    }

    void setSelectedItemId(int fragmentId) {
        setTitle(fragmentId);
        if (isFragmentIdCorrect(fragmentId) && mBottomNavigationView != null) {
            switch (fragmentId) {
                case CUSTOMERS_FRAGMENT:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_customers);
                    break;
                case ORDERS_FRAGMENT:
                    mBottomNavigationView.setSelectedItemId(R.id.navigation_orders);
                    break;
                case PRODUCTS_FRAGMENT:
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
            case CUSTOMERS_FRAGMENT:
                //return new CustomersFragment();
                return mFirstPageFragment;
            case ORDERS_FRAGMENT:
                return OrdersFragment.newInstance(2);
            case PRODUCTS_FRAGMENT:
                return new ProductsFragment();
        }
        return null;//new CustomersFragment();
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {
        if (findViewById(R.id.container) != null
                && getSupportFragmentManager().findFragmentByTag(OrdersFragment.TAG) == null) {
            mFragment = new OrdersFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(OrdersFragment.TAG);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.replace(R.id.container, mFragment, OrdersFragment.TAG).commit();
        } else  if (findViewById(R.id.container) != null){
            mFragment = new ProductsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(ProductsFragment.TAG);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.replace(R.id.container, mFragment, ProductsFragment.TAG).commit();
        }
    }

    @Override
    public void onFirstPageFragmentCreated() {
        if (findViewById(R.id.container) != null
                && getSupportFragmentManager().findFragmentByTag(CustomersFragment.TAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.container, new CustomersFragment(), CustomersFragment.TAG).commit();
        }
//        else {
//            Toast.makeText(this, "replace", Toast.LENGTH_SHORT).show();
//            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//            transaction.replace(R.id.container,mFragment).commit();
//        }
    }

//    @Override
//    public void onBackPressed() {
////        if(getSupportFragmentManager().findFragmentByTag(CustomersFragment.TAG)==null){
////            Toast.makeText(this, "CustomersFragment.TAG)==null", Toast.LENGTH_SHORT).show();
////        }
////        if(getSupportFragmentManager().findFragmentByTag(OrdersFragment.TAG)==null){
////            Toast.makeText(this, "OrdersFragment.TAG)==null", Toast.LENGTH_SHORT).show();
////        }
//    }

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
                    setFragment(CUSTOMERS_FRAGMENT);
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