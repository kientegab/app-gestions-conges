package bf.mfptps.appgestionsconges.repositories;

import bf.mfptps.appgestionsconges.entities.TypeDemande;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface TypeDemandeRepository extends JpaRepository<TypeDemande, Long>, JpaSpecificationExecutor<TypeDemande> {
	
}
