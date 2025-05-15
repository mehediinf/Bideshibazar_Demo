package com.mtach.bideshibazar;

import android.os.Bundle;
import android.util.Log;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import androidx.credentials.CredentialManager;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
//import androidx.credentials.google.GoogleIdTokenCredential;
//import androidx.credentials.google.GoogleIdTokenCredentialRequest;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginActivity extends BaseActivity {

//    EditText emailInput, passwordInput;
//    CheckBox rememberMe, agreeTerms;
//    Button btnLogin;
//    TextView forgotPassword, signupLink;
//    Button btnGoogleSignIn;
//
//    private CredentialManager credentialManager;
//    private Executor executor;
//
//    private static final String TAG = "LoginActivity";
//    private static final String WEB_CLIENT_ID = "YOUR_WEB_CLIENT_ID_HERE"; // Replace this!

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupToolbar("Login", true);


//        // View bindings
//        emailInput = findViewById(R.id.emailInput);
//        passwordInput = findViewById(R.id.passwordInput);
//        rememberMe = findViewById(R.id.checkboxRemember);
//        agreeTerms = findViewById(R.id.checkboxAgree);
//        btnLogin = findViewById(R.id.btnLogin);
//        forgotPassword = findViewById(R.id.forgotPassword);
//        signupLink = findViewById(R.id.signupLink);
//        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);
//
//        credentialManager = CredentialManager.create(this);
//        executor = Executors.newSingleThreadExecutor();
//
//        // Manual login button
//        btnLogin.setOnClickListener(view -> {
//            String email = emailInput.getText().toString();
//            String password = passwordInput.getText().toString();
//
//            if (email.isEmpty() || password.isEmpty()) {
//                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            if (!agreeTerms.isChecked()) {
//                Toast.makeText(this, "You must agree to Terms & Privacy", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            Toast.makeText(this, "Logging in...", Toast.LENGTH_SHORT).show();
//        });

        // Google Sign-In button
      //  btnGoogleSignIn.setOnClickListener(v -> startGoogleSignIn());
    }

//    private void startGoogleSignIn() {
//        GoogleIdTokenCredentialRequest googleIdTokenRequest =
//                new GoogleIdTokenCredentialRequest.Builder()
//                        .setServerClientId(WEB_CLIENT_ID)
//                        .build();
//
//        GetCredentialRequest request = new GetCredentialRequest.Builder()
//                .addCredentialOption(googleIdTokenRequest)
//                .build();
//
//        credentialManager.getCredentialAsync(
//                this,
//                request,
//                executor,
//                result -> {
//                    try {
////                        GoogleIdTokenCredential credential =
////                                GoogleIdTokenCredential.createFrom(result.getCredential());
////
////                        String idToken = credential.getIdToken();
////                        String email = credential.getId();
//
//                        runOnUiThread(() ->
//                                Toast.makeText(this, "Signed in as: " + email, Toast.LENGTH_SHORT).show()
//                        );
//
//                        // You can send the idToken to your backend server here
//
//                    } catch (Exception e) {
//                        runOnUiThread(() ->
//                                Toast.makeText(this, "Sign-in failed: " + e.getMessage(), Toast.LENGTH_SHORT).show()
//                        );
//                        Log.e(TAG, "Failed to parse Google credential", e);
//                    }
//                },
//                exception -> {
//                    runOnUiThread(() ->
//                            Toast.makeText(this, "Google Sign-In error", Toast.LENGTH_SHORT).show()
//                    );
//                    Log.e(TAG, "getCredentialAsync failed");
//                }
//        );
//    }
}
