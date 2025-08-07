/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 *
 * @author jstev
 */
@Data //Getters y Setters
@Entity //Definir como una Entidad
@Table(name = "constante") //Definir el nombre de la tabla
public class Constante implements Serializable { //Se deben poder serializar

    //Si la tabla esta vacia e insertamos un registro constante
    //El primer valor sera uno para el ID
    private static final long serialVersionUID = 1L;

    //Atribuos de la tabla
    //Llave Primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //La identidad define Valor
    private long idConstante;
    private String atributo;
    private String valor;

}
