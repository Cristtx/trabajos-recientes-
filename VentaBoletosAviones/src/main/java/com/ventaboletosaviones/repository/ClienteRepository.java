package com.ventaboletosaviones.repository;

import com.ventaboletosaviones.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    List<Cliente> findByNombresContainingIgnoreCase(String filtro);
}
