package ar.edu.unju.edm.trabajofebrero.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajofebrero.model.Conductor;
import ar.edu.unju.edm.trabajofebrero.repository.ConductorRepository;
import ar.edu.unju.edm.trabajofebrero.service.IConductorService;

@Service
public class IConductorServiceImp implements IConductorService {
  @Autowired
  ConductorRepository conductorRepository;

  @Override
  public void guardarConductor(Conductor conductor) {
    conductor.setEstado(true);
    conductorRepository.save(conductor);
  }

  @Override
  public List<Conductor> listarConductores() {
    return (List<Conductor>) conductorRepository.findAll();
  }

  @Override
  public void eliminarConductor(Long id) {
    Conductor conductor = buscarConductor(id);
    if (conductor != null) {
      conductor.setEstado(false);
      conductorRepository.save(conductor);
    }
  }

  @Override
  public Conductor modificarConductor(Conductor conductor) {
    Conductor conductorAModificar = buscarConductor(conductor.getId());
    if (conductorAModificar != null) {
      conductorAModificar = conductor;
      return conductorRepository.save(conductorAModificar);
    }
    return null;
  }

  @Override
  public Conductor buscarConductor(Long id) {
    return conductorRepository.findById(id).orElse(null);
  }

}
