package data;

import android.content.ContentValues;

public class Producto {
    private String codigoProducto;
    private String nombreProducto;
    private String marcaProducto;
    //private String fechaVencimientoEstimadaProducto; <-No implementado aun
    private String imagenProducto;

    public Producto(String codigoProducto, String nombreProducto, String marcaProdcuto,
                    /*String fechaVencimientoEstimadaProducto,*/ String imagenProducto) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProdcuto;
        //this.fechaVencimientoProducto = fechaVencimientoEstimadaProducto;
        this.imagenProducto = imagenProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public String getMarcaProdcuto() {
        return marcaProducto;
    }

    /*
    public String getFechaVencimientoEstimadaProducto() {
        return fechaVencimientoEstimadaProducto;
    }
    */

    public String getImagenProducto() {
        return imagenProducto;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(iPantryContract.ProductoEntry.CODIGO, codigoProducto);
        values.put(iPantryContract.ProductoEntry.NOMBRE, nombreProducto);
        values.put(iPantryContract.ProductoEntry.MARCA, marcaProducto);
        //values.put(iPantryContract.ProductoEntry.FECHA_VENCIMIENTO_ESTIMADA, fechaVencimientoEstimadaProducto);
        values.put(iPantryContract.ProductoEntry.IMAGEN, imagenProducto);
        return values;
    }
}
