package pl.com.importer.user.service;


import pl.com.importer.model.security.Authority;

import java.util.List;
import java.util.Optional;

/**
 * Interface AuthorityService
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface AuthorityService {

    Optional<Authority> findOneByName(String authorityName);

    List<Authority> findAllOrderedByName();
}
