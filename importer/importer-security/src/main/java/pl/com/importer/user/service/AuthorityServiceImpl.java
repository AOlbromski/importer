package pl.com.importer.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.com.importer.model.security.Authority;
import pl.com.importer.model.user.repository.AuthorityRepository;

import java.util.List;
import java.util.Optional;

/**
 * Class AuthorityServiceImpl
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public Optional<Authority> findOneByName(String authorityName) {
        return authorityRepository.findOneByName(authorityName);
    }

    @Override
    public List<Authority> findAllOrderedByName() {
        return authorityRepository.findAllOrderedByName();
    }
}
