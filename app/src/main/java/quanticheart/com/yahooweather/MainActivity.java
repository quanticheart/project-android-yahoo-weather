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
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/13 at 0:16:0 for quantic heart studios
 *
 */

package quanticheart.com.yahooweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import quanticheart.com.yahooweather.Task.TaskYahooWeather;
import quanticheart.com.yahooweather.Task.YahooWeather.YahooWeatherModel;

public class MainActivity extends AppCompatActivity implements TaskYahooWeather.YahooWeatherCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInterface();
        initVars();
        initActions();

    }

    //==============================================================================================
    //
    // Init
    //
    //==============================================================================================

    private void initInterface() {
        TaskYahooWeather.setYahooWeatherCallback(this);
    }

    private void initVars() {

    }

    private void initActions() {

        TaskYahooWeather weather = new TaskYahooWeather();
        weather.execute("4355576", "-46.4056748");

    }

    //==============================================================================================
    //
    // Yahoo Weather Interface
    //
    //==============================================================================================

    @Override
    public void YahooWeatherOnSuccess(YahooWeatherModel yahooWeatherModel) {

        Log.w("Teste" , yahooWeatherModel.getQuery().getResults().getChannel().getTitle());

    }

    @Override
    public void YahooWeatherOnFail(int status) {

    }
}
