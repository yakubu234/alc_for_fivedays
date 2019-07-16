package com.example.alc_for_fivedays;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class About_alc extends AppCompatActivity {
    private WebView myWebView;
    public ProgressBar superProgressBar;
    private ProgressDialog pd;
    boolean loadingFinished = true;
    boolean redirect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);
        myWebView = findViewById(R.id.about);
        superProgressBar =(ProgressBar) findViewById(R.id.myProgressBar);
        superProgressBar.setMax(100);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setGeolocationEnabled(true);
        myWebView.setWebViewClient(new WebViewClient()
        {
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error)
            {
                handler.proceed();
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                superProgressBar.setVisibility(View.INVISIBLE);
            }
        });
        myWebView.loadUrl("https://andela.com/alc");
        myWebView.setWebChromeClient(new WebChromeClient());
    }

    public class myWebClient extends WebViewClient{
        @Override
        public void onPageFinished(WebView view, String url) {

            super.onPageFinished(view, url);
            superProgressBar.setVisibility(View.INVISIBLE);
            superProgressBar.setProgress(100);
            if (url.contains("https://andela.com/alc/")) {
                superProgressBar.setVisibility(View.GONE);
                superProgressBar.setProgress(100);
            }

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            superProgressBar.setVisibility(View.VISIBLE);
            superProgressBar.setProgress(30);
            loadingFinished = false;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode==KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()){
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
