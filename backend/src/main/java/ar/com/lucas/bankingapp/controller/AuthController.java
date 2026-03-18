package ar.com.lucas.bankingapp.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.com.lucas.bankingapp.dto.RegisterRequestDTO;
import ar.com.lucas.bankingapp.security.JwtUtil;
import ar.com.lucas.bankingapp.service.impl.AuthService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authManager;
	private final JwtUtil jwtUtil;
	private final AuthService authService;

	public AuthController(AuthenticationManager authManager, JwtUtil jwtUtil, AuthService authService) {
		this.authManager = authManager;
		this.jwtUtil = jwtUtil;
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
		String username = body.get("username");
		String password = body.get("password");

		authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		String token = jwtUtil.generateToken(username);
		return ResponseEntity.ok(Map.of("token", token, "username", username));
	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO dto) {
		try {
			authService.register(dto);
			return ResponseEntity.ok(Map.of("message", "Usuario registrado correctamente"));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
		}
	}
}
