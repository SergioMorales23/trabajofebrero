package ar.edu.unju.edm.trabajofebrero.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
public class Viaje {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Enumerated(EnumType.STRING)
  private Tipo tipo;
  @Enumerated(EnumType.STRING)
  private Costo costo;
  @OneToOne
  private Conductor conductor;
  private Boolean estado;

  public Viaje() {
  }
}

enum Tipo {
  Corta, Media, Larga;
}

enum Costo {
  SIETE_MIL, DIEZ_MIL, VEINTE_MIL
}