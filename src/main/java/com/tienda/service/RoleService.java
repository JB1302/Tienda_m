/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Role;
import com.tienda.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jstev
 */
@Service //Definir como un Servicio
public class RoleService {

    //Se define un unico objeto para todos los usuarios.
    //y se crea automaticamente...
    @Autowired

    //Se enlazan
    private RoleRepository roleRepository;

    //Metodo para leer registros de la tabla Role
    @Transactional(readOnly = true)
    public List<Role> getRoles(boolean activo) {
        var lista = roleRepository.findAll();

        return lista;
    }

    //
    @Transactional(readOnly = true)
    public Role getRole(Role role) {

        //Buscar el registro que tenga el ID de role que se pasa
        //Si no lo encuentra devuelve nulo
        return roleRepository.findById(role.getIdRol())
                .orElse(null);
    }

    //Si la transaccion es exitosa se guarda, si no, regresa  al paso anterior
    @Transactional
    public void save(Role role) {
        roleRepository.save(role);
    }

    //Borrar Registros
    @Transactional
    public void delete(Role role) {
        roleRepository.delete(role);
    }
}
