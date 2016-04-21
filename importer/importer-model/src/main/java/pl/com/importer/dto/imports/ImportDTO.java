package pl.com.importer.dto.imports;

import lombok.*;
import org.joda.time.LocalDate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class ImportDTO.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportDTO implements Serializable {

    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private LocalDate importDate;

    @Setter(AccessLevel.NONE)
    private Long itemsNumber;

    @Setter(AccessLevel.NONE)
    private List<ImportItemDTO> importItems = new ArrayList<>();

    @Setter(AccessLevel.NONE)
    private String importerUser;
}
