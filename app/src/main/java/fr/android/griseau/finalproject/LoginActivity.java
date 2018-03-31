package fr.android.griseau.finalproject;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText edEmail =  findViewById(R.id.edEmail);
        final EditText edPassword =  findViewById(R.id.edPassword);
        final Button btLogin =  findViewById(R.id.btLogin);
        final Button btRegister =  findViewById(R.id.btRegister);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email=edEmail.getText().toString();
                final String password = edPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success)
                            {
                                int ID = jsonResponse.getInt("userID");
                                String surname = jsonResponse.getString("userSurname");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("ID", ID);
                                intent.putExtra("Surname", surname);
                                LoginActivity.this.startActivity(intent);
                            }else
                            {
                                AlertDialog.Builder builder = new AlertDialog.Builder((LoginActivity.this));
                                builder.setMessage("Login failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(email, password, getString(R.string.ip_address), responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);
            }
        });
    }

    public void onClickRegister(View V){
        Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
        LoginActivity.this.startActivity(registerIntent);
    }

}
