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

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmailId,editTextPassword;
    Button buttonLogin;
    String LoginAPI = "http://canada.net.in/api/login.php";
    Spinner spinner;
    ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmailId = findViewById(R.id.editTextEmailId);
        editTextPassword = findViewById(R.id.editTextPassword);
        spinner = findViewById(R.id.spinner);
        String[] loginType = {"Choose Login Type", "Business Login", "Member Login"};
        arrayAdapter = new ArrayAdapter(getApplicationContext(), R.layout.spinner_item,R.id.customSpinnerItemTextView, loginType);
        spinner.setAdapter(arrayAdapter);
        buttonLogin = findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);
        final String email         = editTextEmailId.getText().toString();
        final String password      = editTextPassword.getText().toString();
        final String member        = "2";
        final String business      = "3";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LoginAPI,
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
                                makeText(LoginActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                loading.dismiss();
                                editTextEmailId.setText("");
                                editTextPassword.setText("");
                                spinner.setSelection(0);
                                Intent intent =new Intent(LoginActivity.this, DashBoard_Activity.class);
                                startActivity(intent);
                                makeText(LoginActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                            makeText(getApplicationContext(), "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
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
                    params.put("email", email);
                    params.put("password", password);
                    params.put("role_id", member);
                }
                if (spinner.getSelectedItem().equals("Business Login"))
                {
                    params.put("email", email);
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
