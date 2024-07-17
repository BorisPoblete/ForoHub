package aluracursos.ForoHub.domain.topico;

import java.time.LocalDateTime;

public record DetallesTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        TopicoEstado estado,
        Long usuario
) {
    public DetallesTopico(Topico topic) {
        this(topic.getTitulo(), topic.getMensaje(), topic.getFechaCreacion(), topic.getEstado(), topic.getUsuario().getId());
    }
}
