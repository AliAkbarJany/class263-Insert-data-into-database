package com.rafsan.class263insertdataintodatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    Button buttonInsertData;
    ProgressBar progressBar;
    EditText edName,edMobile,edEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonInsertData = findViewById(R.id.buttonInsertData);
        progressBar = findViewById(R.id.progressBar);
        edName = findViewById(R.id.edName);
        edMobile = findViewById(R.id.edMobile);
        edEmail = findViewById(R.id.edEmail);

        buttonInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = edName.getText().toString();
                String phone = edMobile.getText().toString();
                String email = edEmail.getText().toString();

                String url = "https://ali71.000webhostapp.com/apps/data.php?n="+name+"&p="+phone+"&e="+email;

                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressBar.setVisibility(View.GONE);
                                new AlertDialog.Builder(MainActivity.this)
                                        .setTitle("Server response")
                                        .setMessage(response)
                                        .show();


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                if (name.length()>0){
                    queue.add(stringRequest);
                }
                else {
                    edName.setError("Input Your name");
                }



            }
        });

    }
}