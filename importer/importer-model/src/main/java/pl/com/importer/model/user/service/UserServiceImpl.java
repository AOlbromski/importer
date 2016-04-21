package pl.com.importer.model.user.service;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.importer.model.security.Authority;
import pl.com.importer.model.security.User;
import pl.com.importer.model.user.exception.IncorrectDataException;
import pl.com.importer.model.user.exception.ResourceNotFoundException;
import pl.com.importer.model.user.repository.AuthorityRepository;
import pl.com.importer.model.user.repository.PersistentTokenRepository;
import pl.com.importer.model.user.repository.UserRepository;
import pl.com.importer.model.user.util.RandomUtil;

import java.io.Serializable;
import java.util.*;

/**
 * Class Assemblers
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */

@Service
@Transactional
public class UserServiceImpl implements Serializable, UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    public String getLoggedUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            if (principal instanceof UserDetails) {
                UserDetails userDetails = (UserDetails) principal;
                return userDetails.getUsername();

            }
        }
        return null;
    }

    @Override
    public User getLoggedUser() {
        String loggedUsername = getLoggedUsername();
        if (loggedUsername == null) {
            throw new RuntimeException("User not exists!");
        }
        return findUserByLogin(loggedUsername);
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByLogin(final String login) {
        return userRepository.findOneByLogin(login).orElseThrow(
                () -> new ResourceNotFoundException("Nie znaleziono użytkownika : " + login));
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserByEmail(final String email) {
        Optional<User> userOptional = userRepository.findOneByEmail(email);
        return userOptional.isPresent() ? userOptional.get() : null;
    }

    @Override
    public List<User> getUsersOrderedByLogin() {
        return userRepository.findUsersOrderedByLogin();
    }

    @Override
    public Optional<User> findUserById(final Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void assignAuthority(final Long userId, final String authorityName) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        Authority authority = authorityRepository.findOneByName(authorityName)
                .orElseThrow(ResourceNotFoundException::new);
        user.getAuthorities().add(authority);
        userRepository.save(user);
    }

    @Override
    public void deleteAuthority(Long userId, String authorityName) {
        User user = userRepository.findById(userId).orElseThrow(ResourceNotFoundException::new);
        Authority authority =
                user.getAuthorities().stream().filter(auth -> auth.getName().equals(authorityName))
                        .findAny().orElseThrow(IncorrectDataException::new);
        user.getAuthorities().remove(authority);
        userRepository.save(user);
    }

    @Override
    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        userRepository.findOneByActivationKey(key).map(user -> {
            user.setActivated(true);
            user.setActivationKey(null);
            userRepository.save(user);
            log.debug("Activated user: {}", user);
            return user;
        });
        return Optional.empty();
    }

    @Override
    public User createUserInformation(String login, String password, String firstName,
                                      String lastName, String email, String langKey, String pesel) {

        final Authority authority = authorityRepository.findOne("ROLE_USER");
        final Set<Authority> authorities = new HashSet<>(Collections.singletonList(authority));

        User user =
                new User(null, login, passwordEncoder.encode(password), firstName, lastName, email,
                         false, langKey, RandomUtil.generateActivationKey(), pesel, authorities,
                         null);

        return user;
    }

    @Override
    public void updateUserInformation(String firstName, String lastName, String email,
                                      String pesel) {
        userRepository.findOneByLogin(getLoggedUsername()).ifPresent(u -> {
            u.setFirstName(firstName);
            u.setLastName(lastName);
            u.setEmail(email);
            u.setPesel(pesel);
            userRepository.save(u);
            log.debug("Changed Information for User: {}", u);
        });
    }

    @Override
    public void changePassword(String password) {
        userRepository.findOneByLogin(getLoggedUsername()).ifPresent(u -> {
            String encryptedPassword = passwordEncoder.encode(password);
            u.setPassword(encryptedPassword);
            userRepository.save(u);
            log.debug("Changed password for User: {}", u);
        });
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserWithAuthorities() {
        User currentUser = userRepository.findOneByLogin(getLoggedUsername()).get();
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }

    @Scheduled(cron = "0 0 0 * * ?")
    @Override
    public void removeOldPersistentTokens() {
        LocalDate now = new LocalDate();
        persistentTokenRepository.findByTokenDateBefore(now.minusMonths(1)).stream()
                .forEach(token -> {
                    log.debug("Deleting token {}", token);
                    User user = token.getUser();
                    user.getPersistentTokens().remove(token);
                    persistentTokenRepository.delete(token);
                });
    }

    @Scheduled(cron = "0 0 1 * * ?")
    @Override
    public void removeNotActivatedUsers() {
        DateTime now = new DateTime();
        List<User> users =
                userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minusDays(3));
        for (User user : users) {
            log.debug("Deleting not activated user {}", user.getLogin());
            userRepository.delete(user);
        }
    }

    @Override
    public Optional<User> findOneByLogin(final String login) {
        return userRepository.findOneByLogin(login);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
