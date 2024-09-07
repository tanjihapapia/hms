package com.example.hms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText v_loginemail, v_loginpassword;
    private MaterialButton v_login, v_createAcc;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        v_loginemail = findViewById(R.id.email);
        v_loginpassword = findViewById(R.id.password);
        v_login = findViewById(R.id.btn_login);
        v_createAcc = findViewById(R.id.btn_register);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Initialize ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Logging in...");
        progressDialog.setCancelable(false);

        if (firebaseUser != null && firebaseUser.isEmailVerified()) {
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish(); // Close LoginActivity if user is already logged in
        }

        v_createAcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        v_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail = v_loginemail.getText().toString().trim();
                String pass = v_loginpassword.getText().toString().trim();

                if (mail.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Fill All the Fields", Toast.LENGTH_SHORT).show();
                } else {
                    // Show loader
                    progressDialog.show();

                    firebaseAuth.signInWithEmailAndPassword(mail, pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Hide loader
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                checkEmailVerification();
                            } else {
                                Toast.makeText(LoginActivity.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void checkEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null && firebaseUser.isEmailVerified()) {
            Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
            finish(); // Close LoginActivity after successful login
        } else {
            Toast.makeText(LoginActivity.this, "Verify Your Email First", Toast.LENGTH_SHORT).show();
            firebaseAuth.signOut();
        }
    }
}
