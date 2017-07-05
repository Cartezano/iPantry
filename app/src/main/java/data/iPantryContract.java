package data;

 /*
    Declaracion de la BD
 */

import android.provider.BaseColumns;

public class iPantryContract {
    public static abstract class ProductoEntry implements BaseColumns{
        public static final String TABLE_NAME = "productos";

        public static final String CODIGO = "codigoProducto";
        public static final String NOMBRE = "nombreProducto";
        public static final String MARCA = "marcaProducto";
        //public static final String FECHA_VENCIMIENTO_ESTIMADA = "fechaVencimientoEstimadaProducto";
        public static final String IMAGEN = "imagenProducto";
    }

    public static abstract class UsuarioEntry implements  BaseColumns{
        public static final String TABLE_NAME = "usuarios";

        public static final String IDUSUARIO = "idUsuario";
        public static final String USER = "userUsuario";
        public static final String PASSWORD = "passwordUsuario";
        public static final String NOMBRE = "nombreUsuario";
        public static final String APELLIDO = "apellidoUsuario";
        public static final String CORREO = "correoUsuario";
    }

    public static abstract class ProductoUsuarioEntry implements  BaseColumns{
        public static final String TABLE_NAME = "productoUsuario";

        public static final String ID = "idProductoUsuario";
        public static final String CODIGO = "codigoProducto";
        public static final String IDUSUARIO = "idUsuario";
        public static final String FECHA_VENCIMENTO = "fechaVencimientoProducto";
        public static final String CANTIDAD = "cantidadProducto";
    }
}
