package pl.com.importer.model.user.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;
import pl.com.importer.model.security.User;

import java.util.List;
import java.util.Optional;

/**
 * Interface UserService
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface UserService {

    String getLoggedUsername();

    User getLoggedUser();

    User findUserByLogin(String login);

    User findUserByEmail(String email);

    List<User> getUsersOrderedByLogin();

    Optional<User> findUserById(Long userId);

    void assignAuthority(Long userId, String authority);

    void deleteAuthority(Long userId, String authority);

    Optional<User> activateRegistration(String key);

    void changePassword(String password);

    User createUserInformation(String login, String password, String firstName, String lastName,
                               String email, String langKey, String pesel);

    @Transactional(readOnly = true)
    User getUserWithAuthorities();

    @Scheduled(cron = "0 0 1 * * ?")
    void removeNotActivatedUsers();

    @Scheduled(cron = "0 0 0 * * ?")
    void removeOldPersistentTokens();

    void updateUserInformation(String firstName, String lastName, String email, String pesel);

    Optional<User> findOneByLogin(String login);

    List<User> findAll();

    User save(User user);
}
