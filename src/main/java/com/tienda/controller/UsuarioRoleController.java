/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.controller;

import com.tienda.domain.Rol;
import com.tienda.domain.Role;
import com.tienda.domain.Usuario;
import com.tienda.service.RolService;
import com.tienda.service.RoleService;
import com.tienda.service.UsuarioService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jstev
 */
@Controller //Definir Controlador
@RequestMapping("/usuario_role") //Este maneja los mapeos empezando en Categoria
public class UsuarioRoleController {

    @Autowired
    private UsuarioService usuarioservice;
    @Autowired
    private RolService rolService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/asignar") //Establecer la vista de Listado    
    //De momento el metodo ejecutaria en .../categoria/listado
    public String asignar(Usuario usuario, Model model) {
        if (usuario == null) {
            usuario = new Usuario();
        }
        usuario = usuarioservice.getUsuarioPorUsername(usuario.getUsername());

        if (usuario != null) {
            model.addAttribute("usuario", usuario);

            //Recuperar informacion de roles disponibles;
            var lista = rolService.getRols();
            ArrayList<String> rolesDisponibles = new ArrayList<>();
            for (Rol r : lista) {
                rolesDisponibles.add(r.getRol());
            }

            var rolesAsignados = usuario.getRoles();

            //Borrar el role que ya tine
            for (Role r : rolesAsignados) {
                rolesDisponibles.remove(r.getNombre());
            }
            model.addAttribute("rolesDisponibles", rolesDisponibles);
            model.addAttribute("rolesAsignados", rolesAsignados);
            model.addAttribute("idUsuario", usuario.getIdUsuario());
            model.addAttribute("username", usuario.getUsername());

        }

        return "/usuario_role/listado";
    }

    @GetMapping("/agregar")
    public String agregar(Usuario usuario, Rol rol, Model model) {
        rolService.save(rol);
        return "redirect:/usuario_role/asignar?username=" + usuario.getUsername();
    }

    @GetMapping("/eliminar")
    public String eliminar(Usuario usuario, Rol rol, Model model) {
        rolService.delete(rol);
        return "redirect:/usuario_role/asignar?username=" + usuario.getUsername();
    }

}
