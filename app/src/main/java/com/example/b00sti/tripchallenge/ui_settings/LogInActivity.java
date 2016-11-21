package com.example.b00sti.tripchallenge.ui_settings;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.b00sti.tripchallenge.R;
import com.example.b00sti.tripchallenge.main.MainActivity_;
import com.example.b00sti.tripchallenge.ui_signin.SignUpActivity_;
import com.example.skeleton.android_utils.firebase.FirebaseManager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_log_in)
public class LogInActivity extends AppCompatActivity
{
    protected EditText emailEditText;
    protected EditText passwordEditText;
    protected Button logInButton;
    protected TextView signUpTextView;

    FirebaseAuth firebaseAuth;

    @Bean
    FirebaseManager firebaseManager;

    @AfterViews
    void initData()
    {
        firebaseAuth = firebaseManager.getFirebaseAuth();
    }

    @AfterViews
    void onCreate()
    {
        signUpTextView = (TextView) findViewById(R.id.signUpText);
        emailEditText = (EditText) findViewById(R.id.emailField);
        passwordEditText = (EditText) findViewById(R.id.passwordField);
        logInButton = (Button) findViewById(R.id.loginButton);

        signUpTextView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(LogInActivity.this, SignUpActivity_.class);
                startActivity(intent);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                email = email.trim();
                password = password.trim();

                if (email.isEmpty() || password.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                    builder.setMessage(R.string.login_error_message)
                            .setTitle(R.string.login_error_title)
                            .setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>()
                            {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task)
                                {
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(LogInActivity.this, MainActivity_.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        AlertDialog.Builder builder = new AlertDialog.Builder(LogInActivity.this);
                                        builder.setMessage(task.getException().getMessage())
                                                .setTitle(R.string.login_error_title)
                                                .setPositiveButton(android.R.string.ok, null);
                                        AlertDialog dialog = builder.create();
                                        dialog.show();
                                    }
                                }
                            });
                }
            }
        });
    }

}
