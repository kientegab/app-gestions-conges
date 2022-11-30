/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import lombok.Builder;

/**
 *
 * @author HEBIE
 */
@Builder
public class ActivateCompteResponse {

    private boolean activate;

    public boolean isActivate() {
        return activate;
    }

    public void setActivate(boolean activate) {
        this.activate = activate;
    }

    public ActivateCompteResponse() {
    }

    public ActivateCompteResponse(boolean activate) {
        this.activate = activate;
    }

}
