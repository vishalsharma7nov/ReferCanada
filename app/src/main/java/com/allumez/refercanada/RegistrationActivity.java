package com.allumez.refercanada;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    String RegistrationAPI = "http://refercanada.com/api/registration.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        editTextFirstName    = (EditText)findViewById(R.id.firstname);
        editTextLastName     = (EditText)findViewById(R.id.lastname);
        editTextEmailId      = (EditText)findViewById(R.id.email);
        editTextMobileNumber = (EditText)findViewById(R.id.mobilenumber);
        editTextPassword     = (EditText)findViewById(R.id.password);

        buttonRegister = (Button)findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registration();

            }
        });

    }

    public void registration()
    {
        final String firstname     = editTextFirstName.getText().toString();
        final String lastname      = editTextLastName.getText().toString();
        final String email         = editTextEmailId.getText().toString();
        final String mobilenumber  = editTextMobileNumber.getText().toString();
        final String password      = editTextPassword.getText().toString();



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
//                                makeText(RegistrationActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                editTextFirstName.setText("");
                                editTextLastName.setText("");
                                editTextEmailId.setText("");
                                editTextMobileNumber.setText("");
                                editTextPassword.setText("");
                                Intent intent =new Intent(RegistrationActivity.this,DashBoardActivity.class);
                                startActivity(intent);
                                makeText(RegistrationActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        makeText(getApplicationContext(), error.getMessage(), LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("fname", firstname);
                params.put("lname", lastname);
                params.put("email", email);
                params.put("mobile", mobilenumber);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }

}
