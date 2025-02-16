package ar.edu.unju.edm.trabajofebrero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.trabajofebrero.model.Conductor;

@Repository
public interface ConductorRepository extends CrudRepository<Conductor, Long> {

}
