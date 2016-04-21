package pl.com.importer.dto.imports;

import lombok.*;

import java.io.Serializable;

/**
 * Class ImportItemDTO.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImportItemDTO implements Serializable{

    @Setter(AccessLevel.NONE)
    private Long id;

    @Setter(AccessLevel.NONE)
    private String mark;

    @Setter(AccessLevel.NONE)
    private String model;

    @Setter(AccessLevel.NONE)
    private String licencePlate;
}
