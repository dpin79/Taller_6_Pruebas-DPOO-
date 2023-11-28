package logica;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.File;

public class Pedido implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7620940290696791915L;
	private int IdPedido;
	private String nombreCliente;
	private String direccionCliente;
	private ArrayList<Producto> itemsPedido;
	private int numeroPedidos;
	
	
	public Pedido (String nombreCliente, String direccionCliente, int numeroPedidos) {
		this.nombreCliente = nombreCliente;
		this.direccionCliente = direccionCliente;
		this.itemsPedido = new ArrayList<Producto>();
		this.numeroPedidos = numeroPedidos;
		this.IdPedido = this.numeroPedidos +1;
		
	}
	
	public int getIdPedido(){
		return this.IdPedido;
	}
	public void AgregarProducto (Producto nuevoitem) throws PedidoException {
		int precioNeto = 0;
		for (Producto producto: this.itemsPedido) {
			precioNeto += producto.getPrecio();
			
			if (precioNeto > 150000) {
				throw new PedidoException("El valor total supera el máximo");
			}
			
			this.itemsPedido.add(nuevoitem);
		}
		
		
		
		
	}
	private int getPrecioNetoPedido(){
		int precioNeto = 0;
		for (Producto producto: this.itemsPedido) {
			precioNeto += producto.getPrecio();
		}
		return precioNeto;
	}
	private int getPrecioTotalPedido() {
		int precioNeto = getPrecioNetoPedido();
		int precioIva = getPrecioIVAPedido();
		
		return precioNeto + precioIva;
		
	}
	private int getPrecioIVAPedido() {
		int precioIva = 0;
		for (Producto producto: this.itemsPedido) {
			precioIva += producto.getPrecio();
		}
		precioIva = (precioIva * 19)/100; 
		return precioIva;
		
	}
	private int getCaloriasPedido() {
		int calorias = 0;
		for (Producto producto: this.itemsPedido) {
			calorias += producto.getCalorias();
			}
		return calorias;
	}
	public String generarTextoFactura() {
		StringBuilder textoFactura = new StringBuilder();
		textoFactura.append("Nombre: " + this.nombreCliente + "\nDirección: " + 
		this.direccionCliente + "\nId pedido:"  + Integer.toString(this.IdPedido) + "\n");
		for (Producto producto: this.itemsPedido) {
			textoFactura.append(producto.generarTextoFactura());
		}
		textoFactura.append("\nPrecio neto: " + Integer.toString(getPrecioNetoPedido())+ "\nIVA: "
				+ Integer.toString(getPrecioIVAPedido()) + "\nPrecio total: " + Integer.toString(getPrecioTotalPedido())+
				"\nCantidad total calorias: " + Integer.toString(getCaloriasPedido()));
		return textoFactura.toString();
		
		
	}
	public void guardarFactura() {
		String textoFactura = generarTextoFactura();
		try {
            FileWriter writer = new FileWriter(Integer.toString(this.IdPedido)+".txt");
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write(textoFactura);

            buffer.close();
            writer.close();
            System.out.println(textoFactura);

            System.out.println("El recibo se ha generado y guardado correctamente");
            

        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
	public boolean equals(Pedido pedidoComparar) {
		boolean esIgual = false;
		if (this.itemsPedido.size() == pedidoComparar.itemsPedido.size()) {
			ArrayList<String> nombresPedidoActual = new ArrayList<String>();
			ArrayList<String> nombresPedidoComparar = new ArrayList<String>();
			int i = 0;
			boolean continuar = true;
			while (i < this.itemsPedido.size() && continuar == true) {
				nombresPedidoActual.add(this.itemsPedido.get(i).getNombre());
				nombresPedidoComparar.add(pedidoComparar.itemsPedido.get(i).getNombre());
				i++;
			}
			Collections.sort(nombresPedidoActual);
			Collections.sort(nombresPedidoComparar);
			esIgual = nombresPedidoActual.equals(nombresPedidoComparar);
		}
				
			
		
		
		
		
		
		return esIgual;
	}
		
}
	
