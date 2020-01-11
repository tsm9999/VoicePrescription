package com.example.tanush.sih_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DoctorLoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    EditText mail,password;
    Button button;
    Button register;
    private FirebaseUser user;
    TextView resetPassword;
    Button ok_forgot, cancel_forgot;
    EditText forgotEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);



        FirebaseApp.initializeApp(DoctorLoginActivity.this);
        firebaseAuth=FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        if(user != null) {
            startActivity(new Intent(getBaseContext(),DashBoardActivity.class));
        }

        mail=findViewById(R.id.mail);
        password=findViewById(R.id.password);
        button=findViewById(R.id.button);
        register=findViewById(R.id.register);
        resetPassword=findViewById(R.id.reset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mail.getText().toString().isEmpty()&&!password.getText().toString().isEmpty()) {
                    final ProgressDialog progressDialog = new ProgressDialog(DoctorLoginActivity.this);
                    progressDialog.setMessage("Logging You In...");
                    progressDialog.show();
                    firebaseAuth.signInWithEmailAndPassword(mail.getText().toString(), password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        startActivity(new Intent(DoctorLoginActivity.this, DashBoardActivity.class));
                                        progressDialog.dismiss();
                                    } else {
                                        progressDialog.dismiss();
                                        Toast.makeText(DoctorLoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else
                    Toast.makeText(DoctorLoginActivity.this, "Please Enter Details", Toast.LENGTH_SHORT).show();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorLoginActivity.this,DoctorSignUpActivity.class));
            }
        });

        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mAlterDialog=new AlertDialog.Builder(DoctorLoginActivity.this);
                final View mview1 =getLayoutInflater().inflate(R.layout.reset_password_dialog,null);
                mAlterDialog.setView(mview1);
                ok_forgot=mview1.findViewById(R.id.forgot_okbtn);
                cancel_forgot=mview1.findViewById(R.id.forgot_cancelbtn);
                forgotEmail=mview1.findViewById(R.id.forgotEmail);
                final ProgressDialog progressDialog = new ProgressDialog(DoctorLoginActivity.this);
                progressDialog.setMessage("Loading...");
                final AlertDialog dialog=mAlterDialog.create();
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                ok_forgot.setOnClickListener(new View.OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        if(forgotEmail.getText().toString().isEmpty()) {
                            forgotEmail.setError("Enter E-Mail ID");
                        }
                        else {
                            progressDialog.show();
                            firebaseAuth.sendPasswordResetEmail(forgotEmail.getText().toString().trim()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()) {
                                        startActivity(new Intent(mview1.getContext(),DoctorLoginActivity.class));
                                        Toast.makeText(getBaseContext(), "Password Reset Email sent!!", Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(getBaseContext(), "Invalid E-Mail Address", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            dialog.dismiss();
                        }
                    }
                });
                cancel_forgot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}
