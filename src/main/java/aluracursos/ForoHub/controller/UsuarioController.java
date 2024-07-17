package aluracursos.ForoHub.controller;

import aluracursos.ForoHub.domain.usuario.DatosUsuario;
import aluracursos.ForoHub.domain.usuario.Usuario;
import aluracursos.ForoHub.infra.security.DatosJwtToken;
import aluracursos.ForoHub.infra.security.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@ResponseBody
@RequestMapping("/login")
@SecurityRequirement(name ="bearer-key")
public class UsuarioController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DatosJwtToken> getLogin(@RequestBody @Valid DatosUsuario datosUsusario) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosUsusario.email(), datosUsusario.password());
        var userAuth = authenticationManager.authenticate(authenticationToken);
        var token = tokenService.generarToken((Usuario) userAuth.getPrincipal());
        return ResponseEntity.ok(new DatosJwtToken(token));
    }
}
