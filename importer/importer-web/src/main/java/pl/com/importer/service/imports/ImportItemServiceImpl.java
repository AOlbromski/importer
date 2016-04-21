package pl.com.importer.service.imports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.importer.model.imports.ImportItem;
import pl.com.importer.repository.ImportItemRepository;

import java.util.List;

/**
 * Class ImportItemServiceImpl.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Service
@Transactional
public class ImportItemServiceImpl implements ImportItemService {

    @Autowired
    private ImportItemRepository importItemRepository;

    Logger logger = Logger.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public ImportItem getElement(final Long id) {
        return importItemRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ImportItem> getElements() {
        return importItemRepository.findAll();
    }

    @Override
    public void add(final ImportItem element) {
        importItemRepository.save(element);
    }

    @Override
    public void update(final ImportItem element) {
        importItemRepository.save(element);
    }

    @Override
    public void delete(final ImportItem element) {
        importItemRepository.delete(element);
    }


}
