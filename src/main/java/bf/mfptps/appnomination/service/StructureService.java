package bf.mfptps.appnomination.service;

import bf.mfptps.appnomination.entities.Structure;
import bf.mfptps.appnomination.service.dto.StructureDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StructureService {

    Structure create(StructureDTO structure);

    Structure update(Structure structure);

    Optional<Structure> get(long id);

    Page<Structure> findAll(Pageable pageable);

    void delete(Long code);

}
