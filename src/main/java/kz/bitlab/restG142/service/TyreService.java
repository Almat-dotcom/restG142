package kz.bitlab.restG142.service;

import kz.bitlab.restG142.model.Tyres;

import java.util.List;

public interface TyreService {
    Tyres getById(Long id);
    List<Tyres> getAll();
    Tyres add(Tyres tyre);
    Void delete(Long id);
    Tyres update(Tyres tyre);
}
