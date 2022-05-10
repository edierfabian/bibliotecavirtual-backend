package com.efhh.bibliotecavirtual.model;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVenta;
    private LocalDateTime fecha;
    private Float total;
    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false,foreignKey =@ForeignKey (name="FK_venta_usuario"))
    private Usuario usuario;

    public Integer getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(Integer idVenta) {
        this.idVenta = idVenta;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
