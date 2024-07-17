package aluracursos.ForoHub.domain.topico.validaciones;

import aluracursos.ForoHub.domain.topico.ActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosTopico;
import aluracursos.ForoHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidacionTitulo implements ValidacionTopico{
    @Autowired
    TopicoRepository repository;

    @Override
    public void validacion(DatosTopico datos) {
        var titulo = repository.existsByTitulo(datos.titulo());
        if (titulo) {
            throw new ValidationException("Ya existe un topico con este titulo");
        }
    }

    @Override
    public void validacion(ActualizarTopico datos) {
        var titulo = repository.existsByTitulo(datos.titulo());
        if (titulo) {
            throw new ValidationException("Ya existe un topico con este titulo.");
        }
    }

}
