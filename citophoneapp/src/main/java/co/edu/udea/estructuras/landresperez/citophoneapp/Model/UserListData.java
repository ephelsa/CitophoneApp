package co.edu.udea.estructuras.landresperez.citophoneapp.Model;

/**
 * Created by landres.perez on 18/08/17.
 */

public class UserListData {

    private String numeroApartamento, nombreApartamento;

    private long numeroTelefono;

    public UserListData() {
    }

    public UserListData(String numeroApartamento, String nombreApartamento, long numeroTelefono) {
        this.numeroApartamento = numeroApartamento;
        this.nombreApartamento = nombreApartamento;
        this.numeroTelefono = numeroTelefono;
    }

    public long getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(long numeroTelefono) {
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
