package pl.com.importer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import pl.com.importer.model.imports.ImportItem;

/**
 * Interface ImportItemRepository
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */

public interface ImportItemRepository extends JpaRepository<ImportItem, Long> {

}
