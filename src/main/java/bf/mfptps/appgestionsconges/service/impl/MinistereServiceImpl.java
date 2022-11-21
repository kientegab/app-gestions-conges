package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.repositories.MinistereRepository;
import bf.mfptps.appgestionsconges.security.SecurityUtils;
import bf.mfptps.appgestionsconges.service.MinistereService;
import bf.mfptps.appgestionsconges.service.dto.MinistereDTO;
import bf.mfptps.appgestionsconges.service.mapper.MinistereMapper;
import bf.mfptps.appgestionsconges.utils.AppUtil;
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
public class MinistereServiceImpl implements MinistereService {

    private final MinistereRepository ministereRepository;
    private final MinistereMapper ministereMapper;

    public MinistereServiceImpl(MinistereRepository ministereRepository, MinistereMapper ministereMapper) {
        this.ministereRepository = ministereRepository;
        this.ministereMapper = ministereMapper;
    }

    @Override
    public Ministere create(MinistereDTO ministereDTO) {
        Ministere ministere = ministereMapper.toEntity(ministereDTO);
        return ministereRepository.save(ministere);
    }

    @Override
    public Ministere update(Ministere ministere) {
        return ministereRepository.save(ministere);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(String code) {
        return ministereRepository.findByCode(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Ministere> findAll(Pageable pageable) {
        if (SecurityUtils.isCurrentUserInRole(AppUtil.ADMIN)) {
            return ministereRepository.findAll(pageable);
        } else {
            //Ne pas retourner le MinistereTest au cas où le client n'est pas ADMIN
            return ministereRepository.findAll(AppUtil.BASIC_MINISTERE_CODE, pageable);
        }
    }

    @Override
    public void delete(Long code) {
        ministereRepository.deleteById(code);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Ministere> get(Long id) {
        return ministereRepository.findById(id);
    }

}
