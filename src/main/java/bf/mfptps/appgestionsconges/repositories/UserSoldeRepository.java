package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bf.mfptps.appgestionsconges.entities.UtilisateurSolde;

public interface UserSoldeRepository extends JpaRepository<UtilisateurSolde, Long>, JpaSpecificationExecutor<UtilisateurSolde> {
	
	@Query("SELECT s FROM UtilisateurSolde s WHERE s.annee=:ANNEE and s.typeDemande=:TYPEDEMANDE and s.matricule=:MATRICULE")
	Optional<UtilisateurSolde> findUserSoldeByYear(@Param("MATRICULE") String matricule, @Param("ANNEE") int annee, @Param("TYPEDEMANDE") String typeDemande);
	
	@Transactional
	@Query("UPDATE UtilisateurSolde "
			+ " SET soldeRestant=(soldeRestant - :NOMBREJOUR)"
			+ " WHERE id=:ID")
	UtilisateurSolde updateUserSolde(@Param("ID") Long id, @Param("NOMBREJOUR") int nombreJour);
}
