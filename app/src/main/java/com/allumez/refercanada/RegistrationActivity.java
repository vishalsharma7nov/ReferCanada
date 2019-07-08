package com.allumez.refercanada;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    WebView webView;
    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

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

        webView = (WebView)findViewById(R.id.webview);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setDomStorageEnabled(true);
        webView.goBack();
        webView.loadUrl("http://refercanada.com/frontend/register");
        webView.setWebViewClient(new CustomWebViewClient());
        webView.setWebViewClient(new CustomWebViewClient() {
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                try {
                    webView.stopLoading();
                    loading.dismiss();
                } catch (Exception e) {
                }

                if (webView.canGoBack()) {
                    loading.dismiss();
                    webView.goBack();
                }

                webView.loadUrl("about:blank");
                AlertDialog alertDialog = new AlertDialog.Builder(RegistrationActivity.this).create();
                alertDialog.setTitle("Error");
                alertDialog.setMessage("Check your internet connection and try again.");
                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                        loading.dismiss();
                    }
                });

                alertDialog.show();
                super.onReceivedError(webView, errorCode, description, failingUrl);
            }
        });


    }

    public class CustomWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            //TODO: show progress bar here
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //TODO: hide progress bar here
            loading.dismiss();
        }

    }




    public void registration()
    {
        final ProgressDialog loading = ProgressDialog.show(this,"Loading","Please wait...",false,false);

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
                                Intent intent =new Intent(RegistrationActivity.this,DashBoardActivity.class);
                                startActivity(intent);
                                makeText(RegistrationActivity.this, loginMsg, Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            loading.dismiss();
                        }


                    }
                },

                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
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
