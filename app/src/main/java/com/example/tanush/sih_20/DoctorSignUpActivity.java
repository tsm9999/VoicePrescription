package com.example.tanush.sih_20;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DoctorSignUpActivity extends AppCompatActivity {

    FirebaseFirestore db;
    FirebaseAuth firebaseAuth;
    CollectionReference cr;
    EditText name, lic_no, phone_no, email, photo, sign, degree_photo, password, retypePassword, dob, degree, hospital, address, aadhar_no;
    Button signUp;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_sign_up);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        cr = db.collection("Doctor_Details");

        name = findViewById(R.id.docname);
        email = findViewById(R.id.docmail);
        phone_no = findViewById(R.id.docmobile);
        lic_no = findViewById(R.id.doclic);
        photo = findViewById(R.id.docphoto);

        sign = findViewById(R.id.docsign);
        degree_photo = findViewById(R.id.docdegree_photo);
        dob = findViewById(R.id.doc_dob);

        degree = findViewById(R.id.doc_degree);
        hospital = findViewById(R.id.doc_hospital);
        address = findViewById(R.id.doc_address);
        aadhar_no = findViewById(R.id.doc_aadhar);

        password = findViewById(R.id.password);
        retypePassword = findViewById(R.id.retypepassword);


        signUp = findViewById(R.id.signupbtn);


        // passwordCheck(password.getText().toString(),retypePassword.getText().toString());
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordCheck();
                if (!name.getText().toString().isEmpty() &&
                        !email.getText().toString().isEmpty() &&
                        !phone_no.getText().toString().isEmpty() &&
                        !lic_no.getText().toString().isEmpty() &&
                        !photo.getText().toString().isEmpty() &&
                        !sign.getText().toString().isEmpty() &&
                        !degree_photo.getText().toString().isEmpty() &&
                        !dob.getText().toString().isEmpty() &&
                        !password.getText().toString().isEmpty() &&
                        !retypePassword.getText().toString().isEmpty() &&
                        !degree.getText().toString().isEmpty() &&
                        !hospital.getText().toString().isEmpty() &&
                        !address.getText().toString().isEmpty() &&
                        !aadhar_no.getText().toString().isEmpty() &&
                        funmail()&&funname()&&funcontactempty()&&funpass1empty()&&funpass2empty()&&funpassinvalid()
                ) {
                    final ProgressDialog progressDialog = new ProgressDialog(DoctorSignUpActivity.this);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();
                    Log.e("error", "wnter if");
                    DoctorUser doctorUser = new DoctorUser(name.getText().toString(), lic_no.getText().toString(), phone_no.getText().toString(), email.getText().toString(), photo.getText().toString(), sign.getText().toString(), degree_photo.getText().toString(), password.getText().toString(), dob.getText().toString(), degree.getText().toString(), hospital.getText().toString(), address.getText().toString(), aadhar_no.getText().toString());

                    cr.document(email.getText().toString()).set(doctorUser).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(DoctorSignUpActivity.this, "User Registered", Toast.LENGTH_SHORT).show();
                            firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                progressDialog.dismiss();
                                                startActivity(new Intent(DoctorSignUpActivity.this, DoctorLoginActivity.class));
                                            } else {
                                                progressDialog.dismiss();
                                                Toast.makeText(DoctorSignUpActivity.this, "Sign In Failed!", Toast.LENGTH_SHORT).show();
                                                task.getException().getMessage();

                                            }
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(DoctorSignUpActivity.this, "ERROR" + e.toString(),
                                                    Toast.LENGTH_SHORT).show();

                                            Log.d("TAG", e.toString());
                                        }
                                    });

                        }
                    });
                }
                else
                {
                    Toast.makeText(DoctorSignUpActivity.this, "Incorrect Details", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }



    boolean funmail() {
        String s = email.getText().toString();
        int n = s.length();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '@') {
                counter = i;
                break;
            }
        }
        String s1 = "";
        for (int i = counter + 1; i < n; i++)
            s1 += s.charAt(i);

        if (s1.equals("gmail.com") || s1.equals("yahoo.com") || s1.equals("outlook.in") || s1.equals("rediffmail.com") || s1.equals("yahoo.in") || s1.equals("outlook.com") || s1.equals("hotmail.com"))
            return true;
        else {
            email.setError("Invalid E-Mail Adderess");
            return false;

        }
    }
    boolean funname() {
        if (name.getText().toString().isEmpty()) {
            name.setError("Mandatory");
            return false;
        } else
            return true;
    }
    boolean funcontactempty() {
        if (phone_no.getText().toString().equals("") || phone_no.getText().toString().length() != 10) {
            phone_no.setError("10 digits!");
            return false;
        } else
            return true;
    }

    boolean funpass1empty() {
        if(password.getText().toString().equals("") ) {
            password.setError("Mandatory");
            return  false;
        } else
            return true;
    }
    boolean funpass2empty() {
        if(retypePassword.getText().toString().equals("")) {
            retypePassword.setError("Mandatory");
            return  false;
        } else
            return true;
    }

    boolean funpassinvalid() {
        if(password.getText().toString().length()!=6 ||retypePassword.getText().toString().length()!=6) {
            password.setError("Minimum 6 characters!");
            retypePassword.setError("Minimum 6 characters!");
            return  false;
        } else
            return true;
    }
    public void passwordCheck() {
        if (!password.getText().toString().equals(retypePassword.getText().toString()))
            retypePassword.setError("Password's do not match");
    }
}

