package entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Director {

	private int idDirector;
	private String nombre;
	private String email;
	private Tipo tipo;
}