package ar.edu.unju.edm.trabajofebrero.service;

import java.util.List;

import ar.edu.unju.edm.trabajofebrero.model.Viaje;

public interface IViajeService {
  public void guardarViaje(Viaje viaje);

  public Viaje buscarViaje(Long id);

  public void eliminarViaje(Long id);

  public Viaje modificarViaje(Viaje viaje);

  public List<Viaje> listarViajesActivos();

  public List<Viaje> listarViajes();
}
