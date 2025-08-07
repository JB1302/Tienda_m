/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Rol;
import com.tienda.repository.RolRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jstev
 */
@Service //Definir como un Servicio
public class RolService {

    //Se define un unico objeto para todos los usuarios.
    //y se crea automaticamente...
    @Autowired

    //Se enlazan
    private RolRepository rolRepository;

    //Metodo para leer registros de la tabla Rol
    @Transactional(readOnly = true)
    public List<Rol> getRols() {
        var lista = rolRepository.findAll();

        return lista;
    }

    //
    @Transactional(readOnly = true)
    public Rol getRol(Rol rol) {

        //Buscar el registro que tenga el ID de rol que se pasa
        //Si no lo encuentra devuelve nulo
        return rolRepository.findById(rol.getRol())
                .orElse(null);
    }

    //Si la transaccion es exitosa se guarda, si no, regresa  al paso anterior
    @Transactional
    public void save(Rol rol) {
        rolRepository.save(rol);
    }

    //Borrar Registros
    @Transactional
    public void delete(Rol rol) {
        rolRepository.delete(rol);
    }
}
