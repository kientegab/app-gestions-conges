/*
 * To change this license header, choose License Headers in Project Properties @HEBIE
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.mfptps.appgestionsconges.entities;

import bf.mfptps.appgestionsconges.config.Constants;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

/**
 *
 * @author HEBIE
 */
@Entity
@Table(name = "corps")
@SQLDelete(sql = "UPDATE corps SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Corps extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 10)
    @Column(name = "code", length = 10)
    private String codeCorps;

    @Column(name = "libelle", nullable = false)
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
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Corps other = (Corps) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "Corps{" + "id=" + id + ", codeCorps=" + codeCorps + ", libelleCorps=" + libelleCorps + '}';
    }

}
