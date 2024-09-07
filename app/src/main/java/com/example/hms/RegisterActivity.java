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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText et_register_username, et_register_email, et_register_password, et_register_confirm_password, et_register_mobile;
    private MaterialButton btn_register, btn_login;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_register_username = findViewById(R.id.et_register_username);
        et_register_email = findViewById(R.id.et_register_email);
        et_register_password = findViewById(R.id.et_register_password);
        et_register_confirm_password = findViewById(R.id.et_register_confirm_password);
        et_register_mobile = findViewById(R.id.et_register_mobile);
        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);

        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize ProgressDialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering...");
        progressDialog.setCancelable(false);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_register_username.getText().toString().trim();
                String email = et_register_email.getText().toString().trim();
                String password = et_register_password.getText().toString().trim();
                String confirmPassword = et_register_confirm_password.getText().toString().trim();
                String mobile = et_register_mobile.getText().toString().trim();

                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || mobile.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill All the Fields", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else if (password.length() < 8) {
                    Toast.makeText(getApplicationContext(), "Password should be at least 8 characters", Toast.LENGTH_SHORT).show();
                } else {
                    // Show loader
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            // Hide loader
                            progressDialog.dismiss();

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Complete", Toast.LENGTH_SHORT).show();
                                sendEmailVerification();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to Register", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Verification Email Sent, Verify and Log in Again", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Failed to Send Verification Email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(getApplicationContext(), "Failed to Verify", Toast.LENGTH_SHORT).show();
        }
    }
}
