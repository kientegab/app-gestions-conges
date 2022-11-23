/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.service.dto;

import bf.mfptps.appgestionsconges.entities.CommonEntity;
import java.util.Objects;

/**
 *
 * @author HEBIE
 */
public class CorpsDTO extends CommonEntity {

    private Long id;

    private String codeCorps;

    private String libelleCorps;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeCorps() {
        return codeCorps;
    }

    public void setCodeCorps(String codeCorps) {
        this.codeCorps = codeCorps;
    }

    public String getLibelleCorps() {
        return libelleCorps;
    }

    public void setLibelleCorps(String libelleCorps) {
        this.libelleCorps = libelleCorps;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorpsDTO other = (CorpsDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "CorpsDTO{" + "id=" + id + ", codeCorps=" + codeCorps + ", libelleCorps=" + libelleCorps + '}';
    }

}
