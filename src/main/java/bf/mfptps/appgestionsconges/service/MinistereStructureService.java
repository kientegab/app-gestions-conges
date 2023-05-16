package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.service.dto.MinistereStructureDTO;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MinistereStructureService {

    MinistereStructure create(MinistereStructureDTO ministereSDTO);

    MinistereStructure update(MinistereStructure ministereS);

    Optional<MinistereStructure> get(Long id);

    Optional<MinistereStructure> getByStructure(Long id);

    Page<MinistereStructure> findAll(Pageable pageable);

    void delete(Long code);

    Page<StructureDTO> findAllBeta(Pageable pageable);

    Page<StructureDTO> findAllStructureByMinistere(long ministereId, Pageable pageable);
}
