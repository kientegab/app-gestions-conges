/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.config;

import bf.mfptps.appgestionsconges.entities.Ministere;
import bf.mfptps.appgestionsconges.entities.MinistereStructure;
import bf.mfptps.appgestionsconges.entities.Structure;
import bf.mfptps.appgestionsconges.entities.TypeStructure;
import bf.mfptps.appgestionsconges.repositories.MinistereRepository;
import bf.mfptps.appgestionsconges.repositories.MinistereStructureRepository;
import bf.mfptps.appgestionsconges.repositories.StructureRepository;
import bf.mfptps.appgestionsconges.repositories.TypeStructureRepository;
import bf.mfptps.appgestionsconges.service.AgentService;
import bf.mfptps.appgestionsconges.utils.AppUtil;
import bf.mfptps.appgestionsconges.web.exceptions.CustomException;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Configuration
public class AppDataInitializer {

    private final MinistereRepository ministereRepository;

    private final StructureRepository structureRepository;

    private final TypeStructureRepository typeStructureRepository;

    private final MinistereStructureRepository ministereStructureRepository;

    private final AgentService agentService;

    public AppDataInitializer(MinistereRepository ministereRepository,
            StructureRepository structureRepository,
            TypeStructureRepository typeStructureRepository,
            MinistereStructureRepository ministereStructureRepository,
            AgentService agentService) {
        this.ministereRepository = ministereRepository;
        this.structureRepository = structureRepository;
        this.typeStructureRepository = typeStructureRepository;
        this.ministereStructureRepository = ministereStructureRepository;
        this.agentService = agentService;
    }

    /**
     * Methode de creation de Ministere, Structure et MinistereStructure par
     * defaut.
     *
     * On relie egalement les Users ADMIN et USER a la Structure par defaut
     */
    @PostConstruct
    public void initialization() {
        if (ministereRepository.count() == 0) {
            try {
                Ministere ministere = new Ministere();
                TypeStructure typeStructure = new TypeStructure();
                Structure structure = new Structure();
                MinistereStructure ministereStructure = new MinistereStructure();

                ministere.setCode(AppUtil.BASIC_MINISTERE_CODE);
                ministere.setLibelle(AppUtil.BASIC_MINISTERE_LABEL);
                ministere.setSigle(AppUtil.BASIC_MINISTERE_SIGLE);
                ministereRepository.save(ministere);

                typeStructure.setLibelle(AppUtil.BASIC_TYPE_STRUCTURE_LIBELLE);
                typeStructure = typeStructureRepository.save(typeStructure);

                structure.setLibelle(AppUtil.BASIC_STRUCTURE_LABEL);
                structure.setSigle(AppUtil.BASIC_STRUCTURE_SIGLE);
                structure.setType(typeStructure);
                structure.setResponsable(AppUtil.BASIC_STRUCTURE_RESPONSABLE);
                structure.setTelephone(AppUtil.BASIC_STRUCTURE_TELEPHONE);
                structure.setEmailResp(AppUtil.BASIC_STRUCTURE_EMAIL);
                structure.setEmailStruct(AppUtil.BASIC_STRUCTURE_EMAIL);
                structureRepository.save(structure);

                ministereStructure.setMinistere(ministere);
                ministereStructure.setStructure(structure);
                ministereStructure.setDateDebut(new Date());
                ministereStructureRepository.save(ministereStructure);

                //Join system users to new basic structure
                agentService.affectationAgent("admin", structure.getId());
                agentService.affectationAgent("user", structure.getId());
            } catch (Exception e) {
                throw new CustomException("Une erreur s'est produite lors de l'initialisation des ministère et structure de base.");
            }
        }
    }
}
