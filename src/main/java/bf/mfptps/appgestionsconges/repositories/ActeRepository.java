package bf.mfptps.appgestionsconges.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.service.dto.ReportCongeDTO;
import bf.mfptps.appgestionsconges.service.dto.StatistiqueObject;

public interface ActeRepository extends JpaRepository<Acte, Long>, JpaSpecificationExecutor<Acte> {

	Optional<Acte> findByReference(String reference);
	
	@Query(
			value="select a.reference,a.annee from acte a join demande d on a.id = d.acte_id  join type_demande typed  on d.type_demande_id = typed.id join agent g on d.agent_id=g.id where g.matricule=? and typed.code=? and a.annee BETWEEN (select CAST(EXTRACT('year'FRom CURRENT_DATE)-3 as text)) and (select CAST(EXTRACT('year'FRom CURRENT_DATE) as text))",
			nativeQuery = true
			)
	Optional<List<Object>> ListOfReferenceByAgentMatricule(String matricule,String type_demande);
	
	
	@Query(
			value = "select sum(d.duree_absence) as valeur,typed.libelle as key from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join type_demande typed on d.type_demande_id = typed.id join agent_structure agsctru  on agsctru.agent_id = d.agent_id join structure struct ON struct.id = agsctru.structure_id where a.annee=? and struct.libelle=? GROUP BY typed.libelle",
			nativeQuery = true
			)
	Optional<List<StatistiqueObject>> totalOfTypedDemandeInYearByStructure(String year,String structure);
	
	
	
	@Query(value = "select typed.libelle as typedemande,sum(d.duree_absence) as valeur  from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join type_demande typed on d.type_demande_id = typed.id where a.annee=? and g.matricule=?  GROUP BY typed.libelle",nativeQuery = true)
	Optional<Object> totalOfAbsenceByTypeAndMAtriculeAndYear(String year,String matricule,String type_demande);
	
	
	@Query(
			value = "select sum(d.duree_absence)  from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join type_demande typed on d.type_demande_id = typed.id where   g.matricule=? and typed.code=? and a.annee=(select CAST(EXTRACT('year'FRom CURRENT_DATE) as text))",
			nativeQuery = true
			)
	Optional<Object> totalOfAbsenceByTypeAndMAtricule(String matricule,String type_demande);
	
	@Query(value="select new bf.mfptps.appgestionsconges.service.dto.( ag.nom as nom,ag.prenom as prenom,td.libelle as typeconge, ag.matricule as matricule ,ag.qualite as qualite,s.libelle as structureliblle,s.sigle as siglestructure,d.periode_debut as dateDebut, d.periode_fin as dateFin   from acte a join demande d on a.id =d.acte_id join agent ag on ag.id =d.agent_id join agent_structure astr on ag.id =astr.agent_id join \"structure\" s on s.id =astr.structure_id  join type_demande td on td.id =d.type_demande_id \r\n"
			+ "where a.reference ='2022_0004'",nativeQuery = true)
	Optional<Object>  getMetadata(String refConge);
	
	
}
