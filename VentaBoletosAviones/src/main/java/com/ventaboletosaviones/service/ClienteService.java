package com.ventaboletosaviones.service;

import com.ventaboletosaviones.model.Cliente;
import java.util.List;

public interface ClienteService {
    Cliente save(Cliente c);
    Cliente update(String id, Cliente c);
    List<Cliente> findAll();
    Cliente findById(String id);
    void delete(String id);
    List<Cliente> search(String filtro);
}
