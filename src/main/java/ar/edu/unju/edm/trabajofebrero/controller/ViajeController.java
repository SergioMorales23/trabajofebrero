package ar.edu.unju.edm.trabajofebrero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.unju.edm.trabajofebrero.model.Viaje;
import ar.edu.unju.edm.trabajofebrero.service.IConductorService;
import ar.edu.unju.edm.trabajofebrero.service.IViajeService;

public class ViajeController {
  @Autowired
  IViajeService viajeService;
  @Autowired
  IConductorService conductorService;

  @GetMapping("/nuevoViaje")
  public ModelAndView getNuevoViajePage() {
    ModelAndView modelView = new ModelAndView("formularioViaje");
    modelView.addObject("viaje", new Viaje());
    modelView.addObject("tipo", List.of("Corta", "Media", "Larga"));
    modelView.addObject("conductor", conductorService.listarConductores());
    return modelView;
  }
}
