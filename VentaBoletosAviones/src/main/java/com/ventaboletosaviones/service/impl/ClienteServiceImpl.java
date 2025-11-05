package com.ventaboletosaviones.service.impl;

import com.ventaboletosaviones.model.Cliente;
import com.ventaboletosaviones.repository.ClienteRepository;
import com.ventaboletosaviones.service.ClienteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;

    public ClienteServiceImpl(ClienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public Cliente save(Cliente c) {
        return repo.save(c);
    }

    @Override
    public Cliente update(String id, Cliente c) {
        if (!repo.existsById(id)) return null;
        return repo.save(c);
    }

    @Override
    public List<Cliente> findAll() {
        return repo.findAll();
    }

    @Override
    public Cliente findById(String id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }

    @Override
    public List<Cliente> search(String filtro) {
        if (filtro == null || filtro.isBlank()) return repo.findAll();
        return repo.findByNombresContainingIgnoreCase(filtro);
    }
}
