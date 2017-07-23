package data;

import android.content.ContentValues;

import java.util.UUID;

public class ProductoUsuario {
    private String idProductoUsuario;
    private String codigoProducto;
    private String idUsuario;
    private String fechaVencimientoProducto;
    private String cantidadProducto;

    public ProductoUsuario(String codigoProducto, String codigoUsuario, String fechaVencimientoProducto,
    String cantidadProducto) {
        this.idProductoUsuario = UUID.randomUUID().toString();
        this.codigoProducto = codigoProducto;
        this.idUsuario = codigoUsuario;
        this.fechaVencimientoProducto = fechaVencimientoProducto;
        this.cantidadProducto = cantidadProducto;
    }

    // Getter

    public String getIdProductoUsuario() {
        return idProductoUsuario;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public String getCodigoUsuario() {
        return idUsuario;
    }

    public String getFechaVencimientoProducto() {
        return fechaVencimientoProducto;
    }

    public String getCantidadProducto() {
        return cantidadProducto;
    }

    // Setter

    public void setIdProductoUsuario(String idProductoUsuario) {
        this.idProductoUsuario = idProductoUsuario;
    }

    public void setCantidadProducto(String cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }
}
