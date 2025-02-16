package ar.edu.unju.edm.trabajofebrero.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
public class Conductor {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido;
  private LocalDate fechaNacimiento;
  private String automovil;
  @Enumerated(EnumType.STRING)
  private TipoAutomovil tipoAutomovil;
  private Boolean estado;

  public Conductor() {
  }
}

enum TipoAutomovil {
  X, Luxe, Premium;
}