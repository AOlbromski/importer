package pl.com.importer.model.imports;

import lombok.*;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import pl.com.importer.model.ExchangeEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Import.
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
@Entity
@Table(name = "import", schema = "import")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Import implements Serializable, ExchangeEntity<Import> {

    private static final long serialVersionUID = 808634203799771361L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Setter(AccessLevel.NONE)
    private Long id;

    @Column(name = "import_date", nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
    @Setter(AccessLevel.NONE)
    private LocalDate importDate;

    @Column(name = "imported_items_number", nullable = false)
    @Setter(AccessLevel.NONE)
    private Long itemsNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_id", referencedColumnName = "id")
    private List<ImportItem> importItems = new ArrayList<>();

    @Column(name = "import_user", nullable = false)
    @Setter(AccessLevel.NONE)
    private String importerUser;

    public Import(Long id) {
        this.id = id;
    }
}
