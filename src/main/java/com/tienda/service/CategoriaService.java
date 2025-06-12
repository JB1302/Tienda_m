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

        //Se hace el filtro si solo se quieren las categorias activas
        if (activo) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    //
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        
        //Buscar el registro que tenga el ID de categoria que se pasa
        //Si no lo encuentra devuelve nulo
        return categoriaRepository.findById(categoria.getIdCategoria())
                .orElse(null);
    }

    //Si la transaccion es exitosa se guarda, si no, regresa  al paso anterior
    @Transactional
    public void save(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    //Borrar Registros
    @Transactional
    public void delete(Categoria categoria) {
        categoriaRepository.delete(categoria);
    }
}
