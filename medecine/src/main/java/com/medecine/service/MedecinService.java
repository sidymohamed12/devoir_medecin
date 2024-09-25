package com.medecine.service;

import java.util.List;

import com.medecine.core.Repository;
import com.medecine.core.Service;
import com.medecine.entities.Medecin;

public class MedecinService implements Service<Medecin> {
    private Repository<Medecin> repository;

    public MedecinService(Repository<Medecin> repository) {
        this.repository = repository;
    }

    @Override
    public void create(Medecin value) {
        repository.insert(value);
    }

    @Override
    public List<Medecin> findAll() {
        return repository.selectAll();
    }

    @Override
    public Medecin getById(int id) {
        return repository.getById(id);
    }

}
