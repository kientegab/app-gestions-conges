package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Visa;
import bf.mfptps.appgestionsconges.repositories.VisaRepository;
import bf.mfptps.appgestionsconges.service.VisaService;
import bf.mfptps.appgestionsconges.service.dto.VisaDTO;
import bf.mfptps.appgestionsconges.service.mapper.VisaMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */

@Service
@Transactional
public class VisaServiceImpl implements VisaService {

    private final VisaRepository visaRepository;

    private final VisaMapper visaMapper;

    public VisaServiceImpl(VisaRepository visaRepository, VisaMapper visaMapper) {
        this.visaRepository = visaRepository;
        this.visaMapper = visaMapper;
    }

    @Override
    public VisaDTO create(VisaDTO visaDTO) {
        Visa visa = visaMapper.toEntity(visaDTO);
        visa = visaRepository.save(visa);
        return visaMapper.toDto(visa);
    }

    @Override
    public VisaDTO update(VisaDTO visaDTO) {
        Visa visa = visaMapper.toEntity(visaDTO);
        visa = visaRepository.save(visa);
        return visaMapper.toDto(visa);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VisaDTO> findOne(Long id) {
        return visaRepository.findById(id).map(visaMapper::toDto);
    }

    @Override
    public Visa findVisaById(Long id) {
        return visaRepository.findVisaById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VisaDTO> findAll(Pageable pageable) {
        return visaRepository.findAll(pageable).map(visaMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        visaRepository.deleteById(id);
    }
}
