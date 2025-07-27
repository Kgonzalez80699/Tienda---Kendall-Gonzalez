package Tienda.Web.dao;

import Tienda.Web.domain.Usuario;//importa la clase Usuario de domain
import org.springframework.data.jpa.repository.JpaRepository;//importa JpaRepository de spring 
//La interfaz define métodos para que pueda acceder a la base de datos de la entidad Usuario.
//A la hora de extender JpaRepository, hereda métodos como los son save(), delete(), findById(), findAll(), etc
public interface UsuarioDao extends JpaRepository<Usuario, Long> {
    //busca un usuario por el nombre, con esto se utiliza para poder iniciar sesion o verificar la existencia
    Usuario findByUsername(String username);
    
    //busca un nombre o usuario y contraseña, con esto es util para poder hacer la verificacion
    Usuario findByUsernameAndPassword(String username, String Password);
    
    //puede usarse para evitar registros duplicados a la hora de validar el registro
    Usuario findByUsernameOrCorreo(String username, String correo);
    
    //con esto verifica si existe un usuario con el username o con el correo dado
    boolean existsByUsernameOrCorreo(String username, String correo);
}