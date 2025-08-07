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
@Table(name = "role") //Definir el nombre de la tabla
public class Rol implements Serializable { //Se deben poder serializar

    //Si la tabla esta vacia e insertamos un registro rol
    //El primer valor sera uno para el ID
    private static final long serialVersionUID = 1L;

    //Atribuos de la tabla
    //Llave Primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La identidad define Valor

    private String rol;

    
}
