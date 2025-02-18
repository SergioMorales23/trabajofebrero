package ar.edu.unju.edm.trabajofebrero.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.trabajofebrero.model.Viaje;
import ar.edu.unju.edm.trabajofebrero.service.IConductorService;
import ar.edu.unju.edm.trabajofebrero.service.IViajeService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ViajeController {
  @Autowired
  IViajeService viajeService;
  @Autowired
  IConductorService conductorService;

  @GetMapping("/nuevoViaje")
  public ModelAndView getNuevoViajePage() {
    ModelAndView modelView = new ModelAndView("formularioViaje");
    modelView.addObject("viaje", new Viaje());
    modelView.addObject("tipos", List.of("Corta", "Media", "Larga"));
    modelView.addObject("conductores", conductorService.listarConductoresActivos());
    return modelView;
  }

  @PostMapping("/viajes/precio")
  public String postVerificarViaje(@ModelAttribute Viaje viaje, RedirectAttributes redirectAttributes) {
    try {
      System.out.println(viaje.getConductor().getApellido());
      Integer precio = 0;
      if (viaje.getTipo().toString() == "Corta") {
        precio = 7000;
      } else {
        if (viaje.getTipo().toString() == "Media") {
          precio = 10000;
        } else {
          precio = 20000;
        }
      }
      if (viaje.getConductor().getTipoAutomovil().toString() == "Luxe") {
        precio = precio + (precio * 10 / 100);
      } else {
        if (viaje.getConductor().getTipoAutomovil().toString() == "Premium") {
          precio = precio + (precio * 20 / 100);
        }
      }
      List<Viaje> viajes = viajeService.listarViajes();
      for (Viaje v : viajes) {
        if (v.getConductor().getId() == viaje.getConductor().getId() && v.getEstado().equals(true)) {
          throw new RuntimeException("El conductor ya tiene un viaje asignado");
        }
      }
      viaje.setCosto(precio);
      viajeService.guardarViaje(viaje);
      return "redirect:/viajes";
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());

      return "redirect:/nuevoViaje";
    }
  }

  @GetMapping("/viajes")
  public ModelAndView getListaViajes() {
    ModelAndView modelView = new ModelAndView("listadoViajes");
    modelView.addObject("viajes", viajeService.listarViajesActivos());
    return modelView;
  }

  @GetMapping("/viajes/eliminar/{id}")
  public String eliminarViaje(@ModelAttribute Viaje viaje) {
    viajeService.eliminarViaje(viaje.getId());
    return "redirect:/viajes";
  }
}
