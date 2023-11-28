package logica;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ProductoMenuTest {
	
	private ProductoMenu producto;

    @Before
    public void setUp() {
        // Configuración inicial para cada prueba
        producto = new ProductoMenu("Hamburguesa", 15000);
    }
	
    @Test
    public void testCalcularPrecioConDescuento() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Hamburguesa Especial", 12000, true);

        // Acción
        int precioConDescuento = producto.calcularPrecioConDescuento();

        // Verificación
        Assert.assertEquals(9600, precioConDescuento);
    }

    @Test
    public void testCalcularPrecioConDescuentoSinDescuento() {
        // Escenario de prueba
        ProductoMenu producto = new ProductoMenu("Papas Fritas", 8000, false);

        // Acción
        int precioConDescuento = producto.calcularPrecioConDescuento();

        // Verificación
        Assert.assertEquals(8000, precioConDescuento);
    }
    
    @Test
    public void testCreacionProductoMenu() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa Clásica", 15000);
        Assertions.assertEquals("Hamburguesa Clásica", producto.getNombre());
        Assertions.assertEquals(15000, producto.getPrecio());
    }

    @Test
    public void testObtenerAtributosProductoMenu() {
        ProductoMenu producto = new ProductoMenu("Hamburguesa Clásica", 15000);
        Assertions.assertEquals("Hamburguesa Clásica", producto.getNombre());
        Assertions.assertEquals(15000, producto.getPrecio());
    }

    @Test
    public void testCompararProductosMenuIguales() {
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa Clásica", 15000);
        ProductoMenu producto2 = new ProductoMenu("Hamburguesa Clásica", 15000);
        Assertions.assertEquals(producto1, producto2);
    }

    @Test
    public void testCompararProductosMenuDiferentes() {
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa Clásica", 15000);
        ProductoMenu producto2 = new ProductoMenu("Hamburguesa Especial", 18000);
        Assertions.assertNotEquals(producto1, producto2);
    }

    // Agregar más casos de prueba para cubrir el 100% de la clase ProductoMenu
}
