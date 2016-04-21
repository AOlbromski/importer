package pl.com.importer.service.imports;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.com.importer.model.imports.Import;
import pl.com.importer.repository.ImportRepository;

import java.util.List;

/**
 * Class ImportServiceImpl.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Service
@Transactional
public class ImportServiceImpl implements ImportService {

    @Autowired
    private ImportRepository importRepository;

    Logger logger = Logger.getLogger(getClass());

    @Override
    @Transactional(readOnly = true)
    public Import getElement(final Long id) {
        return importRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Import> getElements() {
        return importRepository.findAll();
    }

    @Override
    public void add(final Import element) {
        importRepository.save(element);
    }

    @Override
    public void update(final Import element) {
        importRepository.save(element);
    }

    @Override
    public void delete(final Import element) {
        importRepository.delete(element);
    }


}
