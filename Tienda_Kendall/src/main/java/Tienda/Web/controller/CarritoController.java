package Tienda.Web.controller;

import Tienda.Web.domain.Item;
import Tienda.Web.domain.Producto;
import Tienda.Web.service.ItemService;
import Tienda.Web.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CarritoController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService; // Assuming ProductoService is also needed based on context

    // Para ver el carrito
    @GetMapping("/carrito/listado")
    public String inicio(Model model) {
        var items = itemService.gets();
        model.addAttribute("items", items);
        var carritoTotalVenta = 0;
        for (Item i : items) {
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return "/carrito/listado";
    }

    // Para Agregar un producto al carrito
    @GetMapping("/carrito/agregar/{idProducto}")
    public ModelAndView agregarItem(Model model, Item item) {
        Item item2 = itemService.get(item);
        if (item2 == null) {
            Producto producto = productoService.getProducto(item); // Assuming getProducto takes an Item and returns a Producto
            item2 = new Item(producto);
        }
        itemService.save(item2);
        var lista = itemService.gets();
        var totalCarritos = 0;
        var carritoTotalVenta = 0;
        for (Item i : lista) {
            totalCarritos += i.getCantidad();
            carritoTotalVenta += (i.getCantidad() * i.getPrecio());
        }
        model.addAttribute("listaItems", lista);
        model.addAttribute("totalCarritos", totalCarritos);
        model.addAttribute("carritoTotal", carritoTotalVenta);
        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }

    // Para modificar un producto del carrito
    @GetMapping("/carrito/modificar/{idProducto}")
    public String modificarItem(Item item, Model model) {
        item = itemService.get(item);
        model.addAttribute("item", item);
        return "/carrito/modificar";
    }

    // Para eliminar un elemento del carrito
    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminarItem(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    //Para actualizar un producto del carrito (cantidad)
    @PostMapping("/carrito/guardar")
    public String guardarItem(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    //Para facturar los productos del carrito... no incluye la factura en si
    @GetMapping("/facturar/carrito")
    public String facturarCarrito() {
        itemService.facturar();
        return "redirect:/";
    }
}
