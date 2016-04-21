package pl.com.importer.assembler;

import pl.com.importer.assembler.imports.ImportAssembler;
import pl.com.importer.assembler.imports.ImportItemAssembler;
import pl.com.importer.assembler.security.UserAssembler;

import java.io.Serializable;

/**
 * Class Assemblers
 *
 * @author Artur Olbromski artur.olbromski@gmail.pl
 */
public class Assemblers implements Serializable {

    public static final ImportAssembler IMPORT_ASSEMBLER = new ImportAssembler();
    public static final ImportItemAssembler IMPORT_ITEM_ASSEMBLER = new ImportItemAssembler();
    public static final UserAssembler USER_ASSEMBLER = new UserAssembler();
}
