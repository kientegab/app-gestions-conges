package bf.mfptps.appgestionsconges.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bf.mfptps.appgestionsconges.service.ActeService;
import bf.mfptps.appgestionsconges.service.dto.ResponseDto;

@RestController
@RequestMapping(path="/api/statistque")
public class StatistiqueController {
	
	ActeService acteService;
	
	

	public StatistiqueController(ActeService acteService) {
		
		this.acteService = acteService;
	}



	/**
	 * Liste des références des décisions de congé des trois dernières années qui prend en paramètre un matricule.
	 * @param matricule
	 * @param absenceMotif
	 * @return ResponseDto
	 * ok
	 */
	@GetMapping("/liste-of-ref-by-matricule")
	@ResponseBody
	public ResponseEntity<ResponseDto> listeOfReferenceByMatriculeAndAbsenceMotif(@RequestParam String matricule,@RequestParam String code_demande)
	{

		return ResponseEntity.ok().body(acteService.ListOfReferenceByAgentMatriculeService(matricule, code_demande)); 
	}
	
	/**
	 * 
	 * @param year
	 * @param matricule
	 * @param motif
	 * @return ResponseDto
	 */
	@GetMapping("/total-of-absence-by-matriculeandyear")
	@ResponseBody
	public ResponseEntity<ResponseDto>totalOfAbsenceByMatriculeAndYear(@RequestParam String year,@RequestParam String matricule,@RequestParam String type_demane)
	{
			return ResponseEntity.ok().body(acteService.totalOfAbsenceInYearByMAtricule(matricule));
	}
	
	
	/**
	 * 
	 * @param year
	 * @param matricule
	 * @return ResponseDto
	 * ok
	 */
	@GetMapping("/total-of-absence-by-type-matriculeandyear")
	@ResponseBody
	public ResponseEntity<ResponseDto>totalOfAbsenceByTypeMatriculeAndYear(@RequestParam String year,@RequestParam String matricule)
	{
			return ResponseEntity.ok().body(acteService.totalOfAbsenceByTypeAndMAtriculeAndYear(year, matricule));
	}
	
	/**
	 * 
	 * @param matricule
	 * @param motif
	 * @return ResponseDto
	 * ok
	 */ 
	
	@GetMapping("/total-of-absence-by-year-matricule")
	@ResponseBody
	public ResponseEntity<ResponseDto>totalOfAbsenceByYearMatriculeAndYear(@RequestParam String year,@RequestParam String matricule)
	{
			return ResponseEntity.ok().body(acteService.totalOfAbsenceByTypeAndMAtriculeAndYear(year,matricule));
	}
	
	
}
