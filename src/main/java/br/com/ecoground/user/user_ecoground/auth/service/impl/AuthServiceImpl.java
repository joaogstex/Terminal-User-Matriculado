package br.com.ecoground.user.user_ecoground.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ecoground.user.user_ecoground.auth.dto.AuthRequest;
import br.com.ecoground.user.user_ecoground.auth.dto.AuthResponse;
import br.com.ecoground.user.user_ecoground.auth.entity.UsuarioSignUp;
import br.com.ecoground.user.user_ecoground.auth.enums.Role;
import br.com.ecoground.user.user_ecoground.auth.repository.UserRepository;
import br.com.ecoground.user.user_ecoground.auth.service.AuthService;
import br.com.ecoground.user.user_ecoground.auth.util.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(AuthRequest request) {
         if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("Usuário já existe");
        }

        UsuarioSignUp user = new UsuarioSignUp();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER); // ou ADMIN dependendo da lógica

        userRepository.save(user);

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UsuarioSignUp user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }
    
}
