package ies.thiar.concesionario.model;

import jakarta.persistence.Entity;

@Entity
public class PagarEfectivo extends  Pagable{
    @Override
    public void pagar(double cantidad) {
        System.out.println("Pagas en efectivo: "+cantidad);
    }

    @Override
    public int formaPago() {
        return 1;
    }
}
