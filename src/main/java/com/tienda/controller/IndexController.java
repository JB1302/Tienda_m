package com.tienda.controller;

import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller //Definir Controlador
public class IndexController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../producto/listado
    public String listado(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);
      
        return "/index";
    }

}
