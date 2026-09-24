package entity;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Publicacion {
    private int idPublicacion;
    private String usuario;
    private String titulo;
    private String contenido;
    private String referencias;
    private LocalDate fecha;
}

	
