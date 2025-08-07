/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tienda.service;

import com.tienda.domain.Constante;
import com.tienda.repository.ConstanteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author jstev
 */
@Service
public class ConstanteService {

    @Autowired
    private ConstanteRepository constanteRepository;

    @Transactional(readOnly = true)
    public List<Constante> getConstantes() {
        return constanteRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Constante getConstante(Constante constante) {
        return constanteRepository.findById(constante.getIdConstante()).orElse(null);
    }

    @Transactional(readOnly = true)
    public Constante getConstantePorAtributo(String atributo) {
        return constanteRepository.findByAtributo(atributo); // <-- Corrige aquí
    }

    @Transactional
    public void save(Constante constante) {
        constanteRepository.save(constante);
    }

    @Transactional
    public void delete(Constante constante) {
        constanteRepository.delete(constante);
    }
}
