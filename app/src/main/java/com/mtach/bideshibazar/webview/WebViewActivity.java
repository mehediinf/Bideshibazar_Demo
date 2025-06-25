package com.mtach.bideshibazar.webview;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.app.AlertDialog;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    ImageView backButton;
    TextView titleText;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        backButton = findViewById(R.id.backButton);
        titleText = findViewById(R.id.pageTitle);

        String url = getIntent().getStringExtra("url");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        webView.setWebViewClient(new CustomWebViewClient());
        webView.loadUrl(url);

        // Back button functionality
        backButton.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    private class CustomWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(ProgressBar.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(ProgressBar.GONE);
            titleText.setText(webView.getTitle());
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            new AlertDialog.Builder(WebViewActivity.this)
                    .setTitle("SSL Certificate Error")
                    .setMessage("The website's certificate is not trusted. Do you want to continue anyway?")
                    .setPositiveButton("Continue", (dialog, which) -> handler.proceed())
                    .setNegativeButton("Cancel", (dialog, which) -> handler.cancel())
                    .setCancelable(false)
                    .show();
        }
    }
}
