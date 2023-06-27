package com.egg.estancias.controladores;

import com.egg.estancias.entidades.Familia;
import com.egg.estancias.entidades.Usuario;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.servicios.CasaServicio;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/casa")
@PreAuthorize("hasAnyRole('ROLE_FAMILY', 'ROLE_ADMIN')")
public class CasaControlador {
    
    @Autowired
    private CasaServicio casaServicio;
    
    @GetMapping("/agregar")
    public String agregar(){
        return "crear_casa.html";
    }
    
    @PostMapping("/agregado")
    public String agregar(HttpSession session, @RequestParam MultipartFile archivo, @RequestParam String calle, @RequestParam int numero, @RequestParam String codPostal, @RequestParam String ciudad, @RequestParam String pais, @RequestParam int minDias, @RequestParam int maxDias, @RequestParam double precio, @RequestParam String tipoVivienda, ModelMap modelo){
        
        try {
            Familia familia = (Familia) session.getAttribute("usuariosession");
            casaServicio.crear(archivo, familia, calle, numero, codPostal, ciudad, pais, minDias, maxDias, precio, tipoVivienda);
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            return "crear_casa.html";
        }
        
        return "redirect:/inicio";
    }
    
}
