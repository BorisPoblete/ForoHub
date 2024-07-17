package aluracursos.ForoHub.domain.topico.validaciones;

import aluracursos.ForoHub.domain.topico.ActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosActualizarTopico;
import aluracursos.ForoHub.domain.topico.DatosTopico;

public interface ValidacionTopico {
    void validacion(DatosTopico datos);

    void validacion(ActualizarTopico datos);
}
