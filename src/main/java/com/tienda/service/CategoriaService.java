/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Categoria;
import com.tienda.repository.CategoriaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jstev
 */
@Service //Definir como un Servicio
public class CategoriaService {

    //Se define un unico objeto para todos los usuarios.
    //y se crea automaticamente...
    @Autowired

    //Se enlazan
    private CategoriaRepository categoriaRepository;

    //Metodo para leer registros de la tabla Categoria
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activo) {
        var lista = categoriaRepository.findAll();
        return lista;
    }
}
