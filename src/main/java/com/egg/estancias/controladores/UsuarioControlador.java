package com.egg.estancias.controladores;

import com.egg.estancias.entidades.Cliente;
import com.egg.estancias.entidades.Familia;
import com.egg.estancias.entidades.Usuario;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.servicios.ClienteServicio;
import com.egg.estancias.servicios.FamiliaServicio;
import com.egg.estancias.servicios.UsuarioServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuario")
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_CLIENT', 'ROLE_FAMILY')")
public class UsuarioControlador {
    
    @Autowired
    UsuarioServicio usuarioServicio;
    @Autowired
    FamiliaServicio familiaServicio;
    @Autowired
    ClienteServicio clienteServicio;
    
    @GetMapping("/perfil")
    public String perfil(HttpSession session, ModelMap modelo){
        try {
            Usuario usuario = (Usuario) session.getAttribute("usuariosession");
            modelo.put("usuario", usuario);
            
            return "perfil.html";
        } catch (Exception e) {
            modelo.put("error", e.getMessage());
            return "redirect:/inicio";
        }
    }
    
    @GetMapping("/modificar/familia/{id}")
    public String modificarFamilia(@PathVariable String id, ModelMap modelo){
        
        modelo.put("usuario", familiaServicio.getOne(id));
        
        return "modificar_familia.html";
    }
    
    @PostMapping("/modificar/familia/{id}")
    public String modificarFamilia(@PathVariable String id, String alias, String clave, String clave2, String nombre, String email, int minEdad, int maxEdad, int numHijos, ModelMap modelo){
        
        try {
            familiaServicio.modificar(id, alias, clave, clave2, nombre, email, minEdad, maxEdad, numHijos);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificar_familia.html";
        }
        
        return "redirect:/usuario/perfil";
    }
    
    @GetMapping("/modificar/cliente/{id}")
    public String modificarCliente(@PathVariable String id, ModelMap modelo){
        
        modelo.put("usuario", clienteServicio.getOne(id));
        
        return "modificar_cliente.html";
    }
    
    @PostMapping("/modificar/cliente/{id}")
    public String modificarCliente(@PathVariable String id, String alias, String clave, String clave2, String email, String nombre, String calle, int numero, String codPostal, String ciudad, String pais, ModelMap modelo){
        
        try {
            clienteServicio.modificar(id, alias, clave, clave2, email, nombre, calle, numero, codPostal, ciudad, pais);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "modificar_cliente.html";
        }
        
        return "redirect:/usuario/perfil";
    }
}
