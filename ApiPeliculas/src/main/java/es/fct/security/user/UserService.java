package es.fct.security.user;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse updateUser(UserRequest userRequest) {
        User user = User.builder()
                .id(userRequest.getId())
                .username(userRequest.getUsername())
                .email(userRequest.getEmail())
                .password(userRequest.getPassword())
                .build();

        userRepository.updateUser(user.getId(), user.getUsername(), user.getEmail(), user.getPassword());

        return new UserResponse("El usuario se actualizó satisfactoriamente");
    }

    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);

        if (user != null) {
            UserDTO userDTO = UserDTO.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .email(user.getEmail())
                    .password(user.getPassword())
                    .build();
            return userDTO;
        }
        return null;
    }
}
