package com.example.peter.ipantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void agregar_producto(View view) {
        Intent i = new Intent(this, ProductoActivity.class );
        startActivity(i);
    }

    public void listar_producto(View view) {
        Intent i = new Intent(this, ListarProductoActivity.class);
        startActivity(i);
    }
}
