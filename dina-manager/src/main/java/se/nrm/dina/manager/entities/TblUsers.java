package se.nrm.dina.manager.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "tbl_users")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TblUsers.findAll", query = "SELECT t FROM TblUsers t"),   
  @NamedQuery(name = "TblUsers.findByUsername", query = "SELECT t FROM TblUsers t WHERE t.username = :username"),
  @NamedQuery(name = "TblUsers.findByPassword", query = "SELECT t FROM TblUsers t WHERE t.password = :password"),
  @NamedQuery(name = "TblUsers.validateUsername", query = "SELECT Count(t) FROM TblUsers t WHERE t.username = :username"),
  @NamedQuery(name = "TblUsers.validateEmail", query = "SELECT Count(t) FROM TblUsers t WHERE t.email = :email"),
  @NamedQuery(name = "TblUsers.findByEmail", query = "SELECT t FROM TblUsers t WHERE t.email = :email")})
public class TblUsers implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "username")
  private String username;

  @Size(max = 255)
  @Column(name = "password")
  private String password;

  // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
  @Size(max = 255)
  @Column(name = "email")
  private String email;

  @Column(name = "onvacation")
  private Boolean onvacation;

  @OneToMany(cascade = CascadeType.ALL, mappedBy = "username", fetch = FetchType.EAGER, orphanRemoval = true)
  private List<TblGroups> tblGroupsList;

  public TblUsers() {
  }

  public TblUsers(String username) {
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Boolean getOnvacation() {
    return onvacation;
  }

  public void setOnvacation(Boolean onvacation) {
    this.onvacation = onvacation;
  }

  @XmlTransient
  public List<TblGroups> getTblGroupsList() {
    return tblGroupsList;
  }

  public void setTblGroupsList(List<TblGroups> tblGroupsList) {
    this.tblGroupsList = tblGroupsList;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (username != null ? username.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TblUsers)) {
      return false;
    }
    TblUsers other = (TblUsers) object;
    return (this.username != null || other.username == null) && (this.username == null || this.username.equals(other.username));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.manager.entities.TblUsers[ username=" + username + " email =" + email + "]";
  }

}
