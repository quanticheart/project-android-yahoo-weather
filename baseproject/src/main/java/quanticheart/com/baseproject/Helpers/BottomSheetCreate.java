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
 *  * Copyright(c) Developed by John Alves at 2018/11/11 at 0:44:36 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.Helpers;

import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

public class BottomSheetCreate {

    //==============================================================================================
    //
    // Vars
    //
    //==============================================================================================

    private static BottomSheetBehavior bottomSheetBehavior;

    //==============================================================================================
    //
    // Auto Create
    //
    //==============================================================================================

    public static BottomSheetCreate autoMake(View view) {
        return new BottomSheetCreate().Make(view).setState(BottomSheetBehavior.STATE_EXPANDED);
    }

    public static BottomSheetCreate autoMake(View view, BottomSheetBehavior.BottomSheetCallback callback) {
        return new BottomSheetCreate().Make(view).setState(BottomSheetBehavior.STATE_EXPANDED).setCallback(callback);
    }

    //==============================================================================================
    //
    // Builder's
    //
    //==============================================================================================

    public BottomSheetCreate Make(View layout) {
        bottomSheetBehavior = BottomSheetBehavior.from(layout);
        return this;
    }

    public BottomSheetCreate setAutoPeekHeight() {
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.PEEK_HEIGHT_AUTO);
        return this;
    }

    public BottomSheetCreate close() {
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);
        return this;
    }

    public BottomSheetCreate open() {
        bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_EXPANDED);
        return this;
    }

    public BottomSheetCreate setPeekHeight(int height) {
        bottomSheetBehavior.setPeekHeight(height);
        return this;
    }

    public BottomSheetCreate setHideable(boolean hideable) {
        bottomSheetBehavior.setHideable(hideable);
        return this;
    }

    public BottomSheetCreate setState(int state) {
        bottomSheetBehavior.setState(state);
        return this;
    }

    public int getState() {
        return bottomSheetBehavior.getState();
    }

    public BottomSheetCreate setCallback(BottomSheetBehavior.BottomSheetCallback callback) {
        bottomSheetBehavior.setBottomSheetCallback(callback);
        return this;
    }


}
