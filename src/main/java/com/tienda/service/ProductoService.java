package com.tienda.service;

import com.tienda.domain.Producto;
import com.tienda.repository.ProductoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jstev
 */
@Service //Definir como un Servicio
public class ProductoService {

    //Se define un unico objeto para todos los usuarios.
    //y se crea automaticamente...
    @Autowired

    //Se enlazan
    private ProductoRepository productoRepository;

    //Metodo para leer registros de la tabla Producto
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        var lista = productoRepository.findAll();

        //Se hace el filtro si solo se quieren las productos activas
        if (activo) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }

    //
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {

        //Buscar el registro que tenga el ID de producto que se pasa
        //Si no lo encuentra devuelve nulo
        return productoRepository.findById(producto.getIdProducto())
                .orElse(null);
    }

    //Si la transaccion es exitosa se guarda, si no, regresa  al paso anterior
    @Transactional
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    //Borrar Registros
    @Transactional
    public boolean delete(Producto producto) {
        try {
            productoRepository.delete(producto);
            //Cuando se manda a borrar se manda un hilo
            //Ese hilo se va a la DB y borra.
            //Mientras eso se hace, se corre en el background
            
            //No continuar hasta que el proceso finalice
            productoRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
