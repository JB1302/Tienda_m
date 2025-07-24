package com.tienda.service;

import com.tienda.domain.Role;
import com.tienda.domain.Usuario;
import com.tienda.repository.RoleRepository;
import com.tienda.repository.UsuarioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RoleRepository roleRepository;

     @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

     @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioRepository.findById(usuario.getIdUsuario()).orElse(null);
    }

 
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

     @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        return usuarioRepository.findByUsernameAndPassword(username, password);
    }

     @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioRepository.findByUsernameOrCorreo(username, correo);
    }

     @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioRepository.existsByUsernameOrCorreo(username, correo);
    }

     @Transactional
    public void save(Usuario usuario, boolean crearRoleUser) {
        usuario=usuarioRepository.save(usuario);
        if (crearRoleUser) {  //Si se está creando el usuario, se crea el role por defecto "USER"
            Role role = new Role();
            role.setNombre("ROLEE_USER");
            role.setIdUsuario(usuario.getIdUsuario());
            roleRepository.save(role);
        }
    }

     @Transactional
    public boolean delete(Usuario usuario) {
        try {
            usuarioRepository.delete(usuario);
            usuarioRepository.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}