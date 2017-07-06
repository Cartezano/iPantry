package com.example.peter.ipantry;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import data.Producto;
import data.iPantryContract.*;

public class ProductoActivity extends AppCompatActivity {

    private EditText txtNombre, txtMarca, txtCantidad, txtCodigo;
    private DatePicker dpFechaVencimiento;
    private Button btnSalir;
    final Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtMarca = (EditText) findViewById(R.id.txtMarca);
        txtCantidad = (EditText) findViewById(R.id.txtCantidad);
        dpFechaVencimiento = (DatePicker) findViewById(R.id.dpFechaVencimiento);
        btnSalir = (Button) findViewById(R.id.btnVolverMain);

        txtCodigo.setKeyListener(null);
        txtNombre.setKeyListener(null);
        txtMarca.setKeyListener(null);

        setConfDatePick();

        txtCodigo.performClick();
        txtCodigo.setFocusable(false);
    }

    public void scan(View view) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.setPrompt("Scan");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    public void agregar(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
        ContentValues cadena = new ContentValues();
        SQLiteDatabase bd = admin.getWritableDatabase();
        String codigo = txtCodigo.getText().toString();
        String cantidad = txtCantidad.getText().toString();
        String fechaVencimiento = getFecha();
        cadena.put(ProductoUsuarioEntry.CODIGO, codigo);
        cadena.put(ProductoUsuarioEntry.IDUSUARIO, AdminSQLiteOpenHelper.idUsuario);
        cadena.put(ProductoUsuarioEntry.CANTIDAD, cantidad);
        cadena.put(ProductoUsuarioEntry.FECHA_VENCIMENTO, fechaVencimiento);
        //System.out.println(codigo + ", " + AdminSQLiteOpenHelper.idUsuario + ", " + cantidad + ", " + fechaVencimiento);
        long resp;
        if (!cantidad.isEmpty()) {
            int i = Integer.parseInt(cantidad);
            if (i != 0) {
                //TODO: implementar agregar producto existente sume en vez de crear uno nuevo
                //if (admin.existeProducto(bd,codigo,fechaVencimiento)) {
                    //resp = bd.update(Stri)
                //}else{
                    resp = bd.insert(ProductoUsuarioEntry.TABLE_NAME, null, cadena);
                    bd.close();
                    if (resp >= 0) {
                        Toast.makeText(this, "Producto agregado correctamente", Toast.LENGTH_SHORT).show();
                        limpiar();
                    }else if (resp == -1) {
                        Toast.makeText(this, "No se pudo ingresar el producto :(", Toast.LENGTH_SHORT).show();
                    }
                //}
            }else{
                Toast.makeText(this, "Error!: Cantidad no puede ser 0", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Error!: Cantidad no puede estar vacia", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String code = result.getContents();
        if (result.getContents() == null) {
            Toast.makeText(this, "Escaner Cancelado", Toast.LENGTH_LONG).show();
            btnSalir.performClick();
        } else {
            Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
            Producto producto = admin.setProducto(code);
            System.out.println();
            System.out.println("Codigo de barras = " + result.getContents() + "");
            txtCodigo.setText(code);
            txtNombre.setText(producto.getNombreProducto());
            System.out.println("Nombre Producto:" + txtNombre.getText());
            txtMarca.setText(producto.getMarcaProdcuto());
        }
    }

    public void salir(View v) {
        finish();
    }

    private String getFecha(){
        String fecha;
        int day = dpFechaVencimiento.getDayOfMonth();
        int month = dpFechaVencimiento.getMonth();
        int year = dpFechaVencimiento.getYear();
        fecha = year + "-" + month + "-" +day;
        return fecha;
    }

    private void setConfDatePick(){
        //final Calendar c = Calendar.getInstance();
        dpFechaVencimiento.setMinDate(System.currentTimeMillis() - 1000);
        //dpFechaVencimiento.setMaxDate(System.currentTimeMillis());
    }

    private void limpiar(){
        txtCodigo.setText("");
        txtNombre.setText("");
        txtMarca.setText("");
        txtCantidad.setText("");
        txtCodigo.performClick();
    }
}
