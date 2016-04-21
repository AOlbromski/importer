package pl.com.importer.assembler;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class Assemblers
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */

public abstract class AbstractAssembler<T1, T2> implements Assembler<T1, T2> {

    protected ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }


    public List<T2> assemblyToDto(final List<T1> dbo) {
        return dbo.stream().map(this::assemblyToDto).collect(Collectors.toList());
    }

    public List<T1> assemblyToDbo(final List<T2> dto) {
        return dto.stream().map(this::assemblyToDbo).collect(Collectors.toList());
    }

}
