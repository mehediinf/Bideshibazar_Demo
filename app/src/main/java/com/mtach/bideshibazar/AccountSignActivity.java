package com.mtach.bideshibazar;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mtach.bideshibazar.auth.LoginActivity;
import com.mtach.bideshibazar.auth.SignUpActivity;

public class AccountSignActivity extends AppCompatActivity {

    Button btnSignIn, btnCreateAccount, btnSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_sign);




        btnSignIn = findViewById(R.id.btnSignIn);
        btnCreateAccount = findViewById(R.id.btnCreateAccount);
        btnSkip = findViewById(R.id.btnSkip);

        btnSignIn.setOnClickListener(view -> {
                startActivity(new Intent(AccountSignActivity.this, LoginActivity.class));
                }
        );

        btnCreateAccount.setOnClickListener(view ->
                {
                    startActivity(new Intent(AccountSignActivity.this, SignUpActivity.class));
                }
        );

        btnSkip.setOnClickListener(view ->
                {
                    startActivity(new Intent(AccountSignActivity.this, MainActivity.class));
                    finish();
                }
        );
    }
}