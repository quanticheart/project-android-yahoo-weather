
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
 *  * Copyright(c) Developed by John Alves at 2018/11/7 at 3:44:22 for quantic heart studios
 *
 */

package quanticheart.com.baseproject.ActivitySuport;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import quanticheart.com.baseproject.Contants.IntentKeys;
import quanticheart.com.baseproject.R;
import quanticheart.com.baseproject.Utils.GlideUtil;

public class WebViewActivity extends AppCompatActivity {

    //init
    WebView webView;
    LinearLayout llWebview;

    //Load Layout
    CoordinatorLayout loadingLayout;
    ImageView imgFavicon;
    ProgressBar progressBar;

    //TermsOfUser
    Button aceitar;
    Button rejeitar;
    LinearLayout llTerms;

    private static TermsOfUseCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);

        initVars();
        initActions();

    }

    private void initVars() {

        //init
        webView = findViewById(R.id.webview);
        llWebview = findViewById(R.id.llWebview);

        //Load Layout
        loadingLayout = findViewById(R.id.loading);
        imgFavicon = findViewById(R.id.label_favicon);
        progressBar = findViewById(R.id.progress);

        //TermsOfUser
        aceitar = findViewById(R.id.aceitar);
        rejeitar = findViewById(R.id.rejeitar);
        llTerms = findViewById(R.id.linearLayout);

    }


    private void initActions() {
        loadWebViewLoad(webView, getIntent().getStringExtra(IntentKeys.keyUrlForWebView));
    }

    //==============================================================================================
    //
    // Webview init
    //
    //==============================================================================================

    @SuppressLint("SetJavaScriptEnabled")
    private void loadWebViewLoad(final WebView webview, String url) {

        webview.getSettings().setJavaScriptEnabled(true);
        webview.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        webview.getSettings().setSupportMultipleWindows(true);

        webview.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                showWebview();
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
            }

        });

        webview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onReceivedIcon(WebView view, Bitmap icon) {
                super.onReceivedIcon(view, icon);
                GlideUtil.initGlide(WebViewActivity.this, icon, imgFavicon);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                setProgressBar(newProgress);
            }

        });
        webview.loadUrl(url);
    }

    //==============================================================================================
    //
    // Utils
    //
    //==============================================================================================

    private void showLoading() {
        loadingLayout.setVisibility(View.VISIBLE);
        llWebview.setVisibility(View.GONE);
    }

    private void showWebview() {
        loadingLayout.setVisibility(View.GONE);
        llWebview.setVisibility(View.VISIBLE);
        //
        progressBar.setProgress(0);
        progressBar.setSecondaryProgress(0);

        if (getIntent().hasExtra(IntentKeys.keyWebViewForTerms)) {
            initTermsOfUser();
        }
    }

    private void setProgressBar(int newProgress) {
        progressBar.setProgress(newProgress);
        progressBar.setSecondaryProgress(newProgress + 10);
    }

    //==============================================================================================
    //
    // @Override
    //
    //==============================================================================================

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            finish();
        }
    }


    //==============================================================================================
    //
    // @Interface for Terms of user
    //
    //==============================================================================================

    public static void setTermsCallback(TermsOfUseCallback termsOfUseCallback) {
        callback = termsOfUseCallback;
    }

    public interface TermsOfUseCallback {
        void TermsOfUseAccept();

        void TermsOfUseRegected();
    }

    private void initTermsOfUser() {

        llTerms.setVisibility(View.VISIBLE);

        aceitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.TermsOfUseAccept();
                finish();
            }
        });

        rejeitar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.TermsOfUseRegected();
                finish();
            }
        });

    }

}
