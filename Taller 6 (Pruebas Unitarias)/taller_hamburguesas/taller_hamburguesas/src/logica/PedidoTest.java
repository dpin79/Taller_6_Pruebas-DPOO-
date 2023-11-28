package logica;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PedidoTest {

    private Pedido pedido;

    @Before
    public void setUp() {
        // Configuración inicial para cada prueba
        pedido = new Pedido();
    }

    @Test
    public void testAgregarProducto() throws PedidoException {
        // Verificar que se pueda agregar un producto al pedido correctamente
        ProductoMenu producto = new ProductoMenu("Hamburguesa", 15000);
        pedido.agregarProducto(producto);
        Assert.assertEquals(1, pedido.getProductos().size());
        Assert.assertTrue(pedido.getProductos().contains(producto));
    }

    @Test(expected = PedidoException.class)
    public void testAgregarProductoLimiteSuperado() throws PedidoException {
        // Verificar que se lance una excepción al intentar agregar un producto cuando se supera el límite de valor
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 100000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 60000);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);
    }

    @Test
    public void testGenerarTextoFactura() {
        // Verificar que se genere correctamente el texto de la factura
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String textoEsperado = "Pedido:\n" +
                "- Hamburguesa - $15000\n" +
                "- Papas Fritas - $5000\n" +
                "Total: $20000";
        Assert.assertEquals(textoEsperado, pedido.generarTextoFactura());
    }

    @Test
    public void testGuardarFactura() {
        // Verificar que se guarde correctamente la factura en un archivo
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu producto2 = new ProductoMenu("Papas Fritas", 5000);
        pedido.agregarProducto(producto1);
        pedido.agregarProducto(producto2);

        String nombreArchivo = "factura.txt";
        pedido.guardarFactura(nombreArchivo);

        // Verificar que el archivo existe
        File archivo = new File(nombreArchivo);
        Assert.assertTrue(archivo.exists());
        // Verificar que el contenido del archivo es correcto
        String contenidoEsperado = "Pedido:\n" +
                "- Hamburguesa - $15000\n" +
                "- Papas Fritas - $5000\n" +
                "Total: $20000";
        String contenidoActual = obtenerContenidoArchivo(nombreArchivo);
        Assert.assertEquals(contenidoEsperado, contenidoActual);
    }

    // Otros casos de prueba para los métodos de la clase Pedido

    // Método auxiliar para obtener el contenido de un archivo
    private String obtenerContenidoArchivo(String nombreArchivo) {
    	StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido.toString();
    }
}

