package pl.com.importer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.importer.model.imports.Import;

/**
 * Interface ImportRepository
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */

public interface ImportRepository extends JpaRepository<Import, Long> {

}
