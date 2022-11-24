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
public class ArticleDTO extends CommonEntity {

    private Long id;

    private String code;

    private String attributLibelle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAttributLibelle() {
        return attributLibelle;
    }

    public void setAttributLibelle(String attributLibelle) {
        this.attributLibelle = attributLibelle;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final ArticleDTO other = (ArticleDTO) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "ArticleDTO{" + "id=" + id + ", code=" + code + ", attributLibelle=" + attributLibelle + '}';
    }

}
