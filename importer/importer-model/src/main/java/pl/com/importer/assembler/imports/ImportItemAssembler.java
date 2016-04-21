package pl.com.importer.assembler.imports;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import pl.com.importer.assembler.Assembler;
import pl.com.importer.dto.imports.ImportItemDTO;
import pl.com.importer.model.imports.ImportItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class ImportItemAssembler.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public class ImportItemAssembler implements Assembler<ImportItem, ImportItemDTO> {

    private ModelMapper modelMapper = new ModelMapper();

    {
        modelMapper.getConfiguration().setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    @Override
    public ImportItem assemblyToDbo(final ImportItemDTO dto) {
        return modelMapper.map(dto, ImportItem.class);
    }

    @Override
    public List<ImportItem> assemblyToDbo(final List<ImportItemDTO> dto) {
        return dto.stream().map(this::assemblyToDbo).collect(Collectors.toList());
    }

    @Override
    public ImportItemDTO assemblyToDto(final ImportItem dbo) {
        return modelMapper.map(dbo, ImportItemDTO.class);
    }

    @Override
    public List<ImportItemDTO> assemblyToDto(final List<ImportItem> dbo) {
        return dbo.stream().map(this::assemblyToDto).collect(Collectors.toList());
    }
}
