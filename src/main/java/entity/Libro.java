package entity;


import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class Libro {
    private int idLibro;
    private String registro;
    private String titulo;
    private String pais;
    private String autor;
    private LocalDate fechaCreacion;
}
