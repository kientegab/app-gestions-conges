package bf.mfptps.appgestionsconges.service.dto;

import java.time.Instant;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import bf.mfptps.appgestionsconges.config.Constants;

/**
 * A DTO representing a agent, with his profiles.
 */
public class AgentDTO {

    private Long id;

    @NotBlank
    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String matricule;

    @Pattern(regexp = Constants.LOGIN_REGEX)
    @Size(min = 1, max = 50)
    private String matriculeResp;

    @Size(max = 50)
    private String nom;

    @Size(max = 50)
    private String prenom;

    private String telephone;

    @Email
    @Size(min = 5, max = 254)
    private String email;

    private boolean actif = false;

    private Long structureId;

    private String createdBy;

    private Instant createdDate;

    private String lastModifiedBy;

    private Instant lastModifiedDate;

    private Set<PrivilegeDTO> privileges;

    private Set<ProfileDTO> profiles;

    public AgentDTO() {
    }

  /*  public AgentDTO(Agent agent) {
        this.id = agent.getId();
        this.matricule = agent.getMatricule();
        this.matriculeResp = agent.getMatriculeResp();
        this.nom = agent.getNom();
        this.prenom = agent.getPrenom();
        this.telephone = agent.getTelephone();
        this.email = agent.getEmail();
        this.actif = agent.isActif();
        this.createdBy = agent.getCreatedBy();
        this.createdDate = agent.getCreatedDate();
        this.lastModifiedBy = agent.getLastModifiedBy();
        this.lastModifiedDate = agent.getLastModifiedDate();
        Set<Privilege> actions = new HashSet<>();
        Set<String> prof = new HashSet<>();
        agent.getProfiles().stream().forEach(r -> {
            prof.add(r.getName());
            actions.addAll(r.getPrivileges());
        });
        this.profiles = prof;
        this.privileges = actions.stream()
                .map(Privilege::getName)
                .collect(Collectors.toSet());

//        if(null != agent.getStructure() && null != agent.getStructure().getId()) {
//            this.structureId = agent.getStructure().getId();
//        }
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Set<PrivilegeDTO> getPrivileges() {
        return privileges;
    }

    public void setPrivileges(Set<PrivilegeDTO> privileges) {
        this.privileges = privileges;
    }

    public Set<ProfileDTO> getProfiles() {
        return profiles;
    }

    public void setProfiles(Set<ProfileDTO> profiles) {
        this.profiles = profiles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getStructureId() {
        return structureId;
    }

    public void setStructureId(Long structureId) {
        this.structureId = structureId;
    }

    public String getMatriculeResp() {
        return matriculeResp;
    }

    public void setMatriculeResp(String matriculeResp) {
        this.matriculeResp = matriculeResp;
    }

    @Override
    public String toString() {
        return "AgentDTO{"
                + "matricule='" + matricule + '\''
                + "matriculeResp='" + matriculeResp + '\''
                + ", nom='" + nom + '\''
                + ", prenom='" + prenom + '\''
                + ", email='" + email + '\''
                + ", actif=" + actif
                + ", createdBy=" + createdBy
                + ", createdDate=" + createdDate
                + ", lastModifiedBy='" + lastModifiedBy + '\''
                + ", lastModifiedDate=" + lastModifiedDate
                + ", profiles=" + profiles
                + ", privileges=" + privileges
                + "}";
    }
}
