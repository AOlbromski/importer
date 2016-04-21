package pl.com.importer.resources.imports;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.com.importer.assembler.Assemblers;
import pl.com.importer.dto.imports.ImportDTO;
import pl.com.importer.dto.imports.ImportItemDTO;
import pl.com.importer.model.imports.Import;
import pl.com.importer.model.user.service.UserService;
import pl.com.importer.resources.BaseResource;
import pl.com.importer.service.imports.ImportService;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Class ImportResource
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@RestController
@RequestMapping("/api/import")
public class ImportResource extends BaseResource {

    @Autowired
    private  ImportService importService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ImportDTO>> list() {
        return new ResponseEntity<>(
                importService.getElements().stream()
                        .map(importElement -> Assemblers.IMPORT_ASSEMBLER.assemblyToDto(importElement)).collect(toList()),
                HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> save(@RequestBody List<ImportItemDTO> itemDTOs) {
        Import importDbo = new Import(
                null,
                new LocalDate(),
                Long.valueOf(itemDTOs.size()),
                itemDTOs.stream()
                        .map(importItem -> Assemblers.IMPORT_ITEM_ASSEMBLER.assemblyToDbo(importItem)).collect(toList()),
                userService.getLoggedUser().getLogin());

        importService.add(importDbo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam Long id) {

        importService.delete(importService.getElement(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
