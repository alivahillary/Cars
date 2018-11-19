package com.example.android.cars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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

public class RegisterActivity extends AppCompatActivity {

  private  EditText username, email, password;
  private Button btn_register;
  private TextView txt_login;
  private static String URL_REGIST = "https://92494.000webhostapp.com/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

      username =findViewById(R.id.username);
      email =  findViewById(R.id.email);
      password = findViewById(R.id.password);
      btn_register = findViewById(R.id.btn_register);
      txt_login = findViewById(R.id.txt_login);


      txt_login.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
              startActivity(i);
          }
      });

      btn_register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Register();
          }
      });

    }

    private void Register(){

        final String username = this.username.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password= this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if(success.equals("1")){

                                Toast.makeText(RegisterActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity( new Intent(RegisterActivity.this,LoginActivity.class));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Registration Error!" +e.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegisterActivity.this, "Registration Error!" +error.toString(), Toast.LENGTH_SHORT).show();

            }
        })
        {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<>();
               params.put("username",username);
               params.put("email",email);
               params.put("password",password);
               return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);




    }


}
