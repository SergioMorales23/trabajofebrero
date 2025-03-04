package ar.edu.unju.edm.trabajofebrero.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ar.edu.unju.edm.trabajofebrero.model.Conductor;
import ar.edu.unju.edm.trabajofebrero.service.IConductorService;

@Controller // anotacion para indicar que es un controlador
public class ConductorController {
  @Autowired // inyeccion de dependencia
  IConductorService conductorService;

  @GetMapping("/nuevoConductor")
  public ModelAndView getNuevoConductor() {
    ModelAndView modelView = new ModelAndView("formularioConductor");
    modelView.addObject("conductor", new Conductor());
    modelView.addObject("tipos", List.of("X", "Luxe", "Premium"));
    return modelView;
  }

  @PostMapping("/conductores/guardar")
  public String postGuardarConductor(Conductor conductor, RedirectAttributes redirectAttributes) {
    try {
      if (LocalDate.now().getYear() - conductor.getFechaNacimiento().getYear() < 18) {
        throw new RuntimeException("El conductor no puede ser menor de edad");
      }
      conductorService.guardarConductor(conductor);
      return "redirect:/conductores";
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("mensajeError", e.getMessage());

      return "redirect:/nuevoConductor";
    }
  }

  @GetMapping("/conductores")
  public ModelAndView getListaConductores() {
    ModelAndView modelView = new ModelAndView("listadoConductores");
    modelView.addObject("conductores", conductorService.listarConductoresActivos());
    return modelView;
  }

  @GetMapping("/conductores/eliminar/{id}")
  public String eliminarConductor(@PathVariable(name = "id") Long id) {
    conductorService.eliminarConductor(id);
    return "redirect:/conductores";
  }

  @GetMapping("/conductores/editar/{id}")
  public ModelAndView getFormularioEditarConductor(@PathVariable(name = "id") Long id) {
    ModelAndView modelView = new ModelAndView("formularioConductor");
    modelView.addObject("conductor", conductorService.buscarConductor(id));
    modelView.addObject("tipos", List.of("X", "Luxe", "Premium"));
    return modelView;
  }
}
