package com.example.peter.ipantry;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

import data.iPantryContract.*;

public class ListarProductoActivity extends AppCompatActivity {

    private ArrayList<String> productos;
    protected ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listarproducto);

        productos = new ArrayList<>();
        listarProducto();
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, productos);
        lv = (ListView)findViewById(R.id.lvProductos);
        lv.setAdapter(adaptador);
    }

    public void listarProducto() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        SQLiteDatabase bd = admin.getReadableDatabase();

        Cursor c = bd.rawQuery(
                "SELECT " + ProductoEntry.TABLE_NAME + "." + ProductoEntry.CODIGO +"," +
                        ProductoEntry.TABLE_NAME + "." + ProductoEntry.NOMBRE +"," +
                        ProductoEntry.TABLE_NAME + "." + ProductoEntry.MARCA + "," +
                        ProductoEntry.TABLE_NAME + "." + ProductoEntry.IMAGEN + "," +
                        ProductoUsuarioEntry.TABLE_NAME + "." + ProductoUsuarioEntry.CODIGO + "," +
                        ProductoUsuarioEntry.TABLE_NAME + "." + ProductoUsuarioEntry.CANTIDAD + "," +
                        ProductoUsuarioEntry.TABLE_NAME + "." + ProductoUsuarioEntry.FECHA_VENCIMENTO +
                " FROM " + ProductoEntry.TABLE_NAME +
                " INNER JOIN " + ProductoUsuarioEntry.TABLE_NAME +
                        " ON " + ProductoEntry.TABLE_NAME + "." + ProductoEntry.CODIGO + " = " +
                        ProductoUsuarioEntry.TABLE_NAME + "." + ProductoUsuarioEntry.CODIGO +
                        " ORDER BY '" + ProductoUsuarioEntry.FECHA_VENCIMENTO + "' ASC"
                ,null);

        if (c != null ) {
            System.out.println("c != null");
            if  (c.moveToFirst()) {
                System.out.println("c movetofirst c = "+c.getCount());
                do {
                    String nombre = c.getString(c.getColumnIndex(ProductoEntry.NOMBRE));
                    String marca = c.getString(c.getColumnIndex(ProductoEntry.MARCA));
                    String cantidad = c.getString(c.getColumnIndex(ProductoUsuarioEntry.CANTIDAD));
                    String fecha = c.getString(c.getColumnIndex(ProductoUsuarioEntry.FECHA_VENCIMENTO));
                    productos.add("Nombre: " + nombre +
                            "\nMarca: " + marca +
                            "\nCantidad: "+ cantidad +
                            "\nFecha Vencimiento: " + fecha);
                }while (c.moveToNext());
            }
        }
        bd.close();
    }
}
