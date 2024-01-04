package com.vms.backend.pojos;

public class LoginRequest {

  private String email;
  private String passw;

  public String getPassw() {
    return passw;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassw(String passw) {
    this.passw = passw;
  }
}
