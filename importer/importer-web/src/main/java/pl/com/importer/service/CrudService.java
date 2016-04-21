package pl.com.importer.service;

import java.util.List;

/**
 * Interface CrudService
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface CrudService<T, I> {

    T getElement(I id);

    List<T> getElements();

    void add(T element);

    void update(T element);

    void delete(T element);
}
