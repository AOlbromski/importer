package pl.com.importer.model;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

/**
 * Interface ExchangeEntity.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public interface ExchangeEntity<T> {

    default void exchange(final T object) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);

        modelMapper.map(object, this);
    }
}