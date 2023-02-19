package bf.mfptps.appgestionsconges.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import bf.mfptps.appgestionsconges.entities.Acte;

public interface ActeRepository extends JpaRepository<Acte, Long>, JpaSpecificationExecutor<Acte> {

	Optional<Acte> findByReference(String reference);
	
	@Query(
			value="select a.reference from acte a join demande d on a.id = d.acte_id  join motif_absence m  on d.motif_absence_id = m.id join agent g on d.agent_id=g.id where g.matricule=? and m.libelle=? and  a.annee BETWEEN (select CAST(EXTRACT('year'FRom CURRENT_DATE)-3 as text)) and (select CAST(EXTRACT('year'FRom CURRENT_DATE) as text))",
			nativeQuery = true
			)
	Optional<String> ListOfReferenceByAgentMatricule(String matricule,String motif_absence);
	
	
	@Query(
			value = "select sum(d.duree_absence) from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join motif_absence m on d.motif_absence_id = m.id where a.annee=? and g.matricule=? and m.libelle=?",
			nativeQuery = true
			)
	Optional<Integer> totalOfAbsenceInYear(String year, String matricule,String motif_absence);
	
	@Query(value = "select m.libelle,sum(d.duree_absence)  from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join motif_absence m on d.motif_absence_id = m.id where a.annee='' and g.matricule=? and m.libelle=? GROUP BY m.libelle",nativeQuery = true)
	Optional<Object> totalOfAbsenceByTypeAndMAtriculeAndYear(String year,String matricule);
	
	@Query(
			value = "select a.annee,sum(d.duree_absence)  from demande d join acte a on d.acte_id=a.id join agent g on d.agent_id = g.id join motif_absence m on d.motif_absence_id = m.id where   g.matricule='' and m.libelle='' GROUP BY a.annee",
			nativeQuery = true
			)
	Optional<Object> totalOfAbsenceByYeayeAndMAtriculeAndYear(String matricule,String motif);
	
	
}
