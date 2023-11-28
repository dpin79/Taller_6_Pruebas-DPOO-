package logica;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ComboTest {

    private Combo combo;

    @Before
    public void setUp() {
        // Configuración inicial para cada prueba
        combo = new Combo("Combo Hamburguesa", 25000);
        ProductoMenu producto1 = new ProductoMenu("Hamburguesa", 15000);
        ProductoMenu producto2 = new ProductoMenu("Papas fritas", 5000);
        combo.agregarProducto(producto1);
        combo.agregarProducto(producto2);
    }

    @Test
    public void testGetNombre() {
        // Verificar que el nombre del combo sea correcto
        Assert.assertEquals("Combo Hamburguesa", combo.getNombre());
    }

    @Test
    public void testGetPrecio() {
        // Verificar que el precio del combo sea correcto
        Assert.assertEquals(25000, combo.getPrecio());
    }

    @Test
    public void testAgregarProducto() {
        // Verificar que se agregue un producto al combo correctamente
        ProductoMenu producto3 = new ProductoMenu("Bebida", 3000);
        combo.agregarProducto(producto3);
        Assert.assertEquals(3, combo.getProductos().size());
        Assert.assertTrue(combo.getProductos().contains(producto3));
    }

    @Test
    public void testEliminarProducto() {
        // Verificar que se elimine un producto del combo correctamente
        ProductoMenu producto2 = new ProductoMenu("Papas fritas", 5000);
        combo.eliminarProducto(producto2);
        Assert.assertEquals(1, combo.getProductos().size());
        Assert.assertFalse(combo.getProductos().contains(producto2));
    }

}

