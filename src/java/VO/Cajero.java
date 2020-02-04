package VO;
// Generated 28 ene. 2020 12:02:16 by Hibernate Tools 4.3.1



import java.util.Date;

/**
 * Cajero generated by hbm2java
 */
public class Cajero  implements java.io.Serializable {


     private int cajeId;
     private String username;
     private String cajeNombre;
     private String cajeApellido;
     private String cajePassword;
     private Date cajeFechacambio;
     private String cajeRegistradopor;

    public Cajero() {
    }

    public Cajero(int cajeId, String cajeNombre, String cajeApellido, String cajePassword) {
        this.cajeId = cajeId;
        this.cajeNombre = cajeNombre;
        this.cajeApellido = cajeApellido;
        this.cajePassword = cajePassword;
    }

    public Cajero(int cajeId, String username, String cajeNombre, String cajeApellido, String cajePassword, Date cajeFechacambio, String cajeRegistradopor) {
        this.cajeId = cajeId;
        this.username = username;
        this.cajeNombre = cajeNombre;
        this.cajeApellido = cajeApellido;
        this.cajePassword = cajePassword;
        this.cajeFechacambio = cajeFechacambio;
        this.cajeRegistradopor = cajeRegistradopor;
    }

	
    
    
   
    public int getCajeId() {
        return this.cajeId;
    }
    
    public void setCajeId(int cajeId) {
        this.cajeId = cajeId;
    }
    public String getCajeNombre() {
        return this.cajeNombre;
    }
    
    public void setCajeNombre(String cajeNombre) {
        this.cajeNombre = cajeNombre;
    }
    public String getCajeApellido() {
        return this.cajeApellido;
    }
    
    public void setCajeApellido(String cajeApellido) {
        this.cajeApellido = cajeApellido;
    }
    public String getCajePassword() {
        return this.cajePassword;
    }
    
    public void setCajePassword(String cajePassword) {
        this.cajePassword = cajePassword;
    }
    public Date getCajeFechacambio() {
        return this.cajeFechacambio;
    }
    
    public void setCajeFechacambio(Date cajeFechacambio) {
        this.cajeFechacambio = cajeFechacambio;
    }
    public String getCajeRegistradopor() {
        return this.cajeRegistradopor;
    }
    
    public void setCajeRegistradopor(String cajeRegistradopor) {
        this.cajeRegistradopor = cajeRegistradopor;
    }




}


