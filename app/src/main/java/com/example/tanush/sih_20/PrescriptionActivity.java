package com.example.tanush.sih_20;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class PrescriptionActivity extends AppCompatActivity {

    private RecyclerView symRecyclerView;
    private SymptomsAdapter symAdapter;


    private RecyclerView.LayoutManager symLayoutManager;


    EditText newText;
    SpeechRecognizer speechRecognizer;
    ArrayList<String> resultSpeech = null;
    String str, json, age, sym[], diag[], adv[], patient, dose[], days[], med[];
    public AlertDialog alertDialog;
    public ArrayList<SymptomsRecyclerItem> symptomsRecyclerList = new ArrayList<>();


    public String s[] = {"hello", "hiii", "hey"};
    public int p;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pres_temp);
        // Intent intent =new Intent();
        //json= intent.getStringExtra("mess");

        json = "{\"Advice\":[\"advice take steam.\"],\"Age\":20,\"Days\":[\"5\"],\"Diagnosis\":[\"diagnosis viral fever.\"],\"Dose\":[\"after lunch\"],\"Medicines\":[\"Alpha tablet\",\"Arixtra 2.5mg Injection\",\"Banocide 50mg Syrup\"],\"PatientID\":\"ABC\",\"PatientName\":\"Pratik Patil\",\"Recommendations\":[[\"Arixtra 2.5mg Injection\",\"Arixtra 7.5mg Injection\"],[\"Banocide 120mg Syrup\",\"Banocide 50mg Syrup\",\"Banocide Pead 50mg Syrup\"]],\"Symptom\":[\"symptom weakness.\"]}";
        convert_json();


        for (int i = 1; i < sym.length; i++) {

            symptomsRecyclerList.add(new SymptomsRecyclerItem(sym[i]));
        }
        for (int i = 1; i < diag.length; i++) {

            symptomsRecyclerList.add(new SymptomsRecyclerItem(diag[i]));
        }
        //for (int i = 0; i < med.length; i++) {

        // Log.e("j",med[i]+days[i]+dose[i]);
        //
        //}
//        symptomsRecyclerList.add(new SymptomsRecyclerItem();
//        Log.e("success", Integer.toString(med.length));

        Log.e("j", String.valueOf(med));
        for (int i = 1; i < adv.length; i++) {

            symptomsRecyclerList.add(new SymptomsRecyclerItem(adv[i]));
        }


        symRecyclerView = findViewById(R.id.recyclerSymptoms);

        symRecyclerView.setHasFixedSize(false);
        symLayoutManager = new LinearLayoutManager(this);
        symAdapter = new SymptomsAdapter(symptomsRecyclerList);
        symRecyclerView.setLayoutManager(symLayoutManager);
        symRecyclerView.setAdapter(symAdapter);

        symAdapter.setOnItemClickListener(new SymptomsAdapter.onItemClickListener1() {
            @Override
            public void onItemClick(int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(PrescriptionActivity.this);
                final View mview = getLayoutInflater().inflate(R.layout.edit_prescription_dialog, null);
                builder.setView(mview);
                ImageButton startRecording = mview.findViewById(R.id.StartRecordingEdit);

                p = position;
                EditText editText = mview.findViewById(R.id.editText);
                Button cancelDialog = mview.findViewById(R.id.cancelDialogEdit);
                Button okDialog = mview.findViewById(R.id.okDialogEdit);

                newText = mview.findViewById(R.id.editText);
                builder.setCancelable(false);
                final AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();

                speechRecognizer = speechRecognizer.createSpeechRecognizer(mview.getContext());
                Intent intent;
                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                startRecording.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        alertDialog.dismiss();
                        Toast.makeText(getBaseContext(), "Starting Prescription", Toast.LENGTH_SHORT).show();
                        speech(p);


                    }
                });
                okDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        symptomsRecyclerList.get(p).changeSymptom(newText.getText().toString());
                        // symptomsRecyclerList.get(p).changeHead()
                        symAdapter.notifyItemChanged(p);
                        alertDialog.dismiss();
                    }
                });
                cancelDialog.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });


            }

        });

        Button btn = findViewById(R.id.mail);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Resources res = getResources();

                String email1 = "patilgpratik@gmail.com";
                String subject = "Voice Based Prescription";
//                String message = "Dear " + par1.getText().toString() + "," + "\nGreetings from PASC!! \n\nThank you for registering for Pulzion'19. \n" + "Your details have been recorded and corresponding payment received. \n\nPlease find below your Registration ID.\n" + "\nRegistration ID: " + rnd + "\nThe above ID is unique to you." + "\n\nYou have participated in the following Event/s:-\n\n" + event + "\n Total Amount Paid:  Rs. " + amount + "\n\nPlease feel free to reach out to us in case of doubts or difficulty.\nHimani Gwalani: 7387664241\nRitik Manghani: 8208641527" + "\n\nAll the Best!!\n\nRegards,\nPICT ACM Student Chapter\n\n\n\n" + dontreply;
                String message = json;
                //Creating SendMail object
                SendMail sm = new SendMail(PrescriptionActivity.this, email1, subject, message);

                //Executing sendmail to send email
                sm.execute();
                Toast.makeText(PrescriptionActivity.this, "E-Mail Sent!!", Toast.LENGTH_LONG).show();
            }
        });


    }

    public void fun(int position) {
        symptomsRecyclerList.get(position).changeSymptom(str);
        symAdapter.notifyItemChanged(position);
    }

    public void speech(int po) {
        p = po;
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 5);
        } else {
            Toast.makeText(getBaseContext(), "Your Device Doesn't Support Speech Intent", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 5) {
            if (resultCode == RESULT_OK && data != null) {
                resultSpeech = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                str = resultSpeech.get(0);
                Log.e("string", str);
                fun(p);

            }
        }
    }


    public void convert_json() {
        try {
            JSONObject j = new JSONObject(json);
            sym = j.getString("Symptom").split("]|,|\\[");
            med = j.getString("Medicines").split("]|,|\\[");
            adv = j.getString("Advice").split("]|,|\\[");
            dose = j.getString("Dose").split("]|,|\\[");
            diag = j.getString("Diagnosis").split("]|,|\\[");
            days = j.getString("Days").split("]|,|\\[");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
