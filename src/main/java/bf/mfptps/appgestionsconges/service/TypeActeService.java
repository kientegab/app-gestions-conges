package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.TypeActe;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeActeService {

    /**
     * Save a permission.
     *
     * @param permission the entity to save.
     * @return the persisted entity.
     */
    TypeActe save(TypeActe typeActe);

    /**
     * Get all the permission.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TypeActe> findAll(Pageable pageable);

    /**
     * Get all the permission.
     *
     * @return the list of entities.
     */
    List<TypeActe> getList();

    /**
     * Get the "id" permission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeActe> findOne(Long id);

    /**
     * Delete the "id" permission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
