package logica;

import java.io.BufferedReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.io.*;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Restaurante {
	private HashMap<String,Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private HashMap<String, Ingrediente> ingredientes;
	private HashMap<String, ProductoMenu> menuBase;
	private HashMap <String, Combo> combos;
	private HashMap<String,Bebidas> bebidas;
	public Restaurante() {
		this.ingredientes = new HashMap<String,Ingrediente>();
		this.menuBase = new HashMap<String,ProductoMenu>();
		this.combos = new HashMap<String,Combo>();
		this.pedidos = new HashMap<String,Pedido>();
		this.bebidas = new HashMap<String,Bebidas>();
		
	}
	public void iniciarPedido(String nombreCliente, String  direccionCliente) {
		int numeroPedidos = pedidos.size();
		System.out.print(numeroPedidos);
		System.out.println(pedidos);
		this.pedidoEnCurso= new Pedido(nombreCliente, direccionCliente, numeroPedidos);
		
		
	}
	public HashMap<String,Pedido> getPedidos(){
		return this.pedidos;
	}
	public void cerrarYGuardarPedido() {
		this.pedidoEnCurso.guardarFactura();
		File file = new File("pedidosViejos.ser");
		
			try {
				if (file.exists()){
		            FileOutputStream fileOut = new FileOutputStream("pedidosViejos.ser", true);
		            ObjectOutputStream out = new ObjectOutputStream(fileOut);
		            out.writeObject(this.pedidoEnCurso);
		            out.close();
			        fileOut.close();
				}
				else {
					FileOutputStream fileOut = new FileOutputStream("pedidosViejos.ser");
		            ObjectOutputStream out = new ObjectOutputStream(fileOut);
		            out.writeObject(this.pedidoEnCurso);
		            out.close();
			        fileOut.close();

				}

	        } catch (IOException e) {
	            e.printStackTrace();
	         }
			this.pedidos.put(Integer.toString(this.pedidoEnCurso.getIdPedido()), this.pedidoEnCurso);
			this.pedidoEnCurso = null;
			}
	
	

			
		
	public Pedido getPedidoEnCurso(){
		return this.pedidoEnCurso;
	}
	public HashMap<String, ProductoMenu> getMenuBase(){
		
		return this.menuBase;
	}
	public HashMap<String, Ingrediente> getIngredientes(){
		return this.ingredientes;	
	}
	public HashMap<String,Combo> getCombos(){
		return this.combos;
	} 
	public HashMap<String,Bebidas> getBebidas(){
		return this.bebidas;
	}
	public void cargarInformacionRestaurante() {
		try {
			cargarMenu();
			cargarCombos();
			cargarIngredientes();
			cargarBebidas();
			cargarPedidos();
		} catch (IngredienteRepetidoException e) {
            System.out.println("Error al cargar los ingredientes: " + e.getMessage());
        } catch (ProductoRepetidoException e) {
            System.out.println("Error al cargar el menú: " + e.getMessage());
        }
		
	}
	private void cargarCombos() {
		File file = new File("bin/data/combos.txt");
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] txtCombos = line.split(";");
                String nombre = txtCombos[0];
                double descuento = Double.parseDouble(txtCombos[1].replace("%", ""));
                Combo newCombo = new Combo(nombre,descuento);
                int i = 2;
                while (i < txtCombos.length) {
                	ProductoMenu itemCombo = this.menuBase.get(txtCombos[i]);
           
                	newCombo.agregarItemACombo(itemCombo);
                	i ++;
                this.combos.put(nombre, newCombo);
                	
                }
            }
            br.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	private void cargarMenu() throws ProductoRepetidoException{
		
		File file = new File("bin/data/menu.txt");
		Set<String> productosSet = new HashSet<>();
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] txtMenu = line.split(";");
                String nombre = txtMenu[0];
                int precioBase = Integer.parseInt(txtMenu[1]);
                int calorias = Integer.parseInt(txtMenu[2]);
                this.menuBase.put(nombre,new ProductoMenu(nombre,precioBase,calorias));
                
                if (productosSet.contains(nombre)) {
                    throw new ProductoRepetidoException("Producto repetido: " + nombre);
                }

                productosSet.add(nombre);
               }
            
            
            br.close();
            fileReader.close();
            	
        }catch (IOException e) {
        	System.out.println("Error al leer el archivo del menú: " + e.getMessage());
        }
    }
	private void cargarBebidas() {
		
		File file = new File("bin/data/bebidas.txt");
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] txtMenu = line.split(";");
                String nombre = txtMenu[0];
                int precioBase = Integer.parseInt(txtMenu[1]);
                int calorias = Integer.parseInt(txtMenu[2]);
                this.bebidas.put(nombre,new Bebidas(nombre,precioBase,calorias));
               }
            br.close();
            fileReader.close();
            	
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
			
	private void cargarIngredientes () throws IngredienteRepetidoException{
		
		File file = new File("bin/data/ingredientes.txt");
		Set<String> ingredientesSet = new HashSet<>();
		
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader br = new BufferedReader(fileReader);
            String line;
            while ((line = br.readLine()) != null) {
                String[] txtIngredientes = line.split(";");
                String nombre = txtIngredientes[0];
                int costoAdicional = Integer.parseInt(txtIngredientes[1]);
                int calorias = Integer.parseInt(txtIngredientes[2]);
                this.ingredientes.put(nombre,new Ingrediente(nombre,costoAdicional,calorias));
                
                if (ingredientesSet.contains(nombre)) {
                    throw new IngredienteRepetidoException("Ingrediente repetido: " + nombre);
                }
                ingredientesSet.add(nombre);
            
            br.close();
            fileReader.close();
            
            }   	
        }catch (IOException e) {
        	System.out.println("Error al leer el archivo de ingredientes: " + e.getMessage());
        }
    }
	private void cargarPedidos() {
		File file = new File("pedidosViejos.ser");
		try {
			if (file.exists()){
	            FileInputStream fileIn = new FileInputStream("pedidosViejos.ser");
	            ObjectInputStream in = new ObjectInputStream(fileIn);
	            while (true) {
	            	try {
	            		Pedido pedidoCargar =  (Pedido) in.readObject();
			            this.pedidos.put(Integer.toString(pedidoCargar.getIdPedido()), pedidoCargar);
				        
	            	}
	            	catch (EOFException e) {
	            		break;
	            	}
	            		
	            }
	            in.close();
	            fileIn.close();
	            
			}

        }
		catch (IOException e) {
            e.printStackTrace();
         }
		catch (ClassNotFoundException e) {
            e.printStackTrace();
		
	}
		}
}

