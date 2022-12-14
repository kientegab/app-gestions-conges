package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.entities.Ministere;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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

    Page<Demande> findAllByUtilisateurMatriculeAndUtilisateurStructureId(String matricule, Long structureId, Pageable pageable);

    @Query("SELECT COUNT(d) FROM Demande d JOIN d.utilisateur u Join u.structure s"
    		+ " WHERE s.sigle =:SIGLE")
	Long countStructureDemande(@Param("SIGLE") String sigle);
}
