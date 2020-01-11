package com.example.tanush.sih_20;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DashBoardActivity extends AppCompatActivity {

    boolean doubleBackToExitPressedOnce = false;
    SpaceNavigationView navigationView;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser user1;
    ProgressDialog progressDialog;
    FirebaseFirestore db;

    EditText patientID;

    ArrayList<String> resultSpeech = null;
    TextView textDashboard;
    Intent intent;
    SpeechRecognizer speechRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        FirebaseApp.initializeApp(getBaseContext());
        firebaseAuth=FirebaseAuth.getInstance();
        user1 = firebaseAuth.getCurrentUser();
        navigationView=findViewById(R.id.space);
        textDashboard=findViewById(R.id.dashBoardText);





        navigationView.initWithSaveInstanceState(savedInstanceState);
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_history_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_home_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("", R.drawable.ic_favorite_border_black_24dp));
        navigationView.addSpaceItem(new SpaceItem("Assistant Login", R.drawable.ic_assistant));

        navigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                navigationView.setCentreButtonSelectable(true);
                AlertDialog.Builder builder = new AlertDialog.Builder(DashBoardActivity.this);
                final View mview =getLayoutInflater().inflate(R.layout.new_prescription_dialog,null);
                builder.setView(mview);
                ImageButton startRecording=mview.findViewById(R.id.patientStartRecording);
                ImageButton stopRecording=mview.findViewById(R.id.patStopRecording);


                patientID=mview.findViewById(R.id.patId);
                Button cancelDialog=mview.findViewById(R.id.cancelDialog);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));                alertDialog.show();

                speechRecognizer=speechRecognizer.createSpeechRecognizer(mview.getContext());
                Intent intent;
                intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());








//                startRecording.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        db=FirebaseFirestore.getInstance();
//                        db.collection("Patient_Details").get()
//                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                                    @Override
//                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                        if(!queryDocumentSnapshots.isEmpty())
//                                        {
//                                            List<DocumentSnapshot> list=queryDocumentSnapshots.getDocuments();
//                                            for(int i=0;i<list.size();i++)
//                                            {
//                                                if(list.get(i).get("PatientID").equals(patientID.getText().toString()))
//                                                {
//                                                    alertDialog.dismiss();
//                                                    Toast.makeText(getBaseContext(), "Starting Prescription", Toast.LENGTH_SHORT).show();
//                                                    speech();
//                                                    break;
//                                                }
//                                                else {
//                                                    Toast.makeText(getBaseContext(), "Patient ID not found", Toast.LENGTH_SHORT).show(); }
//                                            }
//                                        }
//                                    }
//                                });
//                    }
//                });
                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                if(itemIndex==0)
                    startActivity(new Intent(getBaseContext(),MainActivityKotlin.class));
                else if(itemIndex==1)
                    Toast.makeText(DashBoardActivity.this, "11", Toast.LENGTH_SHORT).show();
                else if(itemIndex==2)
                    Toast.makeText(DashBoardActivity.this, "22", Toast.LENGTH_SHORT).show();
                else if(itemIndex==3)//Assistant Login
                {
                    Toast.makeText(DashBoardActivity.this, "Assistant Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {
                if(itemIndex==0)
                    startActivity(new Intent(getBaseContext(),MainActivityKotlin.class));
                else if(itemIndex==1)
                    Toast.makeText(DashBoardActivity.this, "11", Toast.LENGTH_SHORT).show();
                else if(itemIndex==2)
                    Toast.makeText(DashBoardActivity.this, "22", Toast.LENGTH_SHORT).show();
                else if(itemIndex==3)//Assistant Login
                {
                    Toast.makeText(DashBoardActivity.this, "Assistant Login", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logout) {
            finish();
            FirebaseAuth.getInstance().signOut();

            Intent intent = new Intent(getBaseContext(), DoctorLoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        //super.onCreateOptionsMenu(menu)
        return true;
    }
    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to EXIT", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }


//    public void speech()
//    {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
//        if(intent.resolveActivity(getPackageManager())!=null) {
//            startActivityForResult(intent, 5);
//        }
//        else {
//            Toast.makeText(getBaseContext(),"Your Device Doesn't Support Speech Intent", Toast.LENGTH_SHORT).show();
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(requestCode==5) {
//            if(resultCode==RESULT_OK && data!=null) {
//                resultSpeech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                String str=resultSpeech.get(0);
//                Intent intent = new Intent(getApplicationContext(), MainActivityKotlin.class);
//                intent.putExtra("message", str);
//
//                startActivity(intent);
//            }
//        }
//    }

}
