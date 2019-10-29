package imransk.ml.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView=findViewById(R.id.webView_id);

        this.webView.setWebViewClient(new myWebclient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl("https://radio.net.bd");
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
    }

    public class myWebclient extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            Toast.makeText(MainActivity.this, "finished", Toast.LENGTH_SHORT).show();
            super.onPageFinished(view, url);
        }

        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            Toast.makeText(MainActivity.this, "started", Toast.LENGTH_SHORT).show();
            super.onPageStarted(view, url, favicon);
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(MainActivity.this, "loaded", Toast.LENGTH_SHORT).show();
            view.loadUrl(url);
            return super.shouldOverrideUrlLoading(view, url);
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4 || !this.webView.canGoBack()) {
            return super.onKeyDown(keyCode, event);
        }
        this.webView.goBack();
        return true;
    }

}
