package ar.edu.unju.edm.trabajofebrero.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.edm.trabajofebrero.model.Viaje;

@Repository
public interface ViajeRepository extends CrudRepository<Viaje, Long> {

}
