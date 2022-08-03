package com.efhh.bibliotecavirtual.repository;

import com.efhh.bibliotecavirtual.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IVentasRepository extends JpaRepository<Venta,Integer> {
}
