/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.TypeDemande;
import java.util.Optional;

import bf.mfptps.appgestionsconges.service.dto.TypeDemandeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */
public interface TypeDemandeService {

    TypeDemandeDTO create(TypeDemandeDTO typeDemande);

    TypeDemandeDTO update(TypeDemandeDTO typeDemande);

    Optional<TypeDemandeDTO> findOne(Long id);

    Page<TypeDemandeDTO> findAll(Pageable pageable);

    void delete(Long id);
}
