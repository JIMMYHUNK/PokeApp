package com.example.myapplication2;

import static com.android.volley.Request.Method.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Actividad2 extends AppCompatActivity {
    private TextView tv1;
    private RequestQueue rq;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        tv1=findViewById(R.id.tv1);
        rq = Volley.newRequestQueue(this);
    }

    public void recuperar (View v)
    {
        tv1.setText("");
        String url="https://pokeapi.co/api/v2/pokemon/${5}";
        JsonArrayRequest requerimiento=new JsonArrayRequest(Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        for(int f=0;f<response.length();f++)
                        {
                            try {
                                JSONObject objeto = new JSONObject(response.get(f).toString());
                                tv1.append("name:"+objeto.getString("name")+"\n");
                                tv1.append("url:"+objeto.getString("url")+"\n");
                                tv1.append("______________________________\n");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Actividad2.this,error.getMessage(),Toast.LENGTH_LONG).show();

                    }
                });
        rq.add(requerimiento);
    }
}