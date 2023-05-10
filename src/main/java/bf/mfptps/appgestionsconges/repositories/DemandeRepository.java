package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Agent;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.enums.EPositionDemande;
import bf.mfptps.appgestionsconges.enums.EStatusDemande;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface DemandeRepository extends JpaRepository<Demande, Long>, JpaSpecificationExecutor<Demande> {

    Optional<Demande> findByNumeroDemande(String numeroDemande);

    @Query("SELECT d FROM Demande d "
            + "WHERE d.id = :demandeID "
            + "AND d.deleted = false")
    Ministere findDemandeByID(Long demandeID);

    @Query("SELECT d FROM Demande d "
            + "WHERE d.numeroDemande != :numeroDemande "
            + "AND d.deleted = false")
    Page<Demande> findAll(String numeroDemande, Pageable pageable);

    @Query("SELECT d FROM Demande d, TypeDemande t "
            + "WHERE t.id = :idTypedemande AND d.typeDemande.id = t.id "
            + "AND YEAR(d.periodeDebut) = :annee AND d.statusDemande = :status AND d.positionDemande = :position ")
    Page<Demande> findCAByAnneeAndSGValidated(Integer annee, Long idTypedemande, EStatusDemande status, EPositionDemande position, Pageable pageable);
    // Page<Demande> findAllByAgentMatriculeAndAgentStructureId(String matricule, Long structureId, Pageable pageable);

    Page<Demande> findAllByAgentMatriculeAndTypeDemandeCode(String matricule, String codeTypeDmd, Pageable pageable);

    List<Demande> findAllByAgentAndTypeDemandeCode(Agent agent, String codeTypeDmd);

    Page<Demande> findAllByTypeDemandeCode(String codeTypeDmd, Pageable pageable);

    /*  @Query("SELECT COUNT(d) FROM Demande d JOIN d.agent a Join a.structure s"
    		+ " WHERE s.sigle =:SIGLE")
	Long countStructureDemande(@Param("SIGLE") String sigle);*/
}
