package ar.edu.unju.edm.trabajofebrero.service.Imp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.trabajofebrero.model.Viaje;
import ar.edu.unju.edm.trabajofebrero.repository.ViajeRepository;
import ar.edu.unju.edm.trabajofebrero.service.IViajeService;

@Service
public class IViajeServiceImp implements IViajeService {
  @Autowired
  ViajeRepository viajeRepository;

  @Override
  public void guardarViaje(Viaje viaje) {
    viaje.setEstado(true);
    viajeRepository.save(viaje);
  }

  @Override
  public Viaje buscarViaje(Long id) {
    return viajeRepository.findById(id).orElse(null);
  }

  @Override
  public void eliminarViaje(Long id) {
    Viaje viaje = buscarViaje(id);
    if (viaje != null) {
      viaje.setEstado(false);
      viajeRepository.save(viaje);
    }
  }

  @Override
  public Viaje modificarViaje(Viaje viaje) {
    Viaje viajeAModificar = buscarViaje(viaje.getId());
    if (viajeAModificar != null) {
      viajeAModificar = viaje;
      return viajeRepository.save(viajeAModificar);
    }
    return null;
  }

  @Override
  public List<Viaje> listarViajesActivos() {
    List<Viaje> viajes = (List<Viaje>) viajeRepository.findAll();
    List<Viaje> viajesActivos = new ArrayList<Viaje>();
    for (Viaje v : viajes) {
      if (v.getEstado().equals(true)) {
        viajesActivos.add(v);
      }
    }
    return viajesActivos;
  }

  @Override
  public List<Viaje> listarViajes() {
    return (List<Viaje>) viajeRepository.findAll();
  }

}
