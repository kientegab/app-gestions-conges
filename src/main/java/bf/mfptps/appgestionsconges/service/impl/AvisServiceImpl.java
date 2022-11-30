package bf.mfptps.appgestionsconges.service.impl;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import bf.mfptps.appgestionsconges.entities.Avis;
import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.repositories.AvisRepository;
import bf.mfptps.appgestionsconges.repositories.DemandeRepository;
import bf.mfptps.appgestionsconges.service.AvisService;
import bf.mfptps.appgestionsconges.service.dto.ValideDemandeDto;


@Service
@Transactional
public class AvisServiceImpl implements AvisService{
	
	AvisRepository avisRepository;
	DemandeRepository demandeRepository;
	
	
	
	

	@Override
	public Demande valideAvis(ValideDemandeDto valideDemandeDto) {
		// TODO Auto-generated method stub
		
		Avis avis=new Avis();
		avis.setAvisDG(valideDemandeDto.getAvisDG());
		avis.setAvisDRH(valideDemandeDto.getAvisDRH());
		avis.setAvisSG(valideDemandeDto.getAvisSG());
		avis.setAvisSH(valideDemandeDto.getAvisSH());
		avis.setDemande(demandeRepository.findById(valideDemandeDto.getDemande_id()).orElse(null));
		return avisRepository.saveAndFlush(avis).getDemande();
	}
	
	
	
	
	



}
