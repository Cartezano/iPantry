package com.example.peter.ipantry;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProductoActivity extends AppCompatActivity {

    private EditText txtNombre,txtMarca,txtCantidad, txtFechaVencimiento, txtCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        txtCodigo=(EditText)findViewById(R.id.txtCodigo);
        txtNombre=(EditText)findViewById(R.id.txtNombre);
        txtMarca=(EditText)findViewById(R.id.txtMarca);
        txtCantidad=(EditText)findViewById(R.id.txtCantidad);
        txtFechaVencimiento = (EditText)findViewById(R.id.txtFechaVencimiento);
    }

    public void agregar(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        System.out.println(this.databaseList().toString());
        ContentValues cadena = new ContentValues();
        SQLiteDatabase bd = admin.getWritableDatabase();
        int codigo= Integer.parseInt(txtCodigo.getText().toString());
        String nombre= txtNombre.getText().toString();
        String marca= txtMarca.getText().toString();
        String cantidad= txtCantidad.getText().toString();
        String fechaVencimiento= txtFechaVencimiento.getText().toString();
        cadena.put("codigoProducto",codigo);
        cadena.put("nombreProducto",nombre);
        cadena.put("marcaProducto",marca);
        cadena.put("cantidadProducto",cantidad);
        cadena.put("fechaVencimientoProducto",fechaVencimiento);
        System.out.println(codigo+", "+nombre+", "+marca+", "+cantidad+", "+fechaVencimiento);
        long resp;
        resp = bd.insert("productos",null,cadena);
        bd.close();
        if (resp >= 1)
            Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
        else if (resp == -1)
            Toast.makeText(this, "No se pudo ingresar el producto :(", Toast.LENGTH_SHORT).show();
    }

    public void salir(View v) {
        finish();
    }
}
