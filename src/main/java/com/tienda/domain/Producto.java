/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author jstev
 */
@Data //Getters y Setters
@Entity //Definir como una Entidad
@Table(name = "producto") //Definir el nombre de la tabla
public class Producto implements Serializable { //Se deben poder serializar

    //Si la tabla esta vacia e insertamos un registro categoria
    //El primer valor sera uno para el ID
    private static final long serialVersionUID = 1L;

    //Atribuos de la tabla
    //Llave Primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La identidad define Valor
    private long idProducto; //El ID va a ir sin valor, la base de datos
    private long id_Categoria; //El ID va a ir sin valor, la base de datos

    //Va a ser la que genere el auto incremento
    private String descripcion;
    private String detalle;
    private double precio;
    private int existencias;
 
    private String rutaImagen;
    private boolean activo; //Definir si Una categoria esta activa o inactiva
}
