package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.Privilege;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PrivilegeService {

    /**
     * Save a permission.
     *
     * @param permission the entity to save.
     * @return the persisted entity.
     */
    Privilege save(Privilege permission);

    /**
     * Get all the permission.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Privilege> findAll(Pageable pageable);

    /**
     * Get all the permission.
     *
     * @return the list of entities.
     */
    List<Privilege> getList();

    /**
     * Get the "id" permission.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Privilege> findOne(Long id);

    /**
     * Delete the "id" permission.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
