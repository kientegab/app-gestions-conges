package bf.mfptps.appgestionsconges.service;

import java.util.Optional;

import bf.mfptps.appgestionsconges.entities.Structure;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.dto.DemandeDTO;
import bf.mfptps.appgestionsconges.service.dto.ValidationDTO;

public interface DemandeService {

	DemandeDTO create(DemandeDTO Demande, MultipartFile[] fichiersJoint);

	DemandeDTO update(DemandeDTO Demande);

    Optional<Demande> get(String numeroDemande);

    Optional<Demande> get(Long id);

    Page<DemandeDTO> findAll(Pageable pageable);
    
    //TODO: filtrer les demandes en fonction de critere de selection

    void delete(Long code);
    
   /// DemandeDTO validateDemande(ValidationDTO validationDTO);
    DemandeDTO validation_sh(ValidationDTO validationDTO, boolean islastValidationNode);
    DemandeDTO validation_sg(ValidationDTO validationDTO, boolean islastValidationNode);
    DemandeDTO validation_dg(ValidationDTO validationDTO, boolean islastValidationNode);
    DemandeDTO validation_drh(ValidationDTO validationDTO, boolean islastValidationNode);

    Page<DemandeDTO> getAbsenceByMatricule(String matricule, Pageable pageable);
    Page<DemandeDTO> getAbsenceByStructure(Long structureId, Pageable pageable);
    Page<DemandeDTO> getCongeByMatricule(String matricule, Pageable pageable);
    Page<DemandeDTO> getCongeByStructure(Long structureId, Pageable pageable);

    Page<DemandeDTO> getJouissanceByStructure(Long structureId, Pageable pageable);
    Page<DemandeDTO> getDemandesValid(String codeTypeDmd, Pageable pageable);
}
