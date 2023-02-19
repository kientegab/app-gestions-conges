package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import bf.mfptps.appgestionsconges.entities.TypeActe;

public interface TypeActeRepository extends JpaRepository<TypeActe, Long>, JpaSpecificationExecutor<TypeActe> {

	Optional<TypeActe> findByReference(String reference);
}
