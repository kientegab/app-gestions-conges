/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appnomination.service;

import bf.mfptps.appnomination.entities.TypeStructure;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public interface TypeStructureService {

    TypeStructure create(TypeStructure typeStructure);

    TypeStructure update(TypeStructure typeStructure);

    Optional<TypeStructure> get(long id);

    Page<TypeStructure> findAll(Pageable pageable);

    void delete(Long id);
}
