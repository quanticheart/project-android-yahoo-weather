/*
 *
 *  *                                     /@
 *  *                      __        __   /\/
 *  *                     /==\      /  \_/\/
 *  *                   /======\    \/\__ \__
 *  *                 /==/\  /\==\    /\_|__ \
 *  *              /==/    ||    \=\ / / / /_/
 *  *            /=/    /\ || /\   \=\/ /
 *  *         /===/   /   \||/   \   \===\
 *  *       /===/   /_________________ \===\
 *  *    /====/   / |                /  \====\
 *  *  /====/   /   |  _________    /      \===\
 *  *  /==/   /     | /   /  \ / / /         /===/
 *  * |===| /       |/   /____/ / /         /===/
 *  *  \==\             /\   / / /          /===/
 *  *  \===\__    \    /  \ / / /   /      /===/   ____                    __  _         __  __                __
 *  *    \==\ \    \\ /____/   /_\ //     /===/   / __ \__  ______  ____ _/ /_(_)____   / / / /__  ____ ______/ /_
 *  *    \===\ \   \\\\\\\/   ///////     /===/  / / / / / / / __ \/ __ `/ __/ / ___/  / /_/ / _ \/ __ `/ ___/ __/
 *  *      \==\/     \\\\/ / //////       /==/  / /_/ / /_/ / / / / /_/ / /_/ / /__   / __  /  __/ /_/ / /  / /_
 *  *      \==\     _ \\/ / /////        |==/   \___\_\__,_/_/ /_/\__,_/\__/_/\___/  /_/ /_/\___/\__,_/_/   \__/
 *  *        \==\  / \ / / ///          /===/
 *  *        \==\ /   / / /________/    /==/
 *  *          \==\  /               | /==/
 *  *          \=\  /________________|/=/
 *  *            \==\     _____     /==/
 *  *           / \===\   \   /   /===/
 *  *          / / /\===\  \_/  /===/
 *  *         / / /   \====\ /====/
 *  *        / / /      \===|===/
 *  *        |/_/         \===/
 *  *                       =
 *  *
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 8:53:5 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.BaseProject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import quanticheart.com.baseproject.BaseProject.Adapters.ViewPager.PageConfig;
import quanticheart.com.baseproject.BaseProject.Adapters.ViewPager.ViewPagerAdapter;
import quanticheart.com.baseproject.BaseProject.BroadCast.MyReceiver;
import quanticheart.com.baseproject.BaseProject.BroadCast.SystemUtil;
import quanticheart.com.baseproject.BaseProject.Helpers.WizardHelper;
import quanticheart.com.baseproject.Helpers.BottomSheetCreate;
import quanticheart.com.baseproject.R;
import quanticheart.com.baseproject.Utils.GlideUtil;

public abstract class BaseActivity extends AppCompatActivity implements SystemUtil.ConnectionVerify {

    //init
    public Activity activity;

    //For mensagens
    private static Snackbar snackbar = null;

    //add container view
    private FrameLayout container;

    //BottomSheet
    public FrameLayout bottom_sheet;
    public BottomSheetBehavior mBehavior;

    //
    private SwipeRefreshLayout refresh;

    public NestedScrollView nestedScrollView;

    //==============================================================================================
    //
    // onCreate
    //
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baseproject);

        initVars();
        initActions();

    }

    //==============================================================================================
    //
    // Set Container in FrameLayout
    //
    //==============================================================================================

    public void setBaseContantView(int layout) {
        View view = getLayoutInflater().inflate(layout, null);
        container.addView(view);
    }

    //==============================================================================================
    //
    // Init Methods
    //
    //==============================================================================================

    private void initVars() {

        //init
        activity = BaseActivity.this;

        //add container view
        container = findViewById(R.id.container);

        //Toolbar
        toolbarContainer = findViewById(R.id.toolbarContainer);

        //BottomSheet
        bottom_sheet = findViewById(R.id.bottom_sheet);

        //DrawerLayout
        drawerLayout = findViewById(R.id.baseDrawerLayoutID);
        blockDrawer();

        //BootonNavigationView
        bottomNavigationView = findViewById(R.id.bottomNavigation);

        //SwipeRefreshLayout
        refresh = findViewById(R.id.refresh);
        blockRefreshLayout();
        //NestedScrool
//        nestedScrollView = findViewById(R.id.scrool);

    }

    private void initActions() {
        //For BottomSheet
        mBehavior = BottomSheetBehavior.from(bottom_sheet);
    }

    //==============================================================================================
    //
    // init Wizard
    //
    //==============================================================================================

    public void setWizard(Class classAfterWizard) {
        new WizardHelper(activity);
    }

    //==============================================================================================
    //
    // Toolbar Setup
    //
    //==============================================================================================

    //ToolBar
    private AppBarLayout appBarLayout;
    private ConstraintLayout toolbarContainer;
    private Toolbar toolbar;
    private ImageView toolbarImg;
    private TextView toolbarText;

    public void showToolbar() {
        setupActionBar();
    }

    public void showToolbar(String titleToolbar) {
        setupActionBar();
        setToolbarText(titleToolbar);
    }

    public void setToolbarImage(int drawableForToolbar) {
        setupActionBar();
        GlideUtil.initGlide(activity, drawableForToolbar, toolbarImg);
    }

    public void setBackButtonInToolbar() {
        setupActionBar();
        setupBackToolbar();
    }

    public void setBackButtonInToolbar(String titleToolbar) {
        setupActionBar();
        setToolbarText(titleToolbar);
        setupBackToolbar();
    }

    public void notAnimatorLayout() {
        if (toolbarContainer != null) {
            AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) toolbarContainer.getLayoutParams();
            params.setScrollFlags(0);
        }

        if (bottomNavigationView != null) {
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) bottomNavigationView.getLayoutParams();
            layoutParams.setBehavior(new AppBarLayout.ScrollingViewBehavior(activity, null));
        }

    }
    //Hide Toolbar
    //==============================================================================================

    public void notShowToolbar() {
        if (appBarLayout != null) {
            appBarLayout.setVisibility(View.GONE);
        }
    }

    //BackButton Toolbar
    //==============================================================================================

    private void setupBackToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setToolbarText(String textForToolbar) {
        toolbarText.setText(textForToolbar);
    }

    private void setupActionBar() {
        //ToolBar
        appBarLayout = findViewById(R.id.toolbarInclude);
        toolbar = findViewById(R.id.main_toolbar);
        toolbarImg = findViewById(R.id.toolbarImg);
        toolbarText = findViewById(R.id.toolbarText);

        toolbarContainer.setVisibility(View.VISIBLE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
    }

    //==============================================================================================
    //
    // Set Menu in ToolBar
    //
    //==============================================================================================

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_nav, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        switch (id) {
//        }
//        return super.onOptionsItemSelected(item);
//    }

//    public void setMenuInToolbar(int menuLayout) {
//        toolbar_base.inflateMenu(menuLayout);
//    }

    //==============================================================================================
    //
    // Drawer Layout Toolbar
    //
    //==============================================================================================

    //drawerLayout
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    public NavigationView setNavigationDrawer(int headerLayout, int menuLayout, int extendedLayout) {

        setupActionBar();
        setupDrawer();

        navigationView = findViewById(R.id.navViewId);

        inflateDrawerHeader(headerLayout);
        inflateDrawerMenu(menuLayout);
        inflateDrawerExtendedLayout(extendedLayout);

        return navigationView;
    }

    public NavigationView setNavigationDrawer(int headerLayout, int menuLayout, int extendedLayout, int drawableForToolbar) {

        setToolbarImage(drawableForToolbar);
        setupDrawer();

        navigationView = findViewById(R.id.navViewId);

        inflateDrawerHeader(headerLayout);
        inflateDrawerMenu(menuLayout);
        inflateDrawerExtendedLayout(extendedLayout);

        return navigationView;
    }

    public NavigationView setNavigationDrawer(int headerLayout, int menuLayout, int extendedLayout, String titleToolbar) {

        showToolbar(titleToolbar);
        setupDrawer();

        navigationView = findViewById(R.id.navViewId);

        inflateDrawerHeader(headerLayout);
        inflateDrawerMenu(menuLayout);
        inflateDrawerExtendedLayout(extendedLayout);

        return navigationView;
    }


    //Setup
    //==============================================================================================

    private void setupDrawer() {
        unlockDrawer();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.drawer_open,
                R.string.drawer_close);

        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void inflateDrawerExtendedLayout(int extendedLayout) {
        if (extendedLayout != 0) {
            FrameLayout frameLayout = findViewById(R.id.drawerContainer);
            View view = getLayoutInflater().inflate(extendedLayout, null);
            frameLayout.addView(view);
        }
    }

    private void inflateDrawerMenu(int menuLayout) {
        if (menuLayout != 0) {
            navigationView.inflateMenu(menuLayout);
        }
    }

    private void inflateDrawerHeader(int headerLayout) {
        if (headerLayout != 0) {
            navigationView.inflateHeaderView(headerLayout);
        }
    }

    //Drawer Utils
    //==============================================================================================

    public void closeDrawaer() {
        drawerLayout.closeDrawer(GravityCompat.START);
    }

    public void blockDrawer() {
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    public void unlockDrawer() {
        if (drawerLayout != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        }
    }

    //==============================================================================================
    //
    // ViewPager
    //
    //==============================================================================================
    //Page Create
    public static ArrayList<PageConfig> pageList;
    @SuppressLint("StaticFieldLeak")
    private static ViewPager privateViewPager;
    private BottomNavigationView bottomNavigationView;
    private TabLayout tabLayout;
    boolean execute = true;

    public void setViewPager() {
        setupViewPagers();
    }

    public void setViewPager(boolean TabLayout) {
        setupViewPagers();
        setupTabLayout(TabLayout);
    }

    public void setViewPager(int menuBottonNavigationView) {
        setupViewPagers();
        setupBottonNavigationView(menuBottonNavigationView);
    }

    public void setViewPager(boolean TabLayout, int menuBottonNavigationView) {
        setupViewPagers();
        setupTabLayout(TabLayout);
        setupBottonNavigationView(menuBottonNavigationView);
    }

    //Utils
    //==============================================================================================
    private void setupViewPagers() {
        container.setVisibility(View.GONE);
        //
        privateViewPager = findViewById(R.id.viewpager);
        privateViewPager.setOffscreenPageLimit(pageList.size());
        //
        //
        tabLayout = findViewById(R.id.tablayout);
        //
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), pageList);
        privateViewPager.setAdapter(adapter);
        privateViewPager.setOffscreenPageLimit(Objects.requireNonNull(privateViewPager.getAdapter()).getCount());
    }

    private void setLinkWithBottomNavigation() {

        privateViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                execute = false;
                bottomNavigationView.setSelectedItemId(bottomNavigationView.getMenu().getItem(i).getItemId());
                execute = true;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (!execute) {
                    return true;
                }
                for (int i = 0; i < bottomNavigationView.getMaxItemCount(); i++) {
                    if (bottomNavigationView.getMenu().getItem(i).getItemId() == menuItem.getItemId()) {
                        privateViewPager.setCurrentItem(i);
                        return true;
                    }
                }
                return false;
            }
        });
    }

    private void setupTabLayout(boolean TabLayout) {
        if (TabLayout) {
            tabLayout.setupWithViewPager(privateViewPager);
            tabLayout.setVisibility(View.VISIBLE);
        }
    }

    private void setupBottonNavigationView(int menuBottonNavigationView) {
        bottomNavigationView.inflateMenu(menuBottonNavigationView);
        bottomNavigationView.setVisibility(View.VISIBLE);
        setLinkWithBottomNavigation();
    }

    // @interfaces ViewPagers
    //==============================================================================================

    public static void nextPage() {
        privateViewPager.setCurrentItem(privateViewPager.getCurrentItem() + 1);
    }

    public static void backPage() {
        privateViewPager.setCurrentItem(privateViewPager.getCurrentItem() - 1);
    }

    //==============================================================================================
    //
    // SwipeRefreshLayout
    //
    //==============================================================================================

    @SuppressLint("ClickableViewAccessibility")
    public void setRefreshLayout() {

        unlockRefreshLayout();

        refresh.setColorSchemeColors(getColor(R.color.colorPrimary));
        if (privateViewPager != null) {
            privateViewPager.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    refresh.setEnabled(false);
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_UP:
                            refresh.setEnabled(true);
                            break;
                    }
                    return false;
                }
            });
        }

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (baseRefreshInterface != null) {
                    baseRefreshInterface.refreshInterface();
                }
            }
        });

    }

    //Interface
    //==============================================================================================
    private BaseRefreshInterface baseRefreshInterface;

    public void setBaseRefreshInterface(BaseRefreshInterface baseRefreshInterface) {
        this.baseRefreshInterface = baseRefreshInterface;
    }

    public interface BaseRefreshInterface {
        void refreshInterface();
    }

    //Utils
    //==============================================================================================

    public void blockRefreshLayout() {
        if (refresh != null) {
            refresh.setEnabled(false);
        }
    }

    public void unlockRefreshLayout() {
        if (refresh != null) {
            refresh.setEnabled(true);
        }
    }

    public void showRefresh() {
        if (refresh != null) {
            refresh.setRefreshing(true);
        }
    }

    public void hideRefresh() {
        if (refresh != null) {
            refresh.setRefreshing(false);
        }
    }

    //==============================================================================================
    //
    // BottonSheat
    //
    //==============================================================================================

    private BottomSheetCreate createSheet;

    public void showBottonSheet(View layout) {
        createBottomSheet(layout);
    }

    public void showBottonSheet(View layout, BottomSheetBehavior.BottomSheetCallback callback) {
        createBottomSheet(layout);
        setSheetCallback(callback);
    }

    //Setup
    //==============================================================================================

    private void createBottomSheet(View layout) {
        bottom_sheet.removeAllViews();
        bottom_sheet.addView(layout);

        mBehavior = BottomSheetBehavior.from(bottom_sheet);
        showBottomSheet();
//        createSheet = new BottomSheetCreate().Make(bottom_sheet);
//        createSheet.setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    private void setSheetCallback(BottomSheetBehavior.BottomSheetCallback callback) {
        if (mBehavior != null) {
            mBehavior.setBottomSheetCallback(callback);
        }
    }

    //Utils
    //==============================================================================================

    public void showBottomSheet() {
        if (mBehavior != null) {
            mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }

    public void hideBottomSheet() {
        if (mBehavior != null) {
            mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        }
    }

    public void setAutoBottomSheetPeekHeight() {
        if (mBehavior != null) {
            mBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        }
    }

    public void setBottomSheetPeekHeight(int height) {
        if (mBehavior != null) {
            mBehavior.setPeekHeight(height);
        }
    }

    public void setBottomSheetHideable(boolean hideable) {
        if (mBehavior != null) {
            mBehavior.setHideable(hideable);
        }
    }

    public int getBottomSheetState() {
        if (mBehavior != null) {
            return mBehavior.getState();
        } else {
            return -99;
        }
    }

    public View getView(int layout) {
        return getLayoutInflater().inflate(layout, null);
    }

    //==============================================================================================
    //
    // SnackBar
    //
    //==============================================================================================

    public void showSnackBar(String text) {
        snackbar = Snackbar.make(bottom_sheet, text, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    //==============================================================================================
    //
    // Animations
    //
    //==============================================================================================

    boolean isNavigationHide = false;

//    public void animateNavigation(final boolean hide) {
//        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
//        isNavigationHide = hide;
//        int moveY = hide ? (2 * bottomNavigation.getHeight()) : 0;
//        bottomNavigation.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
//    }

    boolean isSearchBarHide = false;

    public void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
        int moveY = hide ? -(2 * toolbar.getHeight()) : 0;
        toolbar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    //==============================================================================================
    //
    // @OnBackPressed
    //
    //==============================================================================================

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

    }

    //==============================================================================================
    //
    // Systems Verifications
    //
    //==============================================================================================

    private MyReceiver connectionReceiver;
    public static boolean connected = false;
    public static boolean showSnackbar = true;

    private void connectionReceiverRegister() {

        new SystemUtil(activity);
        SystemUtil.setConnectionVerify(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        connectionReceiver = new MyReceiver();
        registerReceiver(connectionReceiver, filter);

    }

    //==============================================================================================
    //
    // @Override connection
    //
    //==============================================================================================

    @Override
    public void ConnectionOK() {
        connected = true;
        if (!verifySnackbar()) {
            clearSnackbar();
        }
        callConectionInterface(connected);
    }


    @Override
    public void ConnectionFail() {
        connected = false;
        if (verifySnackbar() && showSnackbar) {
            showSnackbar();
        }
        callConectionInterface(connected);
    }

    //Interface
    //==============================================================================================

    private void callConectionInterface(boolean connected) {
        if (conectionCallback != null) {
            conectionCallback.ConectionStatus(connected);
        }
    }

    public static void setBaseConectionCallback(BaseConectionCallback baseConectionCallback) {
        conectionCallback = baseConectionCallback;
    }

    private static BaseConectionCallback conectionCallback;

    public interface BaseConectionCallback {
        void ConectionStatus(boolean statusConection);
    }

    //Utils
    //==============================================================================================

    private void showSnackbar() {
        snackbar = Snackbar.make(Objects.requireNonNull(activity.getCurrentFocus()), R.string.msg_no_connection, Snackbar.LENGTH_INDEFINITE);
        snackbar.show();
    }

    private void clearSnackbar() {
        snackbar.dismiss();
        snackbar = null;
    }

    private boolean verifySnackbar() {
        return snackbar == null;
    }

    public void dontShowSnackbarConnection() {
        showSnackbar = false;
    }

    //==============================================================================================
    //
    // @Override Life Cycle Activity
    //
    //==============================================================================================

    @Override
    protected void onPause() {
        super.onPause();
        if (connectionReceiver != null) {
            unregisterReceiver(connectionReceiver);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        connectionReceiverRegister();
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

