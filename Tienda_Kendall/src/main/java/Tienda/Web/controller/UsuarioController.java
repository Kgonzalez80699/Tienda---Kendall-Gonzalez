package Tienda.Web.controller;

//todas las importaciones las cuales se van a usar
import Tienda.Web.domain.Usuario;
import Tienda.Web.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

//Marca la clase como un controlador 
@Controller
@RequestMapping("/usuario")//define que todas las clases van a comenzar por "/usuario"
public class UsuarioController {

    //Servicio que maneja la lógica relacionada con los usuarios
    @Autowired
    private UsuarioService usuarioService;
    
    //muestra el listado de todos los usuarios
    @GetMapping("/listado")
    public String listado(Model model) {
        //obtiene la lista de los usuarios
        var usuarios = usuarioService.getUsuarios();
        model.addAttribute("usuarios", usuarios);//agrega la lista de ususrios para que así la lista pueda acceder
        model.addAttribute("totalUsuarios", usuarios.size());//agrega la cantidad total de usuarios
        return "/usuario/listado";//devuelve la lista de /usuario/listado.html
    }

    @GetMapping("/nuevo")//muestra le formulario para crear usuarios
    public String usuarioNuevo(Usuario usuario) {
        return "/usuario/modifica";//devuleve la vista del formulario
    }



    //guarda un usuario
    @PostMapping("/guardar")
    public String usuarioGuardar(Usuario usuario,
            @RequestParam("imagenFile") MultipartFile imagenFile) {
        if (!imagenFile.isEmpty()) {
            usuarioService.save(usuario,false);
            //usuario.setRutaImagen(
        }
        usuarioService.save(usuario,true);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/eliminar/{idUsuario}")
    public String usuarioEliminar(Usuario usuario) {
        usuarioService.delete(usuario);
        return "redirect:/usuario/listado";
    }

    @GetMapping("/modificar/{idUsuario}")
    public String usuarioModificar(Usuario usuario, Model model) {
        usuario = usuarioService.getUsuario(usuario);
        model.addAttribute("usuario", usuario);
        return "/usuario/modifica";
    }
}
