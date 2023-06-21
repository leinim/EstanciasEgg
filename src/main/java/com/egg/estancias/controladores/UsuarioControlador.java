package com.egg.estancias.controladores;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_FAMILY')")
public class UsuarioControlador {
    
    @GetMapping("/perfil")
    public String perfil(){
        return "perfil.html";
    }
    
}
