package logica;

import java.io.Serializable;

public class Bebidas implements Producto, Serializable {
	private int precio;
	private String nombre;
	private int calorias;
	
	public Bebidas(String nombre, int precio, int calorias) {
		this.precio = precio;
		this.nombre = nombre;
		this.calorias = calorias;
	}
	public int getPrecio() {
		// TODO Auto-generated method stub
		return this.precio;
	}

	@Override
	public String generarTextoFactura() {
		String textoBebida = this.nombre + ": " + Integer.toString(this.precio) + "\n";
		return textoBebida;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return this.calorias;
	}

}
