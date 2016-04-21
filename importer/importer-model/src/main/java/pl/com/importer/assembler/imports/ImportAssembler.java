package pl.com.importer.assembler.imports;

import pl.com.importer.assembler.Assembler;
import pl.com.importer.assembler.Assemblers;
import pl.com.importer.dto.imports.ImportDTO;
import pl.com.importer.dto.imports.ImportItemDTO;
import pl.com.importer.model.imports.Import;
import pl.com.importer.model.imports.ImportItem;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class ImportAssembler.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public class ImportAssembler implements Assembler<Import, ImportDTO> {

    @Override
    public Import assemblyToDbo(final ImportDTO dto) {

        List<ImportItem> importItems =
                Assemblers.IMPORT_ITEM_ASSEMBLER.assemblyToDbo(dto.getImportItems());

        return new Import(dto.getId(), dto.getImportDate(), dto.getItemsNumber(),
                           importItems, dto.getImporterUser());
    }

    @Override
    public List<Import> assemblyToDbo(final List<ImportDTO> dto) {
        return dto.stream().map(this::assemblyToDbo).collect(Collectors.toList());
    }

    @Override
    public ImportDTO assemblyToDto(final Import dbo) {
        List<ImportItemDTO> importItemDTOs =
                Assemblers.IMPORT_ITEM_ASSEMBLER.assemblyToDto(dbo.getImportItems());

        return new ImportDTO(dbo.getId(), dbo.getImportDate(), dbo.getItemsNumber(),
                              importItemDTOs, dbo.getImporterUser());
    }

    @Override
    public List<ImportDTO> assemblyToDto(final List<Import> dbo) {
        return dbo.stream().map(this::assemblyToDto).collect(Collectors.toList());
    }
}
