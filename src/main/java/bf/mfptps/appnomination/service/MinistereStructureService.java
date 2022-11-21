package bf.mfptps.appnomination.service;

import bf.mfptps.appnomination.entities.MinistereStructure;
import bf.mfptps.appnomination.service.dto.MinistereStructureDTO;
import bf.mfptps.appnomination.service.dto.StructureDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MinistereStructureService {

    MinistereStructure create(MinistereStructureDTO ministereSDTO);

    MinistereStructure update(MinistereStructure ministereS);

    Optional<MinistereStructure> get(Long id);

    Page<MinistereStructure> findAll(Pageable pageable);

    void delete(Long code);

    Page<StructureDTO> findAllBeta(Pageable pageable);

    Page<StructureDTO> findAllStructureByMinistere(long ministereId, Pageable pageable);
}
