package pl.com.importer.resources.imports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.com.importer.assembler.Assemblers;
import pl.com.importer.dto.imports.ImportItemDTO;
import pl.com.importer.resources.BaseResource;
import pl.com.importer.service.imports.ImportItemService;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Class ImportItemResource
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@RestController
@RequestMapping("/api/item")
public class ImportItemResource extends BaseResource {

    @Autowired
    private ImportItemService importItemService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ImportItemDTO>> list() {
        return new ResponseEntity<>(
                importItemService.getElements().stream()
                        .map(importItemElement -> Assemblers.IMPORT_ITEM_ASSEMBLER.assemblyToDto(importItemElement)).collect(toList()),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam Long id) {

        importItemService.delete(importItemService.getElement(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
