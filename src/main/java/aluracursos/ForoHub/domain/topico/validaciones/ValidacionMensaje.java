package aluracursos.ForoHub.domain.topico.validaciones;

import aluracursos.ForoHub.domain.topico.ActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosTopico;
import aluracursos.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionMensaje implements ValidacionTopico{

    @Autowired
    TopicoRepository repository;
    @Override
    public void validacion(DatosTopico data) {
        var mensaje = repository.existsByMensaje(data.mensaje());
        if (mensaje) {
            throw new ValidationException("Ya existe un topico con este mensaje.");
        }
    }

    @Override
    public void validacion(ActualizarTopico data) {
        var mensaje = repository.existsByMensaje(data.mensaje());
        if (mensaje) {
            throw new ValidationException("Ya existe un topico con este mensaje.");
        }
    }
}
