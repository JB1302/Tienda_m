/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Constante;
import com.tienda.service.ConstanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jstev
 */
@Controller //Definir Controlador
@RequestMapping("/constante") //Este maneja los mapeos empezando en Constante
public class ConstanteController {

    @Autowired
    private ConstanteService constanteService;

    @GetMapping("/listado")
    public String listado(Model model) {
        var lista = constanteService.getConstantes();
        model.addAttribute("constantes", lista);
        model.addAttribute("totalConstantes", lista.size());
        model.addAttribute("constante", new Constante()); // <- Esto es CLAVE
        return "/constante/listado";
    }

    //Guardar el link de firebase en MySql
    @PostMapping("/guardar")
    public String guardar(Constante constante,
            RedirectAttributes redirectAttributes) {
        // Guardar la categoría con la ruta de la imagen (o simplemente si no había imagen)
        constanteService.save(constante);

        redirectAttributes.addFlashAttribute("todoOk",
                "Categoría actualizada correctamente");

        return "redirect:/constante/listado";
    }

    @GetMapping("/eliminar/{idConstante}")
    public String eliminar(Constante constante) {
        constanteService.delete(constante);
        return "redirect:/constante/listado";
    }

    @GetMapping("/modificar/{idConstante}")
    //Tomar el elemento constante y el Modelo del elemento 
    public String modificar(Constante constante, Model model) {
        //Recuperar la informacion del registro
        constante = constanteService.getConstante(constante);

        model.addAttribute("constante", constante);
        //Navegar manejando la ID que se solicito
        return "/constante/modifica";
    }

}
