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
import android.view.MenuInflater;
import android.view.MenuItem;

import ca.coffee_team.newcoffeeapp.dialog.AddEditCustomerDialog;
import ca.coffee_team.newcoffeeapp.dialog.AddEditOrderDialog;
import ca.coffee_team.newcoffeeapp.dialog.AddEditProductDialog;
import ca.coffee_team.newcoffeeapp.fragment.CustomerFragment;
import ca.coffee_team.newcoffeeapp.fragment.CustomersFragment;
import ca.coffee_team.newcoffeeapp.fragment.FragmentContainer;
import ca.coffee_team.newcoffeeapp.fragment.OnItemClickListener;
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
        OnListItemClickListener, BottomNavigationView.OnNavigationItemReselectedListener,
        OnItemClickListener {

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
    private Menu mOptionsMenu;

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
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_action_bar_menu, menu);
        mOptionsMenu = menu;

        setEditStatus(getSelectedBottomNavigationItemPosition());
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mOptionsMenu = menu;
        return super.onPrepareOptionsMenu(menu);
    }

    private void setEditStatus(boolean editStatus) {
        if (mOptionsMenu != null) {
            mOptionsMenu.getItem(0).setVisible(!editStatus);
            mOptionsMenu.getItem(1).setVisible(editStatus);
            onPrepareOptionsMenu(mOptionsMenu);
        }
    }

    public void setEditStatus(int position) {
        switch (position) {
            case FIRST_PAGE:
                setEditStatus(mFragmentContainer1.isEditStatus());
                break;
            case SECOND_PAGE:
                setEditStatus(mFragmentContainer2.isEditStatus());
                break;
            case THIRD_PAGE:
                setEditStatus(mFragmentContainer3.isEditStatus());
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                getAddDialog(getSelectedBottomNavigationItemPosition());
                return true;
            case R.id.edit_item:
                getEditDialog(getSelectedBottomNavigationItemPosition());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void getEditDialog(int position) {
        String id = null;
        switch (position) {
            case FIRST_PAGE:
                id = mFragmentContainer1.getObjectId();
                if (id != null && mFragmentContainer1.getBackStackCount() <= 2) {
                    AddEditCustomerDialog addEditCustomerDialog = AddEditCustomerDialog.newInstance(id);
                    addEditCustomerDialog.show(getSupportFragmentManager(), "AddEditCustomerDialog");
                } else if (id != null) {
                    AddEditOrderDialog addEditOrderDialog = AddEditOrderDialog.newInstance(id);
                    addEditOrderDialog.show(getSupportFragmentManager(), "AddEditOrderDialog");
                }
                break;
            case SECOND_PAGE:
                id = mFragmentContainer2.getObjectId();
                AddEditOrderDialog addEditOrderDialog = AddEditOrderDialog.newInstance(id);
                addEditOrderDialog.show(getSupportFragmentManager(), "AddEditOrderDialog");
                break;
            case THIRD_PAGE:
                id = mFragmentContainer3.getObjectId();
                if (id != null) {
                    AddEditProductDialog addEditProductDialog = AddEditProductDialog.newInstance(id);
                    addEditProductDialog.show(getSupportFragmentManager(), "AddEditProductDialog");
                }
                break;
        }
    }

    public void getAddDialog(int position) {
        String id = null;
        switch (position) {
            case FIRST_PAGE:
                id = mFragmentContainer1.getObjectId();
                if (mFragmentContainer1.getBackStackCount() <= 2) {
                    AddEditCustomerDialog addEditCustomerDialog = new AddEditCustomerDialog();
                    addEditCustomerDialog.show(getSupportFragmentManager(), "AddEditCustomerDialog");
                }else if (id != null) {
                    AddEditOrderDialog addEditOrderDialog = AddEditOrderDialog.newInstanceFor(id);
                    addEditOrderDialog.show(getSupportFragmentManager(), "AddEditOrderDialog");
                }
                break;
            case SECOND_PAGE:
                id = mFragmentContainer2.getObjectId();
                if (id != null) {
                    AddEditOrderDialog addEditOrderDialog = AddEditOrderDialog.newInstanceFor(id);
                    addEditOrderDialog.show(getSupportFragmentManager(), "AddEditOrderDialog");
                } else {
                    AddEditOrderDialog addEditOrderDialog = new AddEditOrderDialog();
                    addEditOrderDialog.show(getSupportFragmentManager(), "AddEditOrderDialog");
                }
                break;
            case THIRD_PAGE:
                AddEditProductDialog addEditProductDialog = new AddEditProductDialog();
                addEditProductDialog.show(getSupportFragmentManager(), "AddEditProductDialog");
                break;
        }
    }

    @Override
    public void onItemClick(ModelObject item) {
        if (item instanceof Customer) {
            StandardFragment standardFragment;

            standardFragment = OrdersFragment.newInstance(item.getId());

            switch (getSelectedBottomNavigationItemPosition()) {
                case FIRST_PAGE:
                    mFragmentContainer1.showNextFragment(standardFragment, item.getId());
                    break;
            }
        }
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
                mFragmentContainer1.showNextFragment(standardFragment, item.getId());
                break;
            case SECOND_PAGE:
                mFragmentContainer2.showNextFragment(standardFragment, item.getId());
                break;
            case THIRD_PAGE:
                mFragmentContainer3.showNextFragment(standardFragment, item.getId());
                break;
        }
    }

    @Override
    public void updateActionBar() {
        setActionBarTitle(getSelectedBottomNavigationItemPosition());
        setBackButtonStatus(getSelectedBottomNavigationItemPosition());
        setEditStatus(getSelectedBottomNavigationItemPosition());
    }

    public void updateActionBar(int position) {
        setActionBarTitle(position);
        setBackButtonStatus(position);
        setEditStatus(position);
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
        setEditStatus(false);
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