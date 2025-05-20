package com.mtach.bideshibazar.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.*;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.gson.JsonObject;
import com.mtach.bideshibazar.BaseActivity;
import com.mtach.bideshibazar.MainActivity;
import com.mtach.bideshibazar.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends BaseActivity {

    private static final int RC_SIGN_IN = 1001;
    private GoogleSignInClient mGoogleSignInClient;

    EditText nameInput, emailInput, passwordInput, confirmPasswordInput;
    Button signUpButton;
    SignInButton btnGoogleSignIn;

    AuthApi authApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupToolbar("Create an account",true);


        // Email SignUp Views
      nameInput = findViewById(R.id.fullName);
      emailInput = findViewById(R.id.email);
      passwordInput = findViewById(R.id.password);
      confirmPasswordInput = findViewById(R.id.confirmPassword);
      signUpButton = findViewById(R.id.btnSignUp);


        // Google SignIn Button
        btnGoogleSignIn = findViewById(R.id.btnGoogleSignIn);

        // Retrofit Init
        authApi = ApiClient.getClient().create(AuthApi.class);

        // Email Sign-Up Listener
        signUpButton.setOnClickListener(v -> registerUser());

        // Google Sign-In Setup
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnGoogleSignIn.setOnClickListener(v -> signInWithGoogle());
    }

    // Email Registration
    private void registerUser() {
        String name = nameInput.getText().toString().trim();
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.getText().toString().trim();
        String confirmPassword = confirmPasswordInput.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Fill in all the boxes", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
            return;
        }

        JsonObject request = new JsonObject();
        request.addProperty("name", name);
        request.addProperty("email", email);
        request.addProperty("password", password);
        request.addProperty("password_confirmation", confirmPassword);

        Call<ResponseBody> call = authApi.registerUser(request);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Sign up successful", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    try {
                        String error = response.errorBody().string();
                        Log.e("SignUp", "Error: " + error);
                        Toast.makeText(SignUpActivity.this, "Sign up failed", Toast.LENGTH_LONG).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        Toast.makeText(SignUpActivity.this, "Unknown problem", Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("SignUp", "Failure: " + t.getMessage());
                Toast.makeText(SignUpActivity.this, "Network Problem", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Google Sign-In
    private void signInWithGoogle() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    // Result Handle
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
            Toast.makeText(this, "Welcome " + account.getDisplayName(), Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
            intent.putExtra("email", account.getEmail());
            startActivity(intent);
            finish();

        } catch (ApiException e) {
            Log.w("SignUpActivity", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(this, "Google Sign-In Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
