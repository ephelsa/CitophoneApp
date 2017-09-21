package co.edu.udea.estructuras.landresperez.citophoneapp.Model;

import java.io.Serializable;

/**
 * Created by landres.perez on 18/08/17.
 */

public class UserListData implements Serializable {

    private String numeroApartamento, nombreApartamento;

    private String numeroTelefono;

    public UserListData() {
    }

    public UserListData(String numeroApartamento, String nombreApartamento, String numeroTelefono) {
        this.numeroApartamento = numeroApartamento;
        this.nombreApartamento = nombreApartamento;
        this.numeroTelefono = numeroTelefono;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(String numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public String getNombreApartamento() {
        return nombreApartamento;
    }

    public void setNombreApartamento(String nombreApartamento) {
        this.nombreApartamento = nombreApartamento;
    }
}
