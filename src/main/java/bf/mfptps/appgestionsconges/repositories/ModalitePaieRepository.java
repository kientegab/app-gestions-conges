/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.ModalitePaie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author HEBIE
 */
public interface ModalitePaieRepository extends JpaRepository<ModalitePaie, Long>, JpaSpecificationExecutor<ModalitePaie> {

    @Query("select m from ModalitePaie m where m.deleted=false AND m.desactiver=false")
    public Page<ModalitePaie> getAllActivatedOperateur(Pageable pageable);
}
