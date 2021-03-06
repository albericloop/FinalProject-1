package fr.android.griseau.finalproject;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Thomas on 24/03/2018.
 */

public class LoginRequest extends StringRequest {
    private static final String LOGIN_REQUEST_URL = "Login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String password, String IP, Response.Listener<String> listener){
        super(Method.POST, IP + LOGIN_REQUEST_URL, listener, null);
        params=new HashMap<>();
        params.put("email", email);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
