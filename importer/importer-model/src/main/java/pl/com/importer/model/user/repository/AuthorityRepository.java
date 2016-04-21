package pl.com.importer.model.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.com.importer.model.security.Authority;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Authority entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Optional<Authority> findOneByName(String authorityName);

    @Query("SELECT authority FROM Authority authority ORDER BY authority.name ASC")
    List<Authority> findAllOrderedByName();

}
