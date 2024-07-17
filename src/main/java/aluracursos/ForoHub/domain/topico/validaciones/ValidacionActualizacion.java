package aluracursos.ForoHub.domain.topico.validaciones;

import aluracursos.ForoHub.domain.topico.ActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosActualizarTopico;
import aluracursos.ForoHub.domain.usuario.Usuario;

public interface ValidacionActualizacion {
    void validacion(DatosActualizarTopico datos, Usuario usuario);

    void validacion(ActualizarTopico data, Usuario usuario);
}
