package ies.thiar.concesionario.model;

import jakarta.persistence.Entity;

@Entity
public class PagarTarjeta extends  Pagable{

    @Override
    public void pagar(double cantidad) {
        System.out.println("Pagas en tarjeta: "+cantidad);
    }

    @Override
    public int formaPago() {
        return 0;
    }
}
