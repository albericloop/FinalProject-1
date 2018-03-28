package fr.android.griseau.finalproject;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final EditText edName = (EditText) findViewById(R.id.edName);
        final EditText edSurname = (EditText) findViewById(R.id.edSurname);
        final EditText edEmail = (EditText) findViewById(R.id.edEmail);
        final EditText edPassword = (EditText) findViewById(R.id.edPassword);

        Button btRegister =  findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email=edEmail.getText().toString();
                final String password=edPassword.getText().toString();
                final String surname=edSurname.getText().toString();
                final String name=edName.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "réponse", Toast.LENGTH_LONG).show();

                        try {

                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success)
                            {
                                Toast.makeText(getApplicationContext(), "félicitations vous êtes inscrit!", Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(), "L'inscription a échoué...", Toast.LENGTH_LONG).show();
                                AlertDialog.Builder builder = new AlertDialog.Builder((RegisterActivity.this));
                                builder.setMessage("Register failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Log.e("VOLLEY", "ERROR");
                    }
                };
                RegisterRequest registerRequest = new RegisterRequest(email, password, surname, name, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }
        });
    }
}
