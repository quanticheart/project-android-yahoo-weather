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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

import quanticheart.com.baseproject.BaseProject.Adapters.ViewPager.PageConfig;
import quanticheart.com.baseproject.BaseProject.Adapters.ViewPager.ViewPagerAdapter;

public abstract class BaseActivityFragmentManager extends BaseActivity {

    //Page Create
    public static ArrayList<PageConfig> pageList;

    //==============================================================================================
    //
    // onCreateView
    //
    //==============================================================================================

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    //==============================================================================================
    //
    // Init Methods
    //
    //==============================================================================================

    private void initVars(View view) {

    }

    private void initActions() {

    }

    //==============================================================================================
    //
    // Utils
    //
    //==============================================================================================

    private static ViewPager privateViewPager;

    public void setupViewPager(ViewPager viewPager) {
        privateViewPager = viewPager;
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), pageList);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(Objects.requireNonNull(viewPager.getAdapter()).getCount());
    }

    //==============================================================================================
    //
    // Interfaces
    //
    //==============================================================================================

    public static void nextPage() {
        privateViewPager.setCurrentItem(privateViewPager.getCurrentItem() + 1);
    }

    public static void backPage() {
        privateViewPager.setCurrentItem(privateViewPager.getCurrentItem() - 1);
    }

}