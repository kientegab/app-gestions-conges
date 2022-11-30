package bf.mfptps.appgestionsconges.service;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.dto.ValideDemandeDto;



public interface AvisService {
	
	public Demande valideAvis(ValideDemandeDto valideDemandeDto );
	
}
