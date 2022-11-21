/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.entities.TypeStructure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TypeStructureRepository extends JpaRepository<TypeStructure, Long>, JpaSpecificationExecutor<TypeStructure> {

}
