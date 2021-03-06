package com.example.agil.bikemessengerbandung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Signup extends AppCompatActivity {
    EditText edit_username;
    EditText edit_pass;
    EditText edit_email;
    EditText edit_phone;
    Button btn_sign;
    Button btn_login;
    private static final String REGISTER_URL="http://agilhenri.com/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        edit_username = (EditText) findViewById(R.id.id_username);
        edit_pass = (EditText) findViewById(R.id.id_pass);
        edit_email = (EditText) findViewById(R.id.id_email);
        edit_phone = (EditText) findViewById(R.id.id_phone);
        btn_sign = (Button) findViewById(R.id.buttonsignupup);
        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        btn_login=(Button)findViewById(R.id.buttonloginme);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentLogin=new Intent(Signup.this,LoginActivity.class);
                startActivity(intentLogin);
            }
        });
    }

    private void registerUser() {
        String username = edit_username.getText().toString().trim().toLowerCase();
        String password = edit_pass.getText().toString().trim().toLowerCase();
        String email = edit_email.getText().toString().trim().toLowerCase();
        String phone_number = edit_phone.getText().toString().trim().toLowerCase();
        register(username, password, email, phone_number);
    }

    private void register(String username, String password, String email, String phone){
        String urlSuffix = "?username=" + username + "&password=" + password + "&email=" + email + "&phone_number=" + phone;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Signup.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader=null;
                try {
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    return  result;

                }catch (Exception e){
                    return null;
                }
            }

        }
        RegisterUser ur=new RegisterUser();
        ur.execute(urlSuffix);
    }
}
