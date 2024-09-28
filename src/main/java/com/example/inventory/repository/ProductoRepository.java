package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.inventory.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Aquí puedes definir métodos personalizados si lo necesitas
}
