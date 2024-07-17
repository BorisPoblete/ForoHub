package aluracursos.ForoHub.domain.topico.validaciones;

import aluracursos.ForoHub.domain.topico.ActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosActualizarTopico;
import aluracursos.ForoHub.domain.topico.TopicoRepository;
import aluracursos.ForoHub.domain.usuario.Usuario;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ValidacionAutor implements ValidacionActualizacion{
    @Autowired
    private TopicoRepository repository;

    @Override
    public void validacion(DatosActualizarTopico data, Usuario user) {
        var topic = repository.findById(data.id()).orElse(null);

        assert topic != null;
        if (!Objects.equals(topic.getUsuario().getId(), user.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }

    @Override
    public void validacion(ActualizarTopico data, Usuario user) {
        var topic = repository.findById(user.getId()).orElse(null);
        assert topic != null;
        if (!Objects.equals(topic.getUsuario().getId(), user.getId())) {
            throw new ValidationException("Este topico no le pertenece a este usuario");
        }
    }
}
