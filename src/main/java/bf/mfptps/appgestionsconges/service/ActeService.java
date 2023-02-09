/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service;

import java.io.File;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import antlr.collections.List;
import bf.mfptps.appgestionsconges.entities.Acte;
import bf.mfptps.appgestionsconges.service.dto.ActeDTO;
import bf.mfptps.appgestionsconges.service.dto.ResponseDto;

/**
 *
 * @author HEBIE
 */
public interface ActeService {

    ActeDTO create(ActeDTO acteDTO);

    ActeDTO update(ActeDTO acteDTO);

    Optional<Acte> findOne(Long id);

    Page<Acte> findAll(Pageable pageable);

    void delete(Long id);
    
    File generateActe(String referenceActe);
    
    /**
     * Liste des références des décisions de congé des trois dernières années qui prend en paramètre un matricule
     * @param matricule
     * @param motif_absence
     * @return ResponseDto
     */
    ResponseDto  ListOfReferenceByAgentMatriculeService(String matricule, String motif_absence);
    
    /**
     * Nombre de jour d’autorisation contracté qui prend en paramètre un matricule et une année.
     * @param year
     * @param matricule
     * @param motif_absence
     * @return ResponseDto
     */
    ResponseDto totalOfAbsenceInYearByMAtricule(String year,String matricule,String motif_absence);
    
    /**
     * Nombre de jour de congé par type qui prend en paramètre un matricule et une année
     * @param year
     * @param matricule
     * @return ResponseDto
     */
    ResponseDto totalOfAbsenceByTypeAndMAtriculeAndYear(String year, String matricule);
     
    ResponseDto totalOfAbsenceByYeayeAndMAtriculeAndYear(String matricule,String motif);
     
    
}
