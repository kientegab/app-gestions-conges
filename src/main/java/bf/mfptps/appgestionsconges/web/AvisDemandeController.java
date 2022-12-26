package bf.mfptps.appgestionsconges.web;


import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import bf.mfptps.appgestionsconges.entities.Demande;
import bf.mfptps.appgestionsconges.service.AvisService;
import bf.mfptps.appgestionsconges.service.dto.ValideDemandeDto;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("api")
public class AvisDemandeController {
	
	private AvisService avisService;
	
	private org.slf4j.Logger log=LoggerFactory.getLogger(AvisDemandeController.class);
	
	
	public AvisDemandeController(AvisService avisService) {
		
		this.avisService = avisService;
	}




	@PostMapping("/avis-demande")
	public ResponseEntity<Demande>saveOrUpdateAvis(@RequestBody ValideDemandeDto valideDemandeDto)
	{
		log.info("Validation de la demande {}",valideDemandeDto);
		
		return ResponseEntity.ok(avisService.valideAvis(valideDemandeDto));
	}

}
