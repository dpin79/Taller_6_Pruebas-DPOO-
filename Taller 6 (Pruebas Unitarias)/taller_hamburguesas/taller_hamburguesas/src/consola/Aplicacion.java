package consola;
import logica.Restaurante;
import logica.Bebidas;
import logica.Combo;
import logica.Ingrediente;
import logica.Pedido;
import logica.ProductoAjustado;
import logica.ProductoMenu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;




public class Aplicacion {
	private Restaurante restaurante;

	public void ejecutarAplicacion()
	{
		System.out.println("Bienvenido");
		this.restaurante = new Restaurante();
		this.restaurante.cargarInformacionRestaurante();
		boolean continuar = true;
		 
		while (continuar)
		{
			try
			{
				mostrarMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					
					iniciarPedido();
					}
					
				else if (opcion_seleccionada == 2 ) {
					System.out.println("gei");
					verNombresMenu();
					
				}
					
				else if (opcion_seleccionada == 3 ) {
					verNombresCombo();
				}
					
					
				else if (opcion_seleccionada == 4 ) {
					verNombresIngredientes();
				}
				
				else if (opcion_seleccionada == 5 ) {
					verNombresBebidas();
				}
				else if (opcion_seleccionada == 6 ) {
					agregarElementosPedido();
				}
				else if (opcion_seleccionada == 7) {
					
					Collection<Pedido> pedidosAnteriores= this.restaurante.getPedidos().values();
					for (Pedido pedido: pedidosAnteriores ) {
						if (pedido.equals(this.restaurante.getPedidoEnCurso())) {
							System.out.println("El pedido con id " + Integer.toString(pedido.getIdPedido()) +
							" Es identico al actual\n");
						}
					}
					this.restaurante.cerrarYGuardarPedido();
				}
				else if (opcion_seleccionada == 8) {
					consultarInformacionPedidoID();
				}
				else if (opcion_seleccionada == 9) {
					continuar = false;
				}
				
					
			
			
				
	
				
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
	}
	public void mostrarMenu()
	{
		System.out.println("\nOpciones de la aplicación\n");
		System.out.println("1. Nuevo pedido");
		System.out.println("2. Ver menu");
		System.out.println("3. Ver combos");
		System.out.println("4. Ver adiciones");
		System.out.println("5. Ver bebidas");
		System.out.println("6. Agregar elemento al pedido");	
		System.out.println("7. Cerrar pedido");	
		System.out.println("8. Consultar informacion de un pedido por su id");
		System.out.println("9. Salir");
	}
	public void verNombresCombo() {
		Collection<Combo> nombresCombo= this.restaurante.getCombos().values();
		int i = 0;
		for(Combo producto: nombresCombo) {
			ArrayList<ProductoMenu>items= producto.getItems();
			StringBuilder itemsCombo = new StringBuilder();
			for (ProductoMenu itemCombo: items) {
				itemsCombo.append("-"+itemCombo.getNombre() + " ");
				}
			System.out.println(Integer.toString(i)+ ". Combo: " + producto.getNombre()
			+ ", Contiene: \n" + itemsCombo.toString());
			i ++;
		}		
	}
	public void verNombresBebidas() {
		Collection<String> nombresMenu= this.restaurante.getBebidas().keySet();
		int i = 0;
		for(String nombre: nombresMenu) {
			System.out.println(Integer.toString(i)+". " + nombre);
			i++;
		}
				
	}
	public void verNombresIngredientes() {
		Collection<String> nombresMenu= this.restaurante.getIngredientes().keySet();
		int i = 0;
		for(String nombre: nombresMenu) {
			
			System.out.println(Integer.toString(i)+". " + nombre + "");
			i++;
			}	
	}
	public void verNombresMenu() {
		Collection<String> nombresMenu= this.restaurante.getMenuBase().keySet();
		int i = 0;
		for(String nombre: nombresMenu) {
			System.out.println(Integer.toString(i)+". " + nombre);
			i++;
		}
				
	}
	public void agregarIngredientesMenu()
	{
		System.out.println("\nAjustando el producto\n");
		System.out.println("1. Agregar ingredientes");
		System.out.println("2. Quitar ingredientes");
		System.out.println("3. Dejar de modificar");
			
	}
	public void agregarPedidoMenu()
	{
		System.out.println("\nAgregando elementos al pedido\n");
		System.out.println("1. Agregar un producto del menu");
		System.out.println("2. Agregar combo");
		System.out.println("3. Ajustar un producto del menu");
		System.out.println("4. Agregar bebidas");
		System.out.println("5. Dejar de agregar elementos");	
	}
	private void agregarElementosPedido() {
		if (this.restaurante.getPedidoEnCurso() != null) {
			boolean seguir = true;
			while(seguir) {
				agregarPedidoMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				
				if (opcion_seleccionada == 1) {
					verNombresMenu();
					String nombreElemento = input("Escriba nombre del elemento que desea");
					if (this.restaurante.getMenuBase().containsKey(nombreElemento)) {
						auxiliarAgregarElemento(nombreElemento,"productoMenu");
						
						}
					else {
						System.out.println("Ese elemento no está en el menú");
					}
					
					
					}
				else if (opcion_seleccionada == 2) {
					verNombresCombo();
					String nombreElemento = input("Escriba el nombre del combo que desea");
					if (this.restaurante.getCombos().containsKey(nombreElemento)) {
						auxiliarAgregarElemento(nombreElemento,"combo");
						}
					else {
						System.out.println("Ese elemento no está en el menú");
					}
				}
				else if (opcion_seleccionada == 3) {
					agregarElementoMenuAjustado();
					
					}
				else if (opcion_seleccionada == 5) {
					seguir = false;
					
				}
				else if (opcion_seleccionada == 4) {
					verNombresBebidas();
					String nombreElemento = input("Escriba el nombre de la bebida que desea");
					if (this.restaurante.getBebidas().containsKey(nombreElemento)) {
						auxiliarAgregarElemento(nombreElemento,"bebida");
						}
					else {
						System.out.println("Ese elemento no está en el menú");
					}
				}
				else {
					System.out.println("Seleccione una opcion valida");
				}
				
			}
			
		}
		else {
			System.out.println("Primero inicie un pedido");
		}
		
	}
	private void agregarElementoMenuAjustado() {
		verNombresMenu();
		String nombreElemento = input("Por favor digite el nombre del elemento que desea ajustar");
		if (this.restaurante.getMenuBase().containsKey(nombreElemento)){
			ProductoMenu elementoMenu = this.restaurante.getMenuBase().get(nombreElemento);
			boolean continuar = true;
			ProductoAjustado productoAjustado = new ProductoAjustado(elementoMenu);
			while (continuar) {
				agregarIngredientesMenu();
				int opcion_seleccionada = Integer.parseInt(input("Por favor seleccione una opción"));
				if (opcion_seleccionada == 1) {
					verNombresIngredientes();
					String nombreIngrediente = input("Escriba el ingrediente que desea agregar");
					if (this.restaurante.getIngredientes().containsKey(nombreIngrediente)){
						Ingrediente ingrediente = this.restaurante.getIngredientes().get(nombreIngrediente);
						productoAjustado.agregarIngredientes(ingrediente);
						System.out.println(ingrediente.getNombre() + " agregado exitosamente");
					}
					else{
						System.out.println("Ese ingrediente no existe");
					}
				}
				else if(opcion_seleccionada == 2) {
					verNombresIngredientes();
					String nombreIngrediente = input("Digite el ingrediente que desea eliminar");
					if (this.restaurante.getIngredientes().containsKey(nombreIngrediente)){
						Ingrediente ingrediente = this.restaurante.getIngredientes().get(nombreIngrediente);
						productoAjustado.quitarIngredientes(ingrediente);
						System.out.println(ingrediente.getNombre() + " eliminado exitosamente");
				}
				
			}
				else if (opcion_seleccionada == 3) {
					this.restaurante.getPedidoEnCurso().AgregarProducto(productoAjustado);
					continuar = false;
				}
				else {
					System.out.println("Seleccione una opcion valida");
				}		
		}
		}
		else {
			System.out.println("Ese elemento no está en el menú");
		}
	
	
	
	
	}
	private void auxiliarAgregarElemento(String nombreMenu, String type) {
		if (type == "productoMenu"){
			ProductoMenu elementoMenu = this.restaurante.getMenuBase().get(nombreMenu);
			this.restaurante.getPedidoEnCurso().AgregarProducto(elementoMenu);
			System.out.println(elementoMenu.getNombre() + " añadido exitosamente");
			}
		else if(type == "combo"){
			Combo elementoMenu = this.restaurante.getCombos().get(nombreMenu);
			this.restaurante.getPedidoEnCurso().AgregarProducto(elementoMenu);
			System.out.println(elementoMenu.getNombre() + " añadido exitosamente");
			}
		else if(type == "bebida"){
			Bebidas elementoMenu = this.restaurante.getBebidas().get(nombreMenu);
			this.restaurante.getPedidoEnCurso().AgregarProducto(elementoMenu);
			System.out.println(elementoMenu.getNombre() + " añadido exitosamente");
			}
		
		
	}
	public void consultarInformacionPedidoID() {
		String numeroPedido = input("Digite el número del pedido que quiere consultar la información");
		if (this.restaurante.getPedidos().containsKey(numeroPedido)) {
			System.out.println(this.restaurante.getPedidos().get(numeroPedido).generarTextoFactura());
		}
		else {
			System.out.println("Ese pedido no existe D:");
		}
		
				
		
	}
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	public void iniciarPedido() {
		if (this.restaurante.getPedidoEnCurso() == null) {
			String nombreCliente = input("Ingrese el nombre del cliente");
			String direccionCliente = input("Ingrese la dirección del cliente");
			this.restaurante.iniciarPedido(nombreCliente, direccionCliente);
			System.out.println("Pedido iniciado");
		}
		else {
			System.out.println("Ya hay un pedido en curso");
		}
		
	}
	public static void main(String[] args){
		Aplicacion app = new Aplicacion();
		app.ejecutarAplicacion();
		Restaurante restaurante = new Restaurante();
		restaurante.cargarInformacionRestaurante();
	}
	
	
	

}

