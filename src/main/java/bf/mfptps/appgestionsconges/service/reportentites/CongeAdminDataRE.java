/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.reportentites;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CongeAdminDataRE implements Serializable {

    private String ministereLibelle;

    private String structureParentLibelle;

    private String structureLibelle;

    private InputStream logo;

    private String titre;

//    private String signataire;
//    private String article1;
    private List<VisaRE> visaREs;

//    private List<ArticleRE> articleSuivantREs;
//
//    private List<AgentRE> agentsConcerneREs;
}
