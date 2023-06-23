package com.egg.estancias.servicios;

import com.egg.estancias.entidades.Cliente;
import com.egg.estancias.enumeraciones.Rol;
import com.egg.estancias.errores.MiException;
import com.egg.estancias.repositorios.ClienteRepositorio;
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
public class ClienteServicio implements UserDetailsService {
    
    @Autowired
    private ClienteRepositorio clienteRepositorio;
    
    @Transactional
    public void crear(String alias, String clave, String clave2, String email, String nombre, String calle, int numero, String codPostal, String ciudad, String pais) throws MiException{
        validar(alias, clave, clave2, email, nombre, calle, numero, codPostal, ciudad, pais);
        
        Cliente cliente = new Cliente();
        
        cliente.setAlias(alias);
        cliente.setClave(new BCryptPasswordEncoder().encode(clave));
        cliente.setEmail(email);
        cliente.setNombre(nombre);
        cliente.setCalle(calle);
        cliente.setNumero(numero);
        cliente.setCodPostal(codPostal);
        cliente.setCiudad(ciudad);
        cliente.setPais(pais);
        
        cliente.setFechaAlta(new Date());
        cliente.setRol(Rol.CLIENT);
        
        clienteRepositorio.save(cliente);
        
    }
    
    @Transactional
    public void modificar(String id, String alias, String clave, String clave2, String email, String nombre, String calle, int numero, String codPostal, String ciudad, String pais) throws MiException{
        validar(alias, clave, clave2, email, nombre, calle, numero, codPostal, ciudad, pais);
        
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if(respuesta.isPresent()){
            
            Cliente cliente = respuesta.get();
            cliente.setAlias(alias);
            cliente.setClave(clave);
            cliente.setEmail(email);
            cliente.setNombre(nombre);
            cliente.setCalle(calle);
            cliente.setNumero(numero);
            cliente.setCodPostal(codPostal);
            cliente.setCiudad(ciudad);
            cliente.setPais(pais);           
        
            clienteRepositorio.save(cliente); 
        } else {
            throw new MiException("El cliente solicitado no se encuentra registrado.");
        }  
        
    }
    
    @Transactional
    public void baja(String id) throws MiException{
        
        Optional<Cliente> respuesta = clienteRepositorio.findById(id);
        if (respuesta.isPresent()){
            Cliente cliente = respuesta.get();
            
            cliente.setFechaBaja(new Date());
        } else {
            throw new MiException("El cliente que se desea dar de baja no se encuentra registrado.");
        }       
    }
    
    
    
    public void validar(String alias, String clave, String clave2, String email, String nombre, String calle, int numero, String codPostal, String ciudad, String pais) throws MiException{
        if (alias == null || calle.isEmpty()){
            throw new MiException("El alias no puede ser nulo o estar vacio.");
        }
        
        if (alias.length() < 5 || alias.length() > 8){
            throw new MiException("El alias debe contener entre 5 y 8 caracteres.");
        }
        
        if (clave == null || clave.isEmpty()){
            throw new MiException("La clave no puede ser nula o estar vacia.");
        }
        
        if (clave.length() < 6){
            throw new MiException("La clave debe contener un mínimo de 6 dígitos");
        }
        
        if (email == null || email.isEmpty()){
            throw new MiException("El email no puede ser nulo o estar vacio. Debe contener entre 5 y 8 caracteres.");
        }
                
        if (nombre == null || nombre.isEmpty()){
            throw new MiException("El nombre no puede ser nulo o estar vacio.");
        }
        
        if (calle == null || calle.isEmpty()){
            throw new MiException("La calle no puede ser nula o estar vacia.");
        }
        
        if (numero <= 0 || numero >= 10000){        
            throw new MiException("El numero de calle es invalido.");
        }
        
        if (codPostal == null || codPostal.isEmpty()){
            throw new MiException("El codigo postal no puede ser nulo o estar vacio.");
        }
        
        if (ciudad == null || ciudad.isEmpty()){
            throw new MiException("La ciudad no puede ser nula o estar vacia.");
        }
        
        if (pais == null || pais.isEmpty()){
            throw new MiException("El pais no puede ser nulo o estar vacio.");
        }
        
        if (!clave.equals(clave2)){
            throw new MiException(("Las contraseñas no coinciden."));
        }
    }
    
    public Cliente getOne(String id){
        return clienteRepositorio.getOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       Cliente cliente = clienteRepositorio.buscarPorEmail(email);
        
        if (cliente != null){
            
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + cliente.getRol().toString());
            
            permisos.add(p);
            
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            
            HttpSession session = attr.getRequest().getSession(true);
            
            session.setAttribute("usuariosession", cliente);
            
            return new User(cliente.getEmail(), cliente.getClave(), permisos);
        } else{
            return null;
        }
    }
}
