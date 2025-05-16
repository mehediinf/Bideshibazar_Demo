package com.mtach.bideshibazar.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.mtach.bideshibazar.BaseActivity;
import com.mtach.bideshibazar.MainActivity;
import com.mtach.bideshibazar.R;

public class SignUpActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient mGoogleSignInClient;
    private SignInButton btnGoogleSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupToolbar("Sign Up", true);

        // Google Sign-In options
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);

        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());
    }

    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Sign-in success, navigate to main screen
            Toast.makeText(this, "Welcome " + account.getDisplayName(), Toast.LENGTH_SHORT).show();

            // Example: Open MainActivity after login
            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            intent.putExtra("email", account.getEmail());
            startActivity(intent);
            finish();

        } catch (ApiException e) {
            Log.w("SignUpActivity", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Google Sign-In failed.", Toast.LENGTH_SHORT).show();
        }
    }
}
