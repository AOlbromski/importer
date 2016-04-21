package pl.com.importer.model.imports;

import lombok.*;
import pl.com.importer.model.ExchangeEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class ImportItem.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Entity
@Table(name = "import_item", schema = "import")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ImportItem implements Serializable, ExchangeEntity<ImportItem> {

    private static final long serialVersionUID = -4698211774951381511L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "mark", length = 128, nullable = false)
    @Setter(AccessLevel.NONE)
    private String mark;

    @Column(name = "model", nullable = false)
    @Setter(AccessLevel.NONE)
    private String model;

    @Column(name = "licence_plate", nullable = false)
    @Setter(AccessLevel.NONE)
    private String licencePlate;

    public ImportItem(Long id) {
        this.id = id;
    }
}
