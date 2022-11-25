package bf.mfptps.appgestionsconges.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;

public interface DemandeService {

	DemandeDTO create(DemandeDTO Demande, MultipartFile[] fichiersJoint);

	DemandeDTO update(DemandeDTO Demande);

    Optional<Demande> get(String numeroDemande);

    Optional<Demande> get(Long id);

    Page<DemandeDTO> findAll(Pageable pageable);
    
    //TODO: filtrer les demandes en fonction de critere de selection

    void delete(Long code);

}
