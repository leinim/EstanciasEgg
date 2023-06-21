/*La entidad familia modela las familias que habitan en diferentes países y que ofrecen alguna de las
habitaciones de su hogar para acoger a algún chico (por un módico precio). De cada una de estas
familias se conoce el nombre, la edad mínima y máxima de sus hijos, número de hijos y correo
electrónico. El repositorio que persiste a esta entidad (FamiliaRepositorio) debe contener los
métodos necesarios para guardar/actualizar los datos de las familias en la base de datos, realizar
consultas y eliminar o dar de baja según corresponda.*/
package com.egg.estancias.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Familia extends Usuario {
   
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombre;
    private int edadMin;
    private int edadMax;
    private int numHijos;
    @OneToOne
    private Casa casa;    
    
}
