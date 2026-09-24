package entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Enfermera {
    private int idEnfermera;
    private String nombres;
    private String apellidos;
    private String dni;
    private LocalDate fechaNacimiento;
    private String especialidad;
    private String telefono;
    private String turno;
}