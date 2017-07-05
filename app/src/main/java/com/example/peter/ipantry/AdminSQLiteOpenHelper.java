package com.example.peter.ipantry;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import data.Producto;
import data.Usuario;
import data.ProductoUsuario;
import data.iPantryContract.*;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "ipantry.db";
    public static String idUsuario = "";

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creando BD");
        // Create table Productos
        db.execSQL("CREATE TABLE " + ProductoEntry.TABLE_NAME + "(" +
                ProductoEntry.CODIGO + " TEXT PRIMARY KEY," +
                ProductoEntry.NOMBRE + " TEXT NOT NULL," +
                ProductoEntry.MARCA + " TEXT NOT NULL," +
                //ProductoEntry.FECHA_VENCIMIENTO + "TEXT NOT NULL," +
                ProductoEntry.IMAGEN + " TEXT," +
                "UNIQUE (" + ProductoEntry.CODIGO + "))");
        System.out.println("Tabla Productos creada");
        // Create table Usuarios
        db.execSQL("CREATE TABLE " + UsuarioEntry.TABLE_NAME + "(" +
                UsuarioEntry.IDUSUARIO + " TEXT PRIMARY KEY," +
                UsuarioEntry.USER + " TEXT NOT NULL," +
                UsuarioEntry.PASSWORD + " TEXT NOT NULL," +
                UsuarioEntry.NOMBRE + " TEXT NOT NULL," +
                UsuarioEntry.APELLIDO + " TEXT NOT NULL," +
                UsuarioEntry.CORREO + " TEXT NOT NULL," +
                "CONSTRAINT UC_Usuario UNIQUE (" + UsuarioEntry.IDUSUARIO + "," + UsuarioEntry.USER + "))");

        // Create table ProductoUsuario
        db.execSQL("CREATE TABLE " + ProductoUsuarioEntry.TABLE_NAME + "(" +
                ProductoUsuarioEntry.ID + " TEXT PRIMARY KEY," +
                ProductoUsuarioEntry.CODIGO + " TEXT NOT NULL," +
                ProductoUsuarioEntry.IDUSUARIO + " TEXT NOT NULL," +
                ProductoUsuarioEntry.FECHA_VENCIMENTO + " TEXT," +
                ProductoUsuarioEntry.CANTIDAD + " TEXT NOT NULL," +
                "UNIQUE (" + ProductoUsuarioEntry.ID + ")" +
                "CONSTRAINT FK_PersonOrder FOREIGN KEY (" + ProductoUsuarioEntry.CODIGO + " )" +
                "REFERENCES " + ProductoEntry.TABLE_NAME + "(" + ProductoEntry.CODIGO + ")," +
                "CONSTRAINT FK_PersonOrder FOREIGN KEY (" + ProductoUsuarioEntry.IDUSUARIO + " )" +
                "REFERENCES " + UsuarioEntry.TABLE_NAME + "(" + UsuarioEntry.IDUSUARIO + "))");

        // Insertar datos ficticios para prueba inicial
        baseData(db);
        System.out.println("datos ingresados!!");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {

    }

    private void baseData(SQLiteDatabase db) {
        // Productos
        System.out.println("Ingresando datos");
        dataProductos(db, new Producto("7801620002916", "Jugo Light Tuttifrutilla", "Watts", "watts_tutti_light.jpg"));
        dataProductos(db, new Producto("7802225427197", "Rocklets Mani", "Arcor", "arcor_rocklts_mani.jpg"));
        dataProductos(db, new Producto("7802225587198", "Golpe", "Arcor", "arcor_golpe.jpg"));
        dataProductos(db, new Producto("7790040613706", "Bon o Bon", "Arcor", "arcor_bonobon.jpg"));
        //dataProductos(db, new Producto("asd","asd","asd","asd"));
        //dataProductos(db, new Producto("asd","asd","asd","asd"));
        //dataProductos(db, new Producto("asd","asd","asd","asd"));
        //dataProductos(db, new Producto("asd","asd","asd","asd"));

        // Usuarios
        dataUsuarios(db, new Usuario("admin", "abc123", "Administrador", "iPantry", "jose.marin23@inacapmail.cl"));
    }

    public long dataProductos(SQLiteDatabase db, Producto producto) {
        return db.insert(
                ProductoEntry.TABLE_NAME,
                null,
                producto.toContentValues());
    }

    public long dataUsuarios(SQLiteDatabase db, Usuario usuario) {
        return db.insert(
                UsuarioEntry.TABLE_NAME,
                null,
                usuario.toContentValues());
    }

    public void wea(ProductoUsuario productoUsuario) {
        SQLiteDatabase db = getWritableDatabase();
        // Contenedor de valores
        ContentValues values = new ContentValues();

        // Pares clave-valor
        values.put(ProductoUsuarioEntry.ID, productoUsuario.getIdProductoUsuario());
        values.put(ProductoUsuarioEntry.CODIGO, "7801620002916");
        values.put(ProductoUsuarioEntry.IDUSUARIO, idUsuario);
        values.put(ProductoUsuarioEntry.CANTIDAD, "3");
        values.put(ProductoUsuarioEntry.FECHA_VENCIMENTO, "27.10.2017");

        // Insertar...
        db.insert(ProductoUsuarioEntry.TABLE_NAME, null, values);
    }

    public void setIdUsuario() {
        System.out.println("seteando id de usuario");
        SQLiteDatabase bd = getReadableDatabase();
        Cursor c = bd.rawQuery("SELECT * FROM usuarios WHERE userUsuario LIKE 'admin'", null);
        if (idUsuario.equals("")) {

            if (c != null) {
                System.out.println("if c!= null pasado");
                System.out.println("este es c -> " + c);
                System.out.println("Numero de filas = "+c.getCount());
                if (c.moveToFirst()) {
                    System.out.println("c se movio a la primera fila");
                    do {
                        System.out.println("haciendo 1313");
                        idUsuario = c.getString(c.getColumnIndex(UsuarioEntry.IDUSUARIO));
                        System.out.println("el id del usuario seleccionado es ... ----------->" + idUsuario + "<-------------");
                        //TODO: que no agregue cada vez que se abre la app
                        wea(new ProductoUsuario("7801620002916",idUsuario,"3","27.10.2017"));
                    } while (c.moveToNext());
                }
            }
        }
        bd.close();
    }

    public Producto setProducto(String codigoProducto){
        String nombreProducto = "";
        String marcaProducto = "";
        //String fechaVencimientoEstimadaProducto; <-No implementado aun
        String imagenProducto = "";
        SQLiteDatabase db = getReadableDatabase();
        System.out.println("Ejecutando consulta");
        //Cursor k = db.rawQuery("SELECT * FROM productos",null);
        Cursor c = db.rawQuery("SELECT * FROM productos WHERE codigoProducto LIKE "+ codigoProducto +"",null);
        System.out.println("Consulta = SELECT * FROM productos WHERE codigoProducto LIKE "+ codigoProducto +"");
        //System.out.println("Cantidad filas en K = "+k.getCount());


        if (c != null) {
            System.out.println("Numero de filas en c = "+c.getCount());
            //System.out.println(k.moveToFirst());
            //System.out.println("Fila 1 = "+k.getString(k.getColumnIndex("codigoProducto")));
            if (c.moveToFirst()) {
                System.out.println("c se movio al primero");

                do {
                    System.out.println("Haciendo :X");
                    nombreProducto = c.getString(c.getColumnIndex(ProductoEntry.NOMBRE));
                    marcaProducto = c.getString(c.getColumnIndex(ProductoEntry.MARCA));
                    imagenProducto = c.getString(c.getColumnIndex(ProductoEntry.IMAGEN));
                    System.out.println("Nombre: "+nombreProducto+"Marca: "+marcaProducto);
                    //producto(codigoProducto,nombreProducto,marcaProducto,imagenProducto);
                } while (c.moveToNext());
            }
        }
        db.close();
        return new Producto(codigoProducto,nombreProducto,marcaProducto,imagenProducto);
    }


}
