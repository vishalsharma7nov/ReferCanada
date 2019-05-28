package com.allumez.refercanada;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmailId,editTextPassword;
    Button buttonLogin;
    String LoginAPI = "http://refercanada.com/api/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmailId  = (EditText)findViewById(R.id.editTextEmailId);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);

        buttonLogin = (Button)findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }

    public void login()
    {
        final String email         = editTextEmailId.getText().toString();
        final String password      = editTextPassword.getText().toString();

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
                                makeText(LoginActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                editTextEmailId.setText("");
                                editTextPassword.setText("");
                                Intent intent =new Intent(LoginActivity.this,DashBoardActivity.class);
                                startActivity(intent);
                                makeText(LoginActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
