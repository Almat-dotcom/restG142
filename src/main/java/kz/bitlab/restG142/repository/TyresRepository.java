package kz.bitlab.restG142.repository;

import kz.bitlab.restG142.model.Tyres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TyresRepository extends JpaRepository<Tyres, Long> {
}
