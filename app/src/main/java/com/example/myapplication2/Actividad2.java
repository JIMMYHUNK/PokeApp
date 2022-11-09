package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Actividad2 extends AppCompatActivity {
    private TextView name;
    private TextView link;
    private TextView tipe;
    private RequestQueue rq;
    private RequestManager glide;
    private ImageView image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        name = findViewById(R.id.vname);
        link = findViewById(R.id.vurl);
        tipe = findViewById(R.id.vtipe);
        image = findViewById(R.id.imageView4);
        rq = Volley.newRequestQueue(this);
        glide = Glide.with(this);
        this.traerPokemones();
    }

    public void traerPokemones() {
        name.setText("");
        link.setText("");
        tipe.setText("");
        Random random = new Random();
        int pokemonRandom = random.nextInt(905) + 1;
        String url = "https://pokeapi.co/api/v2/pokemon/" + pokemonRandom;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            name.append(response.getString("name"));
                            link.append(url.toString());
                            glide.load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/"+pokemonRandom+".png").into(image);
                            for(int f=0;f<response.getJSONArray("types").length();f++)
                            {
                                try {
                                    JSONObject objeto = new JSONObject(response.getJSONArray("types").get(f).toString());
                                    tipe.append(objeto.getJSONObject("type").getString("name")+"\n");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }


                            tipe.append(response.getJSONObject("types").getJSONObject("type").getString("name"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        rq.add(jsonObjectRequest);



    }

    public void recuperar(View v) {
        this.traerPokemones();
    }
}