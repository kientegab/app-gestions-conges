package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import bf.mfptps.appgestionsconges.entities.Avis;

public interface AvisRepository extends JpaRepository<Avis, Long>, JpaSpecificationExecutor<Avis> {

	@Query("SELECT a FROM Avis a JOIN a.demande d WHERE d.numeroDemande=:NUMERODEMANDE and a.deleted=false;")
	Optional<Avis> findAvisByNumeroDemande(@Param("NUMERODEMANDE")String numeroDemande);
}
