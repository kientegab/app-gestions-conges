package bf.mfptps.appgestionsconges.entities;


import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Cache;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

/**
 *
 * @author TEGUERA <teguera.zakaria@gmail.com>
 */
@Entity
@Table(name = "visa")
@SQLDelete(sql = "UPDATE visa SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
@FilterDef(
        name = "deletedFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedFilter",
        condition = "deleted = :isDeleted"
)
//@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Visa extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;


   // @NotNull
   // @Size(min = 1, max = 50)
    @Column(name = "code", length = 254)
    private String code;

    //@Size(min = 5, max = 254)
    @Column(name = "libelle", length = 254)
    private String libelle;
/*
    @OneToMany(mappedBy = "visa", cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<TypeVisa> typeVisas = new HashSet<>();
*/
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

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }
/*
    public Set<TypeVisa> getTypeVisas() {
        return typeVisas;
    }

    public void setTypeVisas(Set<TypeVisa> typeVisas) {
        this.typeVisas = typeVisas;
    }

    public void addTypeVisa(TypeVisa typeVisa) {
        this.typeVisas.add(typeVisa);
    }
*/
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Visa visa = (Visa) o;
        return id.equals(visa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
/*
    @Override
    public String toString() {
        return "Visa{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", libelle='" + libelle + '\'' +
                ", typeVisas=" + typeVisas +
                '}';
    }
    */
}
