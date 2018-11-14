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
 *  * Copyright(c) Developed by John Alves at 2018/$today.mouth/12 at 11:25:36 for quantic heart studios
 *
 */

package quanticheart.com.yahooweather.Task;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import quanticheart.com.yahooweather.Task.YahooWeather.YahooWeatherModel;

public class TaskYahooWeather extends AsyncTask<String, Void, String> {
    private HttpURLConnection httpURLConnection = null;
    @SuppressLint("StaticFieldLeak")
    private static Activity mActivity;
    private static String mCep;
    private static boolean buscar_cep = false;

    //==============================================================================================
    //
    // Constants errors
    //
    //==============================================================================================



    @Override
    protected String doInBackground(String... params) {
        StringBuilder result = null;
        int respCode = -1;

        try {
            URL url = new URL("https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(SELECT%20woeid%20FROM%20geo.places%20WHERE%20text%3D%22("+params[0]+"%2C"+params[1]+")%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&language=pt-br");
            YahooWeatherModel.msgOnly(url.toString());
            httpURLConnection = (HttpURLConnection) url.openConnection();

            do {
                if (httpURLConnection != null) {
                    respCode = httpURLConnection.getResponseCode();
                }
            } while (respCode == -1);

            if (respCode == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                result = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                br.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
                httpURLConnection = null;
            }
        }

        return (result != null) ? result.toString() : null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            //                JSONObject object = new JSONObject(s);

            Gson gson = new Gson();
            YahooWeatherModel yahooWeatherModel = gson.fromJson(s, YahooWeatherModel.class);

            if (yahooWeatherModel.getQuery().getResults() == null) {
                callback.YahooWeatherOnFail(NO_VALUE_FOR_WEATHER);
            } else {
                callback.YahooWeatherOnSuccess(yahooWeatherModel);
            }

        } else {
            YahooWeatherModel.msgErro("No conection for server for Weather JSON");
            callback.YahooWeatherOnFail(NO_CONNECTION);
        }
    }

    //==============================================================================================
    //
    // interface
    //
    //==============================================================================================

    public static int NO_CONNECTION = -2;
    public static int NO_VALUE_FOR_WEATHER = -1;
    private static YahooWeatherCallback callback;

    public static void setYahooWeatherCallback(YahooWeatherCallback mCallback) {
        callback = mCallback;
    }

    public interface YahooWeatherCallback {
        void YahooWeatherOnSuccess(YahooWeatherModel yahooWeatherModel);

        void YahooWeatherOnFail(int status);
    }

    //==============================================================================================
    //
    //  TextChangeListener
    //
    //==============================================================================================

    public static TextWatcher TextChangedListener = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 8) {
                if (buscar_cep) {
                    TaskYahooWeather buscarCep = new TaskYahooWeather();
                    buscarCep.execute(s.toString());
                }

            } else {
                buscar_cep = true;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    public static TextWatcher TextChangedListenerWithMask = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 9) {
                if (buscar_cep) {
                    TaskYahooWeather buscarCep = new TaskYahooWeather();
                    buscarCep.execute(s.toString());
                }

            } else {
                buscar_cep = true;
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    //==============================================================================================
    //
    // getTextWatchers
    //
    //==============================================================================================

    public static TextWatcher getTextChangedListener(Activity activity) {
        mActivity = activity;
        return TextChangedListener;
    }


    public static TextWatcher getTextChangedListenerWithMask(Activity activity) {
        mActivity = activity;
        return TextChangedListenerWithMask;
    }
}



