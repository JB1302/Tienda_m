/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tienda.repository;

import com.tienda.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jstev
 */
public interface ProductoRepository 
        extends JpaRepository<Producto, Long>{
    
    //Consulta Ampliada para obtener los productos en un rango de precios
    //Ordenado por precio
    
    public List<Producto> findByPrecioBetweenOrderByPrecio(double precioInferior, double precioSuperior);
    
    //Consulta JPQL  para obtener los productos en un rango de precios
    //P mayuscula porque se refiere a la clase
    @Query(value="SELECT a FROM Producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.precio")
    public List<Producto> consultaJPQL(double precioInf, double precioSup);
    
    //Consulta SQL  para obtener los productos en un rango de precios
    //p Minuscula porque se refiere a la tabla
    @Query(nativeQuery = true,
            value="SELECT * FROM producto a WHERE a.precio BETWEEN :precioInf AND :precioSup ORDER BY a.precio")
    public List<Producto> consultaSQL(double precioInf, double precioSup);
    
    

}
