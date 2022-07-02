package com.efhh.bibliotecavirtual.model;

import javax.persistence.*;

@Entity
public class ItemVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idItemVenta;
    private float precio;

    @Column(name="numDescDisp")
    private Integer numDescargaDisponible;


    @ManyToOne
    @JoinColumn(name = "id_libro", nullable = false,foreignKey =@ForeignKey (name="FK_itemventa_libro"))
    private Libro libro;
    @ManyToOne
    @JoinColumn(name = "id_venta",  nullable = false,foreignKey =@ForeignKey (name="FK_itemventa_venta"))
    private Venta venta;

    public Integer getIdItemVenta() {
        return idItemVenta;
    }

    public void setIdItemVenta(Integer idItemVenta) {
        this.idItemVenta = idItemVenta;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Integer getNumDescargaDisponible() {
        return numDescargaDisponible;
    }

    public void setNumDescargaDisponible(Integer numDescargaDisponible) {
        this.numDescargaDisponible = numDescargaDisponible;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }
}
