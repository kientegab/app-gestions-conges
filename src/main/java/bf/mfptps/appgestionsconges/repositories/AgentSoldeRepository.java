package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bf.mfptps.appgestionsconges.entities.AgentSolde;


public interface AgentSoldeRepository extends JpaRepository<AgentSolde, Long>, JpaSpecificationExecutor<AgentSolde> {
	
	@Query("SELECT s FROM AgentSolde s WHERE s.annee=:ANNEE and s.typeDemande=:TYPEDEMANDE and s.matricule=:MATRICULE and s.deleted=false")
	Optional<AgentSolde> findUserSoldeByYear(@Param("MATRICULE") String matricule, @Param("ANNEE") int annee, @Param("TYPEDEMANDE") String typeDemande);
	
	@Transactional
	@Query("UPDATE UtilisateurSolde "
			+ " SET soldeRestant=(soldeRestant - :NOMBREJOUR)"
			+ " WHERE id=:ID")
	AgentSolde updateUserSolde(@Param("ID") Long id, @Param("NOMBREJOUR") int nombreJour);
}
