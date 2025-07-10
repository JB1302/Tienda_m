package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller //Definir Controlador
@RequestMapping("/pruebas") //Este maneja los mapeos empezando en Producto
public class PruebasController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../producto/listado
    public String listado(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);

        //Inyectar categorias
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    //Ajustar el indice ID categoria basado en le ID que se envia
    @GetMapping("/listado/{idCategoria}") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../producto/listado
    public String listado(Categoria categoria, Model model) {

        //Ir y Recuperar el registro completo del ID
        categoria = categoriaService.getCategoria(categoria);

        var lista = categoria.getProductos();
        model.addAttribute("productos", lista);

        //Inyectar categorias
        var categorias = categoriaService.getCategorias(true);
        model.addAttribute("categorias", categorias);

        return "/pruebas/listado";
    }

    @GetMapping("/listado2") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../producto/listado
    public String listado2(Model model) {
        var lista = productoService.getProductos(false);
        model.addAttribute("productos", lista);
        return "/pruebas/listado2";
    }

    //Consulta Amplia
    @PostMapping("/query1")
    public String query1(
            @RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {
        var productos = productoService.consultaAmpliada(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

    //Consulta JPQL
    @PostMapping("/query2")
    public String query2(
            @RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {
        var productos = productoService.consultaJPQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

        //Consulta SQL 
   @PostMapping("/query3")
    public String query3(
            @RequestParam() double precioInf,
            @RequestParam() double precioSup,
            Model model) {
        var productos = productoService.consultaSQL(precioInf, precioSup);
        model.addAttribute("productos", productos);
        model.addAttribute("precioInf", precioInf);
        model.addAttribute("precioSup", precioSup);

        return "/pruebas/listado2";
    }

}
