package aluracursos.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record RespuestaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        String estado,
        Long idUsuario,
        String curso
) {
    public RespuestaTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion(),topico.getEstado().toString(),topico.getUsuario().getId(),topico.getCurso());
    }
}
