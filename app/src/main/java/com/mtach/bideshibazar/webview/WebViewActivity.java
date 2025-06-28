package com.mtach.bideshibazar.webview;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.R;

public class WebViewActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;
    private ImageView backButton;
    private TextView titleText;
    private String allowedUrl;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view); // তোমার xml layout name

        // Views init
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        backButton = findViewById(R.id.backButton);
        titleText = findViewById(R.id.pageTitle);

        // Intent থেকে allowedUrl নাও
        allowedUrl = getIntent().getStringExtra("url");

        // WebView settings
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);

        // Custom WebViewClient সেট করো
        webView.setWebViewClient(new CustomWebViewClient());

        // মূল লিঙ্ক লোড করো
        webView.loadUrl(allowedUrl);

        // Back button action
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

        // শুধু allowedUrl লোড করবে, অন্য লিঙ্ক ব্রাউজারে ওপেন করবে
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String clickedUrl = request.getUrl().toString();
            if (clickedUrl.equals(allowedUrl)) {
                return false; // allowed url load in WebView
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(clickedUrl));
                startActivity(browserIntent);
                return true; // don’t load in WebView
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.equals(allowedUrl)) {
                return false;
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
                return true;
            }
        }

        // SSL error handle
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
