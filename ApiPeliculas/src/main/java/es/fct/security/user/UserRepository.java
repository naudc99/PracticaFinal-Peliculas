package es.fct.security.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query("update User u set u.username = :username, u.email = :email, u.password = :password where u.id = :id")
    void updateUser(@Param("id") Long id, @Param("username") String username, @Param("email") String email, @Param("password") String password);

}

