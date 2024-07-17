package aluracursos.ForoHub.domain.topico;


import aluracursos.ForoHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Topico")
@Table(name = "Topicos")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    @Enumerated(EnumType.STRING)
    private TopicoEstado estado;
    @JoinColumn(name="usuario_id")
    @ManyToOne
    private Usuario usuario;
    private String curso;

    public Topico(Usuario usuario, DatosTopico topico){
        this.titulo = topico.titulo();
        this.mensaje = topico.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.estado = TopicoEstado.CREADO;
        this.usuario = usuario;
        this.curso = topico.curso();
    }
    public void borrar() {
        this.estado = TopicoEstado.ELIMINADO;
    }

    public void modificar() {
        this.estado = TopicoEstado.MODIFICADO;
    }

}
