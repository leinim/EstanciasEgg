/*La entidad usuario modela los datos de un usuario que accede al sistema para registrarse como
familia y ofrecer una habitación de su casa para estancias, o bien, como un cliente que necesita
realizar una reserva. De cada usuario se debe registrar el nombre de usuario (alias), el correo
electrónico, el password y la fecha de alta. El repositorio que persiste a esta entidad
(UsuarioRepositorio) debe contener los métodos necesarios para registrar el usuario en la base de
datos, realizar consultas y eliminar.*/
package com.egg.estancias.entidades;

import com.egg.estancias.enumeraciones.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    protected String alias;
    protected String email;
    protected String clave;
    @Temporal(TemporalType.DATE)
    protected Date fechaAlta;
    @Temporal(TemporalType.DATE)
    protected Date fechaBaja;
    protected Rol rol;
    
}
