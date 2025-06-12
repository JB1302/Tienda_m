/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author jstev
 */
@Controller //Definir Controlador
@RequestMapping("/categoria") //Este maneja los mapeos empezando en Categoria
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/listado") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../categoria/listado
    public String listado(Model model) {
        var lista = categoriaService.getCategorias(false);
        model.addAttribute("categorias", lista);
        model.addAttribute("totalCategorias", lista.size());
        return "/categoria/listado";
    }

    @PostMapping("/guardar")
    public String guardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        categoriaService.save(categoria);
        //Se usa el redirect para que haga otro GET de la DB
        return "redirect:/categoria/listado";
    }

    @GetMapping("/eliminar/{idCategoria}")
    public String eliminar(Categoria categoria) {
        categoriaService.delete(categoria);
        return "redirect:/categoria/listado";
    }

    @GetMapping("/modificar/{idCategoria}")
    //Tomar el elemento categoria y el Modelo del elemento 
    public String modificar(Categoria categoria, Model model) {
        //Recuperar la informacion del registro
        categoria = categoriaService.getCategoria(categoria);

        model.addAttribute("categoria", categoria);
        //Navegar manejando la ID que se solicito
        return "/categoria/modifica";
    }

}
