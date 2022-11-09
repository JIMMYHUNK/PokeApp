package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
        EditText usuario,pass;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usuario = findViewById(R.id.usuario);
        pass= findViewById(R.id.pass);
    }

    public void presion(View view)
    {
        if(usuario.getText().toString().equals("jimmy")&& pass.getText().toString().equals("123"))
        {
            Intent intet = new Intent(MainActivity.this, Actividad2.class);
            startActivity(new Intent (MainActivity.this,Actividad2.class));
            Toast.makeText(MainActivity.this, "Todo esta ok",Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_LONG).show();
        }

    }
}