package com.mtach.bideshibazar.features;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.common.BitMatrix;
import com.mtach.bideshibazar.R;

public class ShareActivity extends AppCompatActivity {

    private static final String SHARE_LINK = "https://play.google.com/store/apps/details?id=com.mtach.bideshibazar&pcampaignid=web_share";
    private static final String TAG = "ShareAnalytics";

    ImageView backIcon, qrImageView;
    Button shareBtn, copyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        backIcon = findViewById(R.id.backIcon);
        qrImageView = findViewById(R.id.qrImageView);
        shareBtn = findViewById(R.id.shareBtn);
        copyBtn = findViewById(R.id.copyBtn);

        backIcon.setOnClickListener(v -> finish());

        // Generate QR Code
        generateQRCode(SHARE_LINK);

        // Share Intent
        shareBtn.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Bideshi Bazar");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out Bideshi Bazar! " + SHARE_LINK);
            startActivity(Intent.createChooser(shareIntent, "Share via"));

            Log.d(TAG, "User shared the app");
        });

        // Copy to Clipboard
        copyBtn.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("App Link", SHARE_LINK);
            clipboard.setPrimaryClip(clip);
            Toast.makeText(this, "Link copied to clipboard", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "User copied the share link");
        });
    }

    private void generateQRCode(String url) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(url, BarcodeFormat.QR_CODE, 200, 200);
            Bitmap bmp = Bitmap.createBitmap(200, 200, Bitmap.Config.RGB_565);

            for (int x = 0; x < 200; x++) {
                for (int y = 0; y < 200; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }
            qrImageView.setImageBitmap(bmp);
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}




