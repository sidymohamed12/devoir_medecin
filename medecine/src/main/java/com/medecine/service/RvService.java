package com.medecine.service;

import java.util.List;

import com.medecine.core.Repository;
import com.medecine.core.Service;
import com.medecine.entities.Rv;

public class RvService implements Service<Rv> {

    private Repository<Rv> rvRepository;

    public RvService(Repository<Rv> rvRepository) {
        this.rvRepository = rvRepository;
    }

    @Override
    public void create(Rv value) {
        rvRepository.insert(value);
    }

    @Override
    public List<Rv> findAll() {
        return rvRepository.selectAll();
    }

    @Override
    public Rv getById(int id) {
        return rvRepository.getById(id);
    }

}
