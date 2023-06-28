package com.egg.estancias.servicios;

import com.egg.estancias.entidades.Familia;
import com.egg.estancias.entidades.Usuario;
import com.egg.estancias.enumeraciones.Rol;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.repositorios.FamiliaRepositorio;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class FamiliaServicio implements UserDetailsService {

    @Autowired
    private FamiliaRepositorio familiaRepositorio;
    
    @Transactional
    public void crear(String alias, String clave, String clave2, String nombre, String email, int minEdad, int maxEdad, int numHijos) throws MiException {
        validar(alias, clave, clave2, nombre, email, minEdad, maxEdad, numHijos);

        Familia familia = new Familia();

        familia.setAlias(alias);
        familia.setClave(new BCryptPasswordEncoder().encode(clave));
        familia.setNombre(nombre);
        familia.setEmail(email);
        familia.setMinEdad(minEdad);
        familia.setMaxEdad(maxEdad);
        familia.setNumHijos(numHijos);

        familia.setFechaAlta(new Date());
        familia.setRol(Rol.FAMILY);

        familiaRepositorio.save(familia);

    }
    
    @Transactional
    public void modificar(String id, String alias, String clave, String clave2, String nombre, String email, int minEdad, int maxEdad, int numHijos) throws MiException {
        validar(alias, clave, clave2, nombre, email, minEdad, maxEdad, numHijos);

        Optional<Familia> respuesta = familiaRepositorio.findById(id);
        if (respuesta.isPresent()) {
            Familia familia = respuesta.get();

            familia.setAlias(alias);
            familia.setClave(new BCryptPasswordEncoder().encode(clave));
            familia.setNombre(nombre);
            familia.setEmail(email);
            familia.setMinEdad(minEdad);
            familia.setMaxEdad(maxEdad);
            familia.setNumHijos(numHijos);            
           
            
            familiaRepositorio.save(familia);
        } else {
            throw new MiException("La familia solicitada no se encuentra registrada.");
        }      

    }
    
    @Transactional
    public void baja(String id) throws MiException{
        
        Optional<Familia> respuesta = familiaRepositorio.findById(id);
        if (respuesta.isPresent()){
            Familia familia = respuesta.get();
            familia.setFechaBaja(new Date());
        } else {
            throw new MiException("La familia que se desea dar de baja no se encuentra registrada.");
        }
    }

    public void validar(String alias, String clave, String clave2, String nombre, String email, int minEdad, int maxEdad, int numHijos) throws MiException {

        if (alias == null || alias.isEmpty()) {
            throw new MiException("El alias no puede ser nulo o estar vacio.");
        }

        if (alias.length() < 5 || alias.length() > 8) {
            throw new MiException("El alias debe contener entre 5 y 8 caracteres.");
        }

        if (clave == null || clave.isEmpty()) {
            throw new MiException("La clave no puede ser nula o estar vacia.");
        }

        if (clave.length() < 6) {
            throw new MiException("La clave debe contener un mínimo de 8 dígitos");
        }

        if (email == null || email.isEmpty()) {
            throw new MiException("El email no puede ser nulo o estar vacio. Debe contener entre 5 y 8 caracteres.");
        }

        if (nombre == null || nombre.isEmpty()) {
            throw new MiException("El nombre no puede ser nulo o estar vacio.");
        }

        //minEdad, maxEdad, numHijos
        if (!clave.equals(clave2)) {
            throw new MiException(("Las contraseñas no coinciden."));
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario familia = familiaRepositorio.buscarPorEmail(email);
        
        if (familia != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + familia.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", familia);
            
            
            return new User(familia.getEmail(), familia.getClave(), permisos);
        } else{
            return null;
        }
    }
    
    public Familia getOne(String id){
        return familiaRepositorio.getOne(id);
    }    
}
