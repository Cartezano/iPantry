package com.example.peter.ipantry;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import data.Producto;

/**
 * Creado por Peter el 23-07-2017.
 */

public class DescontarProductoActivity extends AppCompatActivity {

    final Activity activity = this;
    private Button btnCam;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descontarproducto);

        btnCam = (Button) findViewById(R.id.btnCam);

        btnCam.performClick();
        btnCam.setFocusable(false);
    }

    public void scanner(View view) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES);
        integrator.setPrompt("Por favor escanee el código ");
        integrator.setCameraId(0);
        integrator.setBeepEnabled(true);
        integrator.setBarcodeImageEnabled(false);
        integrator.setOrientationLocked(false);
        integrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        String code = result.getContents();
        if (result.getContents() == null) {
            Toast.makeText(this, "Escaner Cancelado", Toast.LENGTH_LONG).show();
            finish();
        } else {
            //Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this);
            SQLiteDatabase db = admin.getWritableDatabase();
            int res = admin.descontarCantidadProducto(db,code);

            if (res == -1){
                Toast.makeText(this, "Error! no existe stock del producto", Toast.LENGTH_LONG).show();
            }else if(res > 0){
                Toast.makeText(this, "Cantidad descontada correctamente!", Toast.LENGTH_LONG).show();
                finish();
            }else{
                Toast.makeText(this, "Error! no se pudo realizar la operacion", Toast.LENGTH_LONG).show();
            }

        }
    }
}
