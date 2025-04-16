package com.ecommerce.auth.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.auth.dto.LoginRequestDTO;
import com.ecommerce.auth.dto.RegisterRequestDTO;
import com.ecommerce.auth.jwt.JwtTokenProvider;
import com.ecommerce.user.model.AuthUserModel;
import com.ecommerce.user.repository.AuthUserRepository;

@Service
public class AuthService {
	
    private final AuthUserRepository authUserRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(AuthUserRepository authUserRepository, JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authUserRepository = authUserRepository;
		this.passwordEncoder = new BCryptPasswordEncoder();
    }

	public String authenticate(LoginRequestDTO loginRequest) {
		AuthUserModel user = authUserRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new RuntimeException("Credenciais inválidas"));

		if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPasswordHash())) {
			throw new RuntimeException("Credenciais inválidas");
		}

		return jwtTokenProvider.generateToken(user.getEmail());
	}
    
    public void register(RegisterRequestDTO request) {
    	 if (authUserRepository.findByEmail(request.getEmail()).isPresent()) {
             throw new RuntimeException("E-mail já registrado");
         }

        String hashed = new BCryptPasswordEncoder().encode(request.getPassword());
        AuthUserModel newUser = new AuthUserModel(request.getEmail(), hashed);
        authUserRepository.save(newUser);

    }

}
