package com.example.peter.ipantry;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("iniciando SQLite Helper");

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        admin.setIdUsuario();
    }

    public void agregar_producto(View view) {
        Intent i = new Intent(this, ProductoActivity.class);
        startActivity(i);
    }

    public void listar_producto(View view) {
        Intent i = new Intent(this, ListarProductoActivity.class);
        startActivity(i);
    }

    public void descontar_producto(View view){
        Intent i = new Intent(this, DescontarProductoActivity.class);
        startActivity(i);
    }

    public void onClick(View v) {

        Intent dbmanager = new Intent(this, AndroidDatabaseManager.class);
        startActivity(dbmanager);
    }
}
