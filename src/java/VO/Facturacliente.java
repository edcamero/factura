package VO;
// Generated 28 ene. 2020 12:02:16 by Hibernate Tools 4.3.1


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Facturacliente generated by hbm2java
 */
public class Facturacliente  implements java.io.Serializable {


     private int faclId;
     private Cliente cliente;
     private int faclTotal;
     private Date faclFecha;
     private Date faclFechacambio;
     private String faclRegistradopor;
     private List<Detallefactura> detallefacturas;

    public Facturacliente() {
    }

    public Facturacliente(Cliente cliente) {
        this.cliente = cliente;
        this.faclTotal=0;
        this.detallefacturas=new ArrayList<Detallefactura>();
        this.faclFecha=  new Date();
        this.faclFechacambio=new Date();
    }
  private String fecha(){
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
          String fechaComoCadena = sdf.format(this.faclFecha);
            return fechaComoCadena;
  }
    
     
    public Facturacliente(int faclId, Cliente cliente, int faclTotal, Date faclFecha, Date faclFechacambio, String faclRegistradopor) {
        this.faclId = faclId;
        this.cliente = cliente;
        this.faclTotal = faclTotal;
        this.faclFecha = faclFecha;
        this.faclFechacambio = faclFechacambio;
        this.faclRegistradopor = faclRegistradopor;
        this.detallefacturas=new ArrayList<Detallefactura>(0);
    }

	
    
   
    public int getFaclId() {
        return this.faclId;
    }
    
    public void setFaclId(int faclId) {
        this.faclId = faclId;
    }
    public Cliente getCliente() {
        return this.cliente;
    }
    
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public int getFaclTotal() {
        return this.faclTotal;
    }
    
    public void setFaclTotal(int faclTotal) {
        this.faclTotal = faclTotal;
    }
    public Date getFaclFecha() {
        return this.faclFecha;
    }
    
    public void setFaclFecha(Date faclFecha) {
        this.faclFecha = faclFecha;
    }
    public Date getFaclFechacambio() {
        return this.faclFechacambio;
    }
    
    public void setFaclFechacambio(Date faclFechacambio) {
        this.faclFechacambio = faclFechacambio;
    }
    public String getFaclRegistradopor() {
        return this.faclRegistradopor;
    }
    
    public void setFaclRegistradopor(String faclRegistradopor) {
        this.faclRegistradopor = faclRegistradopor;
    }
    public List<Detallefactura> getDetallefacturas() {
        return this.detallefacturas;
    }
    
    public void setDetallefacturas(ArrayList<Detallefactura> detallefacturas) {
        this.detallefacturas = detallefacturas;
    }

    
    public void AgregarArticulo(int cant,Articulo articulo){
        this.detallefacturas.add(new Detallefactura(articulo, this,cant));
        this.faclTotal=this.faclTotal+(cant*articulo.getArtiValorunitario());
    }

    @Override
    public String toString() {
        return "Facturacliente{" + "faclId=" + faclId + ", cliente=" + cliente + ", faclTotal=" + faclTotal + ", faclFecha=" + faclFecha + ", faclFechacambio=" + faclFechacambio + ", faclRegistradopor=" + faclRegistradopor + '}';
    }

    
    
    



}


