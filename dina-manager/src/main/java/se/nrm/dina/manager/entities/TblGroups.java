package se.nrm.dina.manager.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author idali
 */
@Entity
@Table(name = "tbl_groups")
@XmlRootElement
@NamedQueries({
  @NamedQuery(name = "TblGroups.findAll", query = "SELECT t FROM TblGroups t"),
  @NamedQuery(name = "TblGroups.findByGroupname", query = "SELECT t FROM TblGroups t WHERE t.groupname = :groupname"),
  @NamedQuery(name = "TblGroups.findNonInventoryGroups", query = "SELECT t FROM TblGroups t WHERE t.groupname NOT IN (:groupnames)"), 
  @NamedQuery(name = "TblGroups.findById", query = "SELECT t FROM TblGroups t WHERE t.id = :id")})
public class TblGroups implements Serializable {

  private static final long serialVersionUID = 1L;
  @Basic(optional = false)
  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "groupname")
  private String groupname;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
  private Integer id;

  @JoinColumn(name = "username", referencedColumnName = "username")
  @ManyToOne(optional = false, cascade = CascadeType.ALL)
  private TblUsers username;

  public TblGroups() {
  }

  public TblGroups(Integer id) {
    this.id = id;
  }

  public TblGroups(Integer id, String groupname) {
    this.id = id;
    this.groupname = groupname;
  }

  public String getGroupname() {
    return groupname;
  }

  public void setGroupname(String groupname) {
    this.groupname = groupname;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public TblUsers getUsername() {
    return username;
  }

  public void setUsername(TblUsers username) {
    this.username = username;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof TblGroups)) {
      return false;
    }
    TblGroups other = (TblGroups) object;
    return (this.id != null || other.id == null) && (this.id == null || this.id.equals(other.id));
  }

  @Override
  public String toString() {
    return "se.nrm.dina.manager.entities.TblGroups[ id=" + id + " groupname= " + groupname + " ]";
  }

}
