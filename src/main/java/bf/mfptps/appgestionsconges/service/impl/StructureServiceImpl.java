package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.repositories.MinistereRepository;
import bf.mfptps.appgestionsconges.repositories.MinistereStructureRepository;
import bf.mfptps.appgestionsconges.repositories.StructureRepository;
import bf.mfptps.appgestionsconges.service.StructureService;
import bf.mfptps.appgestionsconges.service.dto.StructureDTO;
import bf.mfptps.appgestionsconges.service.mapper.StructureMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Service
@Transactional
public class StructureServiceImpl implements StructureService {

    private final StructureRepository structureRepository;
    private final MinistereStructureRepository ministereStructureRepository;
    private final MinistereRepository ministereRepository;
    private final StructureMapper structureMapper;

    public StructureServiceImpl(StructureRepository structureRepository,
            MinistereStructureRepository ministereStructureRepository,
            MinistereRepository ministereRepository,
            StructureMapper structureMapper) {
        this.structureRepository = structureRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.ministereRepository = ministereRepository;
        this.structureMapper = structureMapper;
    }

    /**
     *
     * @param structureDTO
     * @return
     */
    @Override
    public Structure create(StructureDTO structureDTO) {
        Structure structure = structureMapper.toEntity(structureDTO);
        Ministere ministere = null;

        if (structureDTO.getMinistere() == null) {
            ministere = ministereRepository.findByCode(AppUtil.BASIC_MINISTERE_CODE).get();
            structureDTO.setMinistere(ministere);
        } else {
            ministere = ministereRepository
                    .findById(structureDTO.getMinistere().getId())
                    .orElseThrow(() -> new CustomException("Le Ministère d'id " + structureDTO.getMinistere().getId() + " est inexistant."));
        }

        if (structureDTO.getParent() != null) {
            MinistereStructure ministereStructureChecked = ministereStructureRepository
                    .findByStructureIdAndStatutIsTrue(structureDTO.getParent().getId())
                    .orElseThrow(() -> new CustomException("La structure parente n'est pas rattachée à un ministère."));
            if (ministere.getId() != ministereStructureChecked.getMinistere().getId()) {
                throw new CustomException("Veuillez selectionner le ministère approprié (" + structureDTO.getParent().getSigle() + " => " + ministereStructureChecked.getMinistere().getSigle() + ").");
            }
        }
        Structure structureSaved = structureRepository.save(structure);
        MinistereStructure ministereStructure = new MinistereStructure();
        ministereStructure.setMinistere(ministere);
        ministereStructure.setStructure(structureSaved);
        ministereStructure.setDateDebut(new Date());
        ministereStructureRepository.save(ministereStructure);
        return structureSaved;
    }

    @Override
    public Structure update(Structure structure) {
        if (structure.getParent() != null) {
            ministereStructureRepository
                    .findByStructureIdAndStatutIsTrue(structure.getParent().getId())
                    .orElseThrow(() -> new CustomException("La structure parente n'est pas rattachée à un ministère."));
        }
        return structureRepository.save(structure);
    }

    @Override
    public Optional<Structure> get(long id) {
        return structureRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Structure> findAll(Pageable pageable) {
        return structureRepository.findAll(pageable);

    }

    @Override
    public void delete(Long id) {
        structureRepository.deleteById(id);
    }

}
