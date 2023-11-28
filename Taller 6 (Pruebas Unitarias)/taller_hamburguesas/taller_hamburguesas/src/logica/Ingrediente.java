package logica;

import java.io.Serializable;

public class Ingrediente implements Serializable {
	private String nombre;
	private int costoAdicional;
	private int calorias;
	
	public Ingrediente(String nombre, int costoAdicional, int calorias) {
		this.nombre = nombre;
		this.costoAdicional = costoAdicional;
		this.calorias = calorias;
	}
	public String getNombre() {
		return this.nombre;
	}
	
	public int getCostoAdicional(){
		return this.costoAdicional;
	}
	public int getCalorias() {
		return this.calorias;
	}
}
