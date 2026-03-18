package ar.com.lucas.bankingapp.service.impl;

import ar.com.lucas.bankingapp.dto.RegisterRequestDTO;
import ar.com.lucas.bankingapp.entity.AppUser;
import ar.com.lucas.bankingapp.entity.Provider;
import ar.com.lucas.bankingapp.repository.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthService {

	private final AppUserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public AuthService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public void register(RegisterRequestDTO dto) {
		if (userRepository.existsByUsername(dto.getUsername())) {
			throw new IllegalArgumentException("El username ya está en uso");
		}
		if (userRepository.existsByEmail(dto.getEmail())) {
			throw new IllegalArgumentException("El email ya está registrado");
		}

		AppUser user = new AppUser();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(passwordEncoder.encode(dto.getPassword()));
		user.setProvider(Provider.LOCAL);
		user.setEnabled(true);
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setRoles(Set.of()); // sin roles por ahora, agregás cuando tengas la entidad Roles

		userRepository.save(user);
	}
}