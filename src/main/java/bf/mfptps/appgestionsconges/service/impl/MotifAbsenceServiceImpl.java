package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.MotifAbsence;
import bf.mfptps.appgestionsconges.repositories.MotifAbsenceRepository;
import bf.mfptps.appgestionsconges.service.MotifAbsenceService;
import bf.mfptps.appgestionsconges.service.dto.MotifAbsenceDTO;
import bf.mfptps.appgestionsconges.service.mapper.MotifAbsenceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */

@Service
@Transactional
public class MotifAbsenceServiceImpl implements MotifAbsenceService {

    private final MotifAbsenceRepository motifAbsenceRepository;

    private final MotifAbsenceMapper motifAbsenceMapper;

    public MotifAbsenceServiceImpl(MotifAbsenceRepository motifAbsenceRepository, MotifAbsenceMapper motifAbsenceMapper) {
        this.motifAbsenceRepository = motifAbsenceRepository;
        this.motifAbsenceMapper = motifAbsenceMapper;
    }

    @Override
    public MotifAbsenceDTO create(MotifAbsenceDTO motifAbsenceDTO) {
        MotifAbsence motifAbsence = motifAbsenceMapper.toEntity(motifAbsenceDTO);
        motifAbsence = motifAbsenceRepository.save(motifAbsence);
        return motifAbsenceMapper.toDto(motifAbsence);
    }

    @Override
    public MotifAbsenceDTO update(MotifAbsenceDTO motifAbsenceDTO) {
        MotifAbsence motifAbsence = motifAbsenceMapper.toEntity(motifAbsenceDTO);
        motifAbsence = motifAbsenceRepository.save(motifAbsence);
        return motifAbsenceMapper.toDto(motifAbsence);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MotifAbsenceDTO> findOne(Long id) {
        return motifAbsenceRepository.findById(id).map(motifAbsenceMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MotifAbsenceDTO> findAll(Pageable pageable) {
        return motifAbsenceRepository.findAll(pageable).map(motifAbsenceMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        motifAbsenceRepository.deleteById(id);
    }
}
