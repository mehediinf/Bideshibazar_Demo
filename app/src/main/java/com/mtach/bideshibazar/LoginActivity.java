package com.mtach.bideshibazar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginActivity extends AppCompatActivity {


    EditText emailInput, passwordInput;
    CheckBox rememberMe, agreeTerms;
    Button btnLogin, btnGoogle;
    TextView forgotPassword, signupLink;



    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        emailInput = findViewById(R.id.emailInput);
        passwordInput = findViewById(R.id.passwordInput);
        rememberMe = findViewById(R.id.checkboxRemember);
        agreeTerms = findViewById(R.id.checkboxAgree);
        btnLogin = findViewById(R.id.btnLogin);
        forgotPassword = findViewById(R.id.forgotPassword);
        signupLink = findViewById(R.id.signupLink);

        btnLogin.setOnClickListener(view -> {
            String email = emailInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!agreeTerms.isChecked()) {
                Toast.makeText(this, "You must agree to Terms & Privacy", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
            // You can add actual login logic here
        });

        btnGoogle.setOnClickListener(view -> {
            Toast.makeText(this, "Google login coming soon...", Toast.LENGTH_SHORT).show();
        });





        // Configure sign-in to request the user’s ID, email address, and basic profile.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestIdToken("YOUR_WEB_CLIENT_ID_HERE")  // Replace with your Web client ID
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.btnGoogleSignIn).setOnClickListener(view -> {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });





    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully
            String name = account.getDisplayName();
            String email = account.getEmail();
            String idToken = account.getIdToken();

            Toast.makeText(this, "Signed in as " + name, Toast.LENGTH_SHORT).show();

        } catch (ApiException e) {
            Log.w("GOOGLE_SIGN_IN", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Sign-in failed", Toast.LENGTH_SHORT).show();
        }
    }
}



