package br.com.ecoground.user.user_ecoground.auth.service;

import br.com.ecoground.user.user_ecoground.auth.dto.AuthRequest;
import br.com.ecoground.user.user_ecoground.auth.dto.AuthResponse;

public interface AuthService {
    public AuthResponse register(AuthRequest request);
    public AuthResponse login(AuthRequest request);
}
