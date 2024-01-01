package com.vms.backend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.util.Date;

@Table(name = "person")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String fullname;

  private String email;

  private String passw;

  private Boolean isBoss;

  @Column(name = "created_at") // Specify column name for clarity
  private Date createdAt; // Use consistent naming conventions

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFullname() {
    return fullname;
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassw() {
    return passw;
  }

  public void setPassw(String passw) {
    this.passw = passw;
  }

  public Boolean getIsBoss() {
    return isBoss;
  }

  public void setIsBoss(Boolean isBoss) {
    this.isBoss = isBoss;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }
  /////////////////////////////////

}
