package bf.mfptps.appgestionsconges.service.impl;

import bf.mfptps.appgestionsconges.entities.AmpliationTypeDemande;
import bf.mfptps.appgestionsconges.entities.ArticleTypeDemande;
import bf.mfptps.appgestionsconges.repositories.AmpliationTypeDReposittory;
import bf.mfptps.appgestionsconges.service.AmpliationTypeDemandeService;
import bf.mfptps.appgestionsconges.service.dto.AmpliationTypeDemandeDTO;
import bf.mfptps.appgestionsconges.service.mapper.AmpliationTypeDemandeMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 *
 * @author ZINA
 */
@Service
@Transactional
public class AmpliationTypeDServiceImpl implements AmpliationTypeDemandeService {

    private final AmpliationTypeDReposittory ampliationTypeDReposittory ;
    private final AmpliationTypeDemandeMapper ampliationTypeDemandeMapper ;

    public AmpliationTypeDServiceImpl(AmpliationTypeDReposittory ampliationTypeDReposittory, AmpliationTypeDemandeMapper ampliationTypeDemandeMapper) {
        this.ampliationTypeDReposittory = ampliationTypeDReposittory;
        this.ampliationTypeDemandeMapper = ampliationTypeDemandeMapper;
    }

    @Override
    public AmpliationTypeDemandeDTO create(AmpliationTypeDemandeDTO ampliationTypeDemandeDTO) {

        AmpliationTypeDemande ampliationTypeDemande = ampliationTypeDemandeMapper.toEntity(ampliationTypeDemandeDTO);
        ampliationTypeDemande = ampliationTypeDReposittory.save(ampliationTypeDemande);
        return ampliationTypeDemandeMapper.toDto(ampliationTypeDemande);

    }

    @Override
    public AmpliationTypeDemandeDTO update(AmpliationTypeDemandeDTO ampliationTypeDemandeDTO) {

        AmpliationTypeDemande ampliationTypeDemande = ampliationTypeDemandeMapper.toEntity(ampliationTypeDemandeDTO);
        ampliationTypeDemande = ampliationTypeDReposittory.save(ampliationTypeDemande);
        return ampliationTypeDemandeMapper.toDto(ampliationTypeDemande);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AmpliationTypeDemandeDTO> findOne(Long id) {
        return ampliationTypeDReposittory.findById(id).map(ampliationTypeDemandeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AmpliationTypeDemandeDTO> findAll(Pageable pageable) {

        return ampliationTypeDReposittory.findAll(pageable).map(ampliationTypeDemandeMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        ampliationTypeDReposittory.deleteById(id);
    }
}
