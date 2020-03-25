package com.trisha.mybrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView webView;
    ImageView iv_exit, iv_home, iv_forward, iv_back, iv_refersh;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        progressBar = findViewById(R.id.progressBar);
        webView = findViewById(R.id.webView);
        iv_back = findViewById(R.id.iv_back);
        iv_exit = findViewById(R.id.iv_exit);
        iv_forward = findViewById(R.id.iv_forward);
        iv_home = findViewById(R.id.iv_home);
        iv_refersh = findViewById(R.id.iv_refersh);
        iv_home.setOnClickListener(this);
        iv_refersh.setOnClickListener(this);
        iv_forward.setOnClickListener(this);
        iv_exit.setOnClickListener(this);
        iv_back.setOnClickListener(this);
        setupWebView();
        //setupTxtUrl();
    }


    private void hideKeyboard() {
        InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
    }

    /*private String buildUrl(String url) {
        if (url.startsWith("http://") || url.startsWith("https://"))
            return url;
        return "http://".concat(url);
    }*/

    private void setupWebView() {
        webView.getSettings().setSaveFormData(false);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT)
            webView.getSettings().setSavePassword(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.getSettings().setAppCacheEnabled(true);
        webView.loadUrl("https://www.google.com/");
        webView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                progressBar.setVisibility(View.GONE);
                webView.loadUrl("https://www.google.com/");

            }

            public void onPageFinished(WebView view, String url) {
                // do your stuff here
                progressBar.setVisibility(View.GONE);
            }



            /*@Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                binding.progressBar.setVisibility(View.VISIBLE);
                binding.progressBar.setProgress(0);

                //binding.txtUrl.setText("https://www.google.com/");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                binding.progressBar.setVisibility(View.GONE);
            }
        });
        binding.webView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                binding.progressBar.setProgress(newProgress);

            }*/
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.iv_back:
                if (webView.canGoBack())
                    webView.goBack();

                break;
            case R.id.iv_exit:
                finish();
                break;
            case R.id.iv_forward:

                if (webView.canGoForward())
                    webView.goForward();
                break;
            case R.id.iv_home:
                webView.loadUrl("https://www.google.com/");

                break;
            case R.id.iv_refersh:
                webView.reload();
                break;
        }
    }

    /*private void setupTxtUrl() {
        binding.txtUrl.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN) {
                    binding.webView.loadUrl("https://www.google.com/");
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });
    }
*/
}
