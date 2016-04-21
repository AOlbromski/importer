package pl.com.importer.model.user.repository;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.com.importer.model.security.User;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    Optional<User> findOneByEmail(String email);

    Optional<User> findOneByLogin(String login);

    void delete(User user);

    @Query("SELECT user FROM User user ORDER BY user.login ASC")
    List<User> findUsersOrderedByLogin();

    Optional<User> findById(Long userId);

    @Query("SELECT user FROM User user WHERE user.login like :login")
    List<User> findUserByLoginLike(@Param("login") String login);
}
