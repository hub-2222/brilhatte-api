package com.brilhatte.app.infra.security;

import com.brilhatte.app.dtos.auth.AuthDTO;
import com.brilhatte.app.dtos.auth.CadastroDTO;
import com.brilhatte.app.dtos.auth.LoginResponseDTO;
import com.brilhatte.app.models.user.User;
import com.brilhatte.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthDTO data){
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);
        String token = jwtService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/signup")
    public ResponseEntity register(@RequestBody CadastroDTO data){
        if (userRepository.findByUsername(data.username()) != null){
            return ResponseEntity.badRequest().body("Usuário já existe");
        }

        String encodedPassword = new BCryptPasswordEncoder().encode(data.password());
        User newUser = new User(data.username(), encodedPassword, data.role());
        userRepository.save(newUser);

        return ResponseEntity.ok(data);
    }
}