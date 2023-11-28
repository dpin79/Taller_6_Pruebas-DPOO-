package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class Combo implements Producto,Serializable {
	
	private double descuento;
	private String nombreCombo;
	private ArrayList<ProductoMenu> itemsCombo;
	private int precio;
	private int calorias;
	
	public Combo(String nombre, double descuento) {
		this.descuento = descuento;
		this.nombreCombo = nombre;
		this.itemsCombo = new ArrayList<ProductoMenu>();
		this.precio = 0;
		this.calorias = 0;
	}
		
	public void agregarItemACombo(ProductoMenu itemCombo) {
		this.itemsCombo.add(itemCombo);
		this.precio += itemCombo.getPrecio()-(int)(itemCombo.getPrecio()*(this.descuento/100));
		this.calorias += itemCombo.getCalorias(); 
	}
	public int getPrecio() {
		// TODO Auto-generated method stub
		return this.precio;
	}

	@Override
	public String generarTextoFactura() {
		StringBuilder textoCombo = new StringBuilder();
		textoCombo.append(this.nombreCombo + ": \n");
		for (ProductoMenu item: this.itemsCombo) {
			textoCombo.append("-" + item.generarTextoFactura()+ "\n");
		}
		textoCombo.append("*Precio total del combo: " + Integer.toString(this.precio) + "\n");
		return textoCombo.toString();
	}

	@Override
	public String getNombre() {
		// TODO Auto-generated method stub
		return this.nombreCombo;
	}
	public ArrayList<ProductoMenu> getItems() {
		// TODO Auto-generated method stub
		return this.itemsCombo;

}

	@Override
	public int getCalorias() {
		// TODO Auto-generated method stub
		return this.calorias;
	}
}