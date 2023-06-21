package com.egg.estancias.servicios;

import com.egg.estancias.entidades.Cliente;
import com.egg.estancias.entidades.Usuario;
import com.egg.estancias.enumeraciones.Rol;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.repositorios.UsuarioRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class UsuarioServicio implements UserDetailsService{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    
    @Transactional
    public void crear(String alias, String clave, String clave2, String email) throws MiException{
        validar(alias, clave, clave2, email);
        
        Usuario usuario = new Cliente();
        
        usuario.setAlias(alias);
        usuario.setClave(clave);
        usuario.setEmail(email);        
        
        usuario.setFechaAlta(new Date());
        usuario.setRol(Rol.ADMIN);
        
        usuarioRepositorio.save(usuario);
        
    }
    
    public void validar(String alias, String clave, String clave2, String email) throws MiException{
        if (alias == null || alias.isEmpty()){
            throw new MiException("El alias no puede ser nulo o estar vacio.");
        }
        
        if (alias.length() < 5 || alias.length() > 8){
            throw new MiException("El alias debe contener entre 5 y 8 caracteres.");
        }
        
        if (clave == null || clave.isEmpty()){
            throw new MiException("La clave no puede ser nula o estar vacia.");
        }
        
        if (clave.length() < 8){
            throw new MiException("El alias debe contener un mínimo de 8 dígitos");
        }
        
        if (email == null || email.isEmpty()){
            throw new MiException("El email no puede ser nulo o estar vacio. Debe contener entre 5 y 8 caracteres.");
        }       
        
        if (!clave.equals(clave2)){
            throw new MiException(("Las contraseñas no coinciden."));
        }
    }
    
    public Usuario getOne(String id){
        return usuarioRepositorio.getOne(id);
    }    
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        
        if (usuario != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", usuario);
            
            return new User(usuario.getEmail(), usuario.getClave(), permisos);
        } else{
            return null;
        }
    }
    
}
