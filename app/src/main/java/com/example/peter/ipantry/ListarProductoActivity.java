package com.example.peter.ipantry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class ListarProductoActivity extends AppCompatActivity {

    private ArrayList<String> productos;
    private ArrayAdapter<String> adaptador;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarproducto);

        productos = new ArrayList<String>();
        listarProducto();
        adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,productos);
        lv = (ListView)findViewById(R.id.lvProductos);
        lv.setAdapter(adaptador);
    }

    //TODO: obtener tabla y mostrar

    public void listarProducto() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        Cursor c = bd.rawQuery("select * from 'productos'",null);

        if (c != null ) {
            if  (c.moveToFirst()) {
                do {
                    String nombre = c.getString(c.getColumnIndex("nombreProducto"));
                    String marca = c.getString(c.getColumnIndex("marcaProducto"));
                    String cantidad = c.getString(c.getColumnIndex("cantidadProducto"));
                    String fecha = c.getString(c.getColumnIndex("fechaVencimientoProducto"));
                    productos.add("Nombre: " + nombre + "\nMarca: " + marca + "\nCantidad: "+ cantidad + "\nFecha Vencimiento: " + fecha);
                }while (c.moveToNext());
            }
        }

        /*resultSet.moveToFirst();
        while (resultSet.moveToLast()){
            int i=0;
            productos.add(resultSet.getString(i));
            i++;
        }*/
        //productos.add(et1.getText().toString());
        //adaptador.notifyDataSetChanged();
    }
}
