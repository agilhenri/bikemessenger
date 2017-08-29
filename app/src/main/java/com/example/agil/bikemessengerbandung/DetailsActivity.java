package com.example.agil.bikemessengerbandung;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DetailsActivity extends AppCompatActivity {

    EditText edit_item;
    EditText edit_sendername;
    EditText edit_senderphone;
    EditText edit_senderadd;
    EditText edit_receivername;
    EditText edit_receiverphone;
    EditText edit_receiveradd;
    Button btn_orderdetails;
    private static final String REGISTER_URL="http://agilhenri.com/UserRegistration/order.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        edit_item = (EditText) findViewById(R.id.edit_item);
        edit_sendername = (EditText) findViewById(R.id.edit_sendername);
        edit_senderphone = (EditText) findViewById(R.id.edit_senderphone);
        edit_senderadd = (EditText) findViewById(R.id.edit_senderadd);
        edit_receivername = (EditText) findViewById(R.id.edit_receivername);
        edit_receiverphone = (EditText) findViewById(R.id.edit_reveicerphone);
        edit_receiveradd = (EditText) findViewById(R.id.edit_receiveradd);
        btn_orderdetails = (Button) findViewById(R.id.btn_orderdetails);
        btn_orderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String item = edit_item.getText().toString().trim().toLowerCase();
        String send_name = edit_sendername.getText().toString().trim().toLowerCase();
        String send_phone = edit_senderphone.getText().toString().trim().toLowerCase();
        String send_add = edit_senderadd.getText().toString().trim().toLowerCase();
        String receiver_name = edit_receivername.getText().toString().trim().toLowerCase();
        String receiver_phone = edit_senderphone.getText().toString().trim().toLowerCase();
        String receiver_add = edit_receiveradd.getText().toString().trim().toLowerCase();
        register(item,send_name,send_phone,send_add,receiver_name,receiver_phone,receiver_add);
    }

    private void register(String item, String send_name, String send_phone, String send_add, String receive_name, String receiver_phone, String receiver_add){
        String urlSuffix = "?item=" + item + "&send_name=" + send_name + "&send_phone=" + send_phone + "&send_add=" + send_add + "&receiver_name=" + receive_name + "&receiver_phone=" + receiver_phone + "&receiver_add=" + receiver_add;
        class RegisterUser extends AsyncTask<String, Void, String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(DetailsActivity.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Done", Toast.LENGTH_SHORT).show();
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
