package co.edu.udea.estructuras.landresperez.citophoneapp.Data;

/**
 * Created by landres.perez on 18/08/17.
 */

public class UserListData {

    private String numeroApartamento, nombreApartamento;

    public UserListData(String numeroApartamento, String nombreApartamento) {
        this.numeroApartamento = numeroApartamento;
        this.nombreApartamento = nombreApartamento;
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
