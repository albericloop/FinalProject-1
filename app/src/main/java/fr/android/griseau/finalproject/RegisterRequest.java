package fr.android.griseau.finalproject;

import android.app.Activity;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 24/03/2018.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://10.4.184.112:8888/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String email, String password, String surname, String name, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);

        params=new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("surname", surname);
        params.put("name", name);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
