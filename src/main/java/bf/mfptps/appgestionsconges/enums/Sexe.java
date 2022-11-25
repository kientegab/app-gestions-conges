/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.enums;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public enum Sexe {
    MASCULIN("MASCULIN"), FEMININ("FEMININ");

    String label;

    Sexe(String _label) {
        this.label = _label;
    }

    public String getLabel() {
        return label;
    }
}
