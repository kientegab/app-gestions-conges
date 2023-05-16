/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.web.vm;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
@Getter
@Setter
@ToString
public class DecisionCAVM {

    private Long structureId;

    private Long concerneStructureId;

    private Integer annee;
}
