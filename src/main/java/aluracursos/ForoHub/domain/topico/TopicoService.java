package aluracursos.ForoHub.domain.topico;


import aluracursos.ForoHub.domain.topico.validaciones.ValidacionActualizacion;
import aluracursos.ForoHub.domain.topico.validaciones.ValidacionTopico;
import aluracursos.ForoHub.domain.usuario.Usuario;
import aluracursos.ForoHub.domain.usuario.UsuarioRepository;
import aluracursos.ForoHub.infra.errores.ValidacionDeIntegridad;
import aluracursos.ForoHub.infra.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private List<ValidacionTopico> validaciones;
    @Autowired
    private List <ValidacionActualizacion> actualizaciones;

    public Topico agregarTopico(DatosTopico datos, HttpServletRequest request) {
        var user = getAuthenticatedUsuario(request);

        if (usuarioRepository.findById(user.getId()).isEmpty()) {
            throw new ValidacionDeIntegridad("No existe el id para este usuario");
        }
        validaciones.forEach(v -> v.validacion(datos));
        return topicoRepository.save(new Topico(user, datos));
    }

    private Usuario getAuthenticatedUsuario(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.replace("Bearer ", "");
        String subject = tokenService.getSubject(token);
        return (Usuario) usuarioRepository.findByEmail(subject);

    }
    public DetallesTopico actualizar(Long id, ActualizarTopico datos, HttpServletRequest request){
        if (datos.titulo() == null && datos.mensaje() == null) {
            throw new ValidacionDeIntegridad("No hay nada para editar");
        }
        if (!topicoRepository.existsById(id)) {
            throw new ValidacionDeIntegridad("No hay topicos con ese id");
        }

        var user = getAuthenticatedUsuario(request);
        DatosTopico datosTopico = new DatosTopico(datos.titulo(), datos.mensaje(), null);
        validaciones.forEach(v -> v.validacion(datosTopico));
        actualizaciones.forEach(v -> v.validacion(datos, user));

        var topico = topicoRepository.getReferenceById(id);
        if (datos.titulo() != null && datos.mensaje() != null) {
            topico.setMensaje(datos.mensaje());
            topico.setTitulo(datos.titulo());
        } else if (datos.titulo() == null) {
            topico.setMensaje(datos.mensaje());
        } else {
            topico.setTitulo(datos.titulo());
        }
        topico.modificar();
        return new DetallesTopico(topico);
    }
    public void borrar(Long id, HttpServletRequest request){
        if(!topicoRepository.existsById(id)){
            throw new ValidacionDeIntegridad("No existe el topico");
        }
        var usuario = getAuthenticatedUsuario(request);
        DatosActualizarTopico datos = new DatosActualizarTopico(id, null, null);
        actualizaciones.forEach(v->v.validacion(datos, usuario));
        var topico = topicoRepository.getReferenceById(id);
        topico.borrar();
    }

}
