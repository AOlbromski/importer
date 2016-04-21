package pl.com.importer.resources.user;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.importer.config.security.util.SecurityUtils;
import pl.com.importer.dto.security.UserDTO;
import pl.com.importer.model.security.Authority;
import pl.com.importer.model.security.PersistentToken;
import pl.com.importer.model.user.repository.PersistentTokenRepository;
import pl.com.importer.model.user.repository.UserRepository;
import pl.com.importer.model.user.service.UserService;
import pl.com.importer.resources.BaseResource;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class UserResource extends BaseResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserRepository userRepository;
    private final UserService userService;
    private final PersistentTokenRepository persistentTokenRepository;

    @Autowired
    public UserResource(final UserRepository userRepository,
                        final UserService userService,
                        final PersistentTokenRepository persistentTokenRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.persistentTokenRepository = persistentTokenRepository;
    }

    private boolean loginIsNotUnique(String userLogin) {
        return userRepository.findOneByLogin(userLogin).isPresent();
    }

    private boolean emailIsNotUnique(String email) {
        return userRepository.findOneByEmail(email).isPresent();
    }

    @RequestMapping(value = "/authenticate",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String isAuthenticated(HttpServletRequest request) {
        log.debug("REST request to check if the current user is authenticated");
        return request.getRemoteUser();
    }

    @RequestMapping(value = "/account",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getAccount() {
        return Optional.ofNullable(userService.getUserWithAuthorities())
                .map(user -> new ResponseEntity<>(
                        new UserDTO(user.getLogin(), null, user.getFirstName(), user.getLastName(),
                                    user.getEmail(), user.getLangKey(),
                                    user.getAuthorities().stream().map(Authority::getName)
                                            .collect(Collectors.toSet()), user.getPesel()),
                        HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/account/sessions",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersistentToken>> getCurrentSessions() {
        return userRepository.findOneByLogin(SecurityUtils.getCurrentLogin())
                .map(user -> new ResponseEntity<>(persistentTokenRepository.findByUser(user),
                                                  HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @RequestMapping(value = "/account/sessions/{series}",
            method = RequestMethod.DELETE)
    public void invalidateSession(@PathVariable String series) throws UnsupportedEncodingException {
        String decodedSeries = URLDecoder.decode(series, "UTF-8");
        userRepository.findOneByLogin(SecurityUtils.getCurrentLogin()).ifPresent(u -> {
            persistentTokenRepository.findByUser(u).stream().filter(persistentToken -> StringUtils
                    .equals(persistentToken.getSeries(), decodedSeries)).findAny()
                    .ifPresent(t -> persistentTokenRepository.delete(decodedSeries));
        });
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }
    public UserService getUserService() {
        return userService;
    }

    public PersistentTokenRepository getPersistentTokenRepository() {
        return persistentTokenRepository;
    }
}
