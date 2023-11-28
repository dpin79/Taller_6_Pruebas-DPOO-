package logica;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductoAjustadoTest {

    private ProductoAjustado producto;

    @Before
    public void setUp() {
        // Configuración inicial para cada prueba
        producto = new ProductoAjustado("Papas Fritas", 5000, 0.1);
        producto = new ProductoAjustado("Hamburguesa Doble", 20000, 2);
        producto = new ProductoAjustado("Hamburguesa", 15000, 0.15);
    }

    @Test
    public void testGetNombre() {
        // Verificar que el nombre del producto sea correcto
        Assert.assertEquals("Papas Fritas", producto.getNombre());
        Assert.assertEquals("Hamburguesa Doble", producto.getNombre());
        Assert.assertEquals("Hamburguesa", producto.getNombre());
    }

    @Test
    public void testGetPrecio() {
        // Verificar que el precio del producto sea correcto
        Assert.assertEquals(5000, producto.getPrecio());
        Assert.assertEquals(20000, producto.getPrecio());
        Assert.assertEquals(15000, producto.getPrecio());
    }

    @Test
    public void testGetDescuento() {
        // Verificar que el descuento del producto sea correcto
        Assert.assertEquals(0.1, producto.getDescuento(), 0.01);
    }

    @Test
    public void testCalcularPrecioConDescuento() {
        // Verificar el cálculo del precio con descuento
        double precioConDescuento = producto.getPrecio() - (producto.getPrecio() * producto.getDescuento());
        Assert.assertEquals(precioConDescuento, producto.calcularPrecioConDescuento(), 0.01);
    }


    @Test
    public void testGetCantidad() {
        // Verificar que la cantidad del producto ajustado sea correcta
        Assert.assertEquals(2, producto.getCantidad());
    }

    @Test
    public void testCalcularPrecioTotal() {
        // Verificar que el cálculo del precio total sea correcto
        int precioTotalEsperado = 20000 * 2;
        Assert.assertEquals(precioTotalEsperado, producto.calcularPrecioTotal());
    }
    // Otros casos de prueba para los métodos de la clase ProductoAjustado

}
