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
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 3:43:36 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.Utils;

import android.app.Activity;
import android.content.Intent;

import quanticheart.com.baseproject.ActivitySuport.WebViewActivity;
import quanticheart.com.baseproject.Contants.IntentKeys;

/**
 * Created by John on 29/01/2018.
 */

public class ActivityUtil {

    /**
     * @param activity
     * @param intent
     * @param finish
     */
    public static void callActivity(Activity activity, final Intent intent, final Boolean finish) {
        initIntent(activity, verifieStatusIntent(intent), finish);
    }


    /**
     *
     * @param activity
     * @param intentClass
     * @param finish
     */
    public static void callActivity(Activity activity, final Class intentClass, final Boolean finish) {
        initIntent(activity, verifieStatusIntent(new Intent(activity, intentClass)), finish);
    }

    /**
     * @param activity
     * @param url
     * @param finish
     */
    public static void callActivityWebView(Activity activity, String url, final Boolean finish) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(IntentKeys.keyUrlForWebView, url);
        ActivityUtil.callActivity(activity, intent, finish);
    }

    /**
     * @param activity
     * @param url
     * @param finish
     */
    public static void callActivityWebViewForTermOfUser(Activity activity, String url, final Boolean finish) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra(IntentKeys.keyUrlForWebView, url);
        intent.putExtra(IntentKeys.keyWebViewForTerms, true);
        ActivityUtil.callActivity(activity, intent, finish);
    }

    /**
     * @param intent
     * @return
     */
    private static Intent verifieStatusIntent(Intent intent) {
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    /**
     * @param activity
     * @param intent
     * @param finish
     */
    private static void initIntent(Activity activity, Intent intent, boolean finish) {
        activity.startActivity(intent);
        if (finish) {
            activity.finish();
        }
    }


}
