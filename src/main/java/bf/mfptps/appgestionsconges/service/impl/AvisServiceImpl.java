package bf.mfptps.appgestionsconges.service.impl;



import bf.mfptps.appgestionsconges.entities.Avis;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.repositories.AvisRepository;
import bf.mfptps.appgestionsconges.service.AvisService;
import bf.mfptps.appgestionsconges.service.dto.AvisDTO;
import bf.mfptps.appgestionsconges.service.dto.ValideDemandeDto;
import bf.mfptps.appgestionsconges.service.mapper.AvisMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 * @author TEGUERA
 */

@Service
@Transactional
public class AvisServiceImpl implements AvisService {

    private final AvisRepository avisRepository;

    private final AvisMapper avisMapper;

    public AvisServiceImpl(AvisRepository avisRepository, AvisMapper avisMapper) {
        this.avisRepository = avisRepository;
        this.avisMapper = avisMapper;
    }
    
    @Override
    public AvisDTO create(AvisDTO avisDTO) {
        Avis avis = avisMapper.toEntity(avisDTO);
        avis = avisRepository.save(avis);
        return avisMapper.toDto(avis);
    }

    @Override
    public AvisDTO update(AvisDTO avisDTO) {
        Avis avis = avisMapper.toEntity(avisDTO);
        avis = avisRepository.save(avis);
        return avisMapper.toDto(avis);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AvisDTO> findOne(Long id) {
        return avisRepository.findById(id).map(avisMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AvisDTO> findAll(Pageable pageable) {
        return avisRepository.findAll(pageable).map(avisMapper::toDto);
    }

    @Override
    public void deleteOne(Long id) {
        avisRepository.deleteById(id);
    }

	@Override
	public Demande valideAvis(ValideDemandeDto valideDemandeDto) {
		// TODO Auto-generated method stub
		return null;
	}
    
    /*
    public Demande valideAvis(ValideDemandeDto valideDemandeDto) {
		// TODO Auto-generated method stub
		
		Avis avis=new Avis();
		avis.setAvisDG(valideDemandeDto.getAvisDG());
		avis.setAvisDRH(valideDemandeDto.getAvisDRH());
		avis.setAvisSG(valideDemandeDto.getAvisSG());
		avis.setAvisSH(valideDemandeDto.getAvisSH());
		
		//avis.setDemande(demandeRepository.findById(valideDemandeDto.getDemande_id()).orElse(null));
		return avisRepository.saveAndFlush(avis).getDemande();
	}
    
    */
	

}
