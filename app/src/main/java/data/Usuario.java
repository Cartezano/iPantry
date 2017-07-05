package data;

import android.content.ContentValues;

import java.util.UUID;

public class Usuario {
    private String idUsuario;
    private String userUsuario;
    private String passwordUsuario;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String correoUsuario;

    public Usuario(String userUsuario, String passwordUsuario, String nombreUsuario, String apellidoUsuario, String correoUsuario) {
        this.idUsuario = UUID.randomUUID().toString();
        this.userUsuario = userUsuario;
        this.passwordUsuario = passwordUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidoUsuario = apellidoUsuario;
        this.correoUsuario = correoUsuario;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public String getUserUsuario() {
        return userUsuario;
    }

    public String getPasswordUsuario() {
        return passwordUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(iPantryContract.UsuarioEntry.IDUSUARIO, idUsuario);
        values.put(iPantryContract.UsuarioEntry.USER, userUsuario);
        values.put(iPantryContract.UsuarioEntry.PASSWORD, passwordUsuario);
        values.put(iPantryContract.UsuarioEntry.NOMBRE, nombreUsuario);
        values.put(iPantryContract.UsuarioEntry.APELLIDO, apellidoUsuario);
        values.put(iPantryContract.UsuarioEntry.CORREO, correoUsuario);
        return values;
    }

}
