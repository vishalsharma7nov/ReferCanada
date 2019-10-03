package com.allumez.refercanada;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class RegistrationActivity extends AppCompatActivity {

    EditText editTextFirstName,editTextLastName,editTextEmailId,editTextMobileNumber,editTextPassword;
    Button  buttonRegister;
    String RegistrationAPI = "http://canada.net.in/api/registration.php";
    ArrayAdapter arrayAdapter;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        editTextFirstName    = findViewById(R.id.firstname);
        editTextLastName     = findViewById(R.id.lastname);
        editTextEmailId      = findViewById(R.id.email);
        editTextMobileNumber = findViewById(R.id.mobilenumber);
        editTextPassword     = findViewById(R.id.password);
        spinner  = findViewById(R.id.spinner);
        buttonRegister = findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();

            }
        });
        String[] loginType ={"Choose Login Type","Business Login", "Member Login"};
        arrayAdapter = new ArrayAdapter(getApplicationContext(),R.layout.spinner_item,R.id.customSpinnerItemTextView,loginType);
        spinner.setAdapter(arrayAdapter);
    }
    public void registration()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String firstname     = editTextFirstName.getText().toString();
        final String lastname      = editTextLastName.getText().toString();
        final String email         = editTextEmailId.getText().toString();
        final String mobilenumber  = editTextMobileNumber.getText().toString();
        final String password      = editTextPassword.getText().toString();
        final String member        = "2";
        final String business      = "3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, RegistrationAPI,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject obj = new JSONObject(response);
                            int abc = Integer.parseInt(obj.getString("status"));
                            String loginMsg = obj.getString("msg");
                            if (abc !=1 )
                            {
                                loading.dismiss();
                                makeText(RegistrationActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                editTextFirstName.setText("");
                                editTextLastName.setText("");
                                editTextEmailId.setText("");
                                editTextMobileNumber.setText("");
                                editTextPassword.setText("");
                                spinner.setSelection(0);
                                Intent intent =new Intent(RegistrationActivity.this,LoginActivity.class);
                                startActivity(intent);
                                makeText(RegistrationActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                            loading.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        makeText(getApplicationContext(), "Error "+error.getMessage(), LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                if (spinner.getSelectedItem().equals("Member Login")) {
                    params.put("fname", firstname);
                    params.put("lname", lastname);
                    params.put("email", email);
                    params.put("mobile", mobilenumber);
                    params.put("password", password);
                    params.put("role_id", member);
                }
                if (spinner.getSelectedItem().equals("Business Login"))
                {
                    params.put("fname", firstname);
                    params.put("lname", lastname);
                    params.put("email", email);
                    params.put("mobile", mobilenumber);
                    params.put("password", password);
                    params.put("role_id", business);
                }
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
