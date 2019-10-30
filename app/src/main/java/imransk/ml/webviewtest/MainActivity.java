package imransk.ml.webviewtest;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {
    WebView takeURL;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setMax(100);
        progressBar.setVisibility(View.GONE);
        takeURL = (WebView) findViewById(R.id.webView);


        takeURL = (WebView) findViewById(R.id.webView);



        //Web View Mechanism

        takeURL.getSettings().setJavaScriptEnabled(true);
        takeURL.getSettings().setSupportZoom(true);
        takeURL.getSettings().setBuiltInZoomControls(false);
        takeURL.getSettings().setUseWideViewPort(true);
        takeURL.getSettings().setLoadWithOverviewMode(true);
        takeURL.getSettings().setLoadsImagesAutomatically(true);


        this.takeURL.getSettings().setBuiltInZoomControls(true);
        this.takeURL.getSettings().setAppCacheEnabled(true);
        this.takeURL.getSettings().setDatabaseEnabled(true);
        this.takeURL.getSettings().setDomStorageEnabled(true);
        this.takeURL.getSettings().setBuiltInZoomControls(true);
        this.takeURL.getSettings().setSupportZoom(true);
        this.takeURL.getSettings().setUseWideViewPort(true);
        this.takeURL.getSettings().setLoadWithOverviewMode(true);

        this.takeURL.getSettings().setPluginState(WebSettings.PluginState.ON);


        takeURL.loadUrl("https://radio.net.bd/");
        takeURL.getSettings().setMediaPlaybackRequiresUserGesture(true);

        takeURL.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        takeURL.setBackgroundColor(Color.WHITE);

        //Progressbar System
        takeURL.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBar.setProgress(newProgress);
                if (newProgress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                if (newProgress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);

                }
            }
        });


        //WebViewClient + Check Internet
        takeURL.setWebViewClient(new ourViewClient() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    webView.stopLoading();
                } catch (Exception e) {
                }

                if (webView.canGoBack()) {
                    webView.goBack();
                }

                webView.loadUrl("about:blank");
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Opps !!");
                alertDialog.setMessage("Check your Wi-Fi or, Data Connection and try again.\n\nOtherwise Check your web URL");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                });

                alertDialog.show();
            }
        });




    }


    public class ourViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            CookieManager.getInstance().setAcceptCookie(true);
            view.loadUrl(url);

            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);

        }
    }



    // This method is used to detect back button
    @Override
    public void onBackPressed() {
        if(takeURL.canGoBack()) {
            takeURL.goBack();
        }else {

            finish();

        }
    }//End Back button press for exit...

}
