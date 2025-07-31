/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Item;
import com.tienda.domain.Producto;
import com.tienda.service.ItemService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jstev
 */
@Controller //Definir Controlador
public class CarritoController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ProductoService productoService;

    @GetMapping("/carrito/listado") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../carrito/listado
    public String listado(Model model) {
        var lista = itemService.getItems();

        model.addAttribute("listaItems", lista);
        model.addAttribute("carritoTotal", itemService.getTotal());
        return "/carrito/listado";
    }

    //Guardar el link de firebase en MySql
    @PostMapping("/carrito/guardar")
    public String guardar(Item item) {
        itemService.update(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/eliminar/{idProducto}")
    public String eliminar(Item item) {
        itemService.delete(item);
        return "redirect:/carrito/listado";
    }

    @GetMapping("/carrito/modificar/{idProducto}")
    //Tomar el elemento carrito y el Modelo del elemento 
    public String modificar(Item item, Model model) {
        itemService.getItem(item);
        model.addAttribute("item", item);
        return "/carrito/modifica";
    }

    @GetMapping("/carrito/agregar/{idProducto}")
    //Tomar el elemento carrito y el Modelo del elemento 
    public ModelAndView agregar(Item item, Model model) {
        Item item2 = itemService.getItem(item);

        //Si se agrega el item por primera vez
        if (item2 == null) {
            Producto producto = productoService.getProducto(item);
            item2 = new Item(producto);
        }
        //Guardar el item y lo guarda en el arreglo de carrito
        //Ya sea nuevo o aumente la cantidad en +1
        itemService.save(item2);

        var lista = itemService.getItems();
        model.addAttribute("listaItems", lista);
        model.addAttribute("carritoTotal", itemService.getTotal());
        model.addAttribute("listaTotal", lista.size());

        return new ModelAndView("/carrito/fragmentos :: verCarrito");
    }
    
    @GetMapping("/facturar/carrito")
    public String facturarCarrito(){
        itemService.facturar();
        return "redirect:/";
    }

}
