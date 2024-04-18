package es.fct.security.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;

import es.fct.security.jwt.JwtService;
import es.fct.security.model.AuthResponse;
import es.fct.security.model.LoginRequest;
import es.fct.security.model.RegisterRequest;
import es.fct.security.role.Role;
import es.fct.security.role.RoleRepository;
import es.fct.security.user.User;
import es.fct.security.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();
    }

    public AuthResponse register(RegisterRequest request) {
        Role defaultRole = roleRepository.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Default role not found")); 
            
        Role role = request.getUsername().equalsIgnoreCase("admin") ? 
                    roleRepository.findByName("ADMIN").orElse(defaultRole) : defaultRole;

        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode(request.getPassword()))
            .email(request.getEmail())
            .role(role)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
    }
}

