package kz.bitlab.restG142.service.impl;

import kz.bitlab.restG142.model.Tyres;
import kz.bitlab.restG142.repository.TyresRepository;
import kz.bitlab.restG142.service.TyreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TyreServiceImpl implements TyreService {
    private final TyresRepository tyresRepository;

    @Override
    public Tyres getById(Long id) {
        return tyresRepository.findById(id).orElseThrow(() -> new RuntimeException("Tyre not found"));
    }

    @Override
    public List<Tyres> getAll() {
        return tyresRepository.findAll();
    }

    @Override
    public Tyres add(Tyres tyre) {
        return tyresRepository.save(tyre);
    }

    @Override
    public Void delete(Long id) {
        tyresRepository.deleteById(id);
        return null;
    }

    @Override
    public Tyres update(Tyres tyre) {
        return tyresRepository.save(tyre);
    }
}
