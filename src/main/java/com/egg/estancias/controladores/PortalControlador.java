package com.egg.estancias.controladores;

import com.egg.estancias.entidades.Usuario;
import javax.servlet.http.HttpSession;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalControlador {
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }    
        
    @GetMapping("/inicio")
    @PreAuthorize("hasAnyRole('ROLE_CLIENT', 'ROLE_FAMILY', 'ROLE_ADMIN')")
    public String inicio(HttpSession session){
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        
        if (logueado.getRol().toString().equals("ADMIN")){
            return "redirect:/admin/dashboard";
        }
        
        return "inicio.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){
        
        if (error != null){
            modelo.put("error", "Usuario o contraseña inválidos.");
        }
        
        return "login.html";
    }
    
    
    
    
}
