/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Categoria;
import com.tienda.service.CategoriaService;
import com.tienda.service.FirebaseStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jstev
 */
@Controller //Definir Controlador
@RequestMapping("/categoria") //Este maneja los mapeos empezando en Categoria
public class CategoriaController {

    @Autowired
    private FirebaseStorageService firebaseStorageService;
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

    //Guardar el link de firebase en MySql
    @PostMapping("/guardar")
    public String guardar(Categoria categoria,
            @RequestParam("imagenFile") MultipartFile imagenFile,
            RedirectAttributes redirectAttributes) {

        if (!imagenFile.isEmpty()) {
            // Guardar primero para obtener el ID de la categoría (si es autoincremental)
            categoriaService.save(categoria);

            // Subir imagen y obtener la ruta
            String rutaImagen = firebaseStorageService.cargaImagen(
                    imagenFile,
                    "categoria",
                    categoria.getIdCategoria() // Asegúrate de tener este método en tu entidad
            );

            // Asignar la ruta a la categoría
            categoria.setRutaImagen(rutaImagen);
        }
        // Guardar la categoría con la ruta de la imagen (o simplemente si no había imagen)
        categoriaService.save(categoria);

        redirectAttributes.addFlashAttribute("todoOk",
                "Categoría actualizada correctamente");

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
