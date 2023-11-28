package logica;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductoAjustado implements Producto, Serializable{
	private String nombre;
	private ProductoMenu base;
	private ArrayList<Ingrediente> agregados;
	private ArrayList<Ingrediente> eliminados;
	private int precio;
	private int calorias;
	
	public ProductoAjustado(ProductoMenu base){
		this.base = base;
		this.nombre = this.base.getNombre() + " Ajustado";
		this.precio = base.getPrecio();
		this.calorias = base.getCalorias();
		this.agregados = new ArrayList<Ingrediente>();
		this.eliminados = new ArrayList<Ingrediente>();
		}
	
	public int getPrecio() {
		return this.precio;
	}

	
	public String generarTextoFactura() {
		StringBuilder textoAjustado = new StringBuilder() ;
		textoAjustado.append(this.nombre+ ": \n");
		for(Ingrediente ingredientes: this.agregados) {
			textoAjustado.append("Ingredientes agregados \n-" + ingredientes.getNombre() + "\n");	
		}
		for(Ingrediente ingredientes: this.eliminados) {
		textoAjustado.append("Ingredientes eliminados \n-" + ingredientes.getNombre()+ "\n");
		}
		textoAjustado.append("*Precio total producto ajustado: " + Integer.toString(this.precio)+ "\n");
		return textoAjustado.toString();
	}

	
	public String getNombre() {
		return this.nombre;
	}
	public void agregarIngredientes(Ingrediente ingrediente){
		this.agregados.add(ingrediente);
		this.precio += ingrediente.getCostoAdicional();
		this.calorias += ingrediente.getCalorias();
	}
	public void quitarIngredientes(Ingrediente ingrediente){
		this.eliminados.add(ingrediente);
		}

	@Override
	public int getCalorias() {
		return this.calorias;
	}
}
