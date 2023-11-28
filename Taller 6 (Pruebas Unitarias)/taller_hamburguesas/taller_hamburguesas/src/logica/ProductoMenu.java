package logica;

import java.io.Serializable;

public class ProductoMenu implements Producto, Serializable {
	private String nombre;
	private int precioBase;
	private int calorias;
	
	
	public ProductoMenu (String nombre, int precioBase, int calorias) {
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.calorias=calorias;
	}
	public int getPrecio() {
		
		return this.precioBase;
	}

	@Override
	public String generarTextoFactura() {
		String textoProductoMenu = this.nombre + ": " + Integer.toString(this.precioBase) + "\n";
		return textoProductoMenu;
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombre;
	}
	public int getCalorias() {
		return this.calorias;
	}
	

}
