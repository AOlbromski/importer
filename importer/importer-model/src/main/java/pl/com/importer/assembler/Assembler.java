package pl.com.importer.assembler;

import java.util.List;

/**
 * Interface for assemblers.
 *
 * @param <T1> dbo objects type
 * @param <T2> dto objects type
 */
public interface Assembler<T1, T2> {

    /**
     * Converet dto objects to dbo objects.
     *
     * @param dto object
     * @return dbo object
     */
    T1 assemblyToDbo(T2 dto);

    /**
     * Convert collection of dtos to dbos.
     *
     * @param dto dto objects
     * @return collection of dbo objects
     */
    List<T1> assemblyToDbo(List<T2> dto);

    /**
     * Converet dbo objects to dto objects.
     *
     * @param dbo object
     * @return dto object
     */
    T2 assemblyToDto(T1 dbo);

    /**
     * Convert to dto objects.
     *
     * @param dbo dbo objects
     * @return collection of dto
     */
    List<T2> assemblyToDto(List<T1> dbo);
}
