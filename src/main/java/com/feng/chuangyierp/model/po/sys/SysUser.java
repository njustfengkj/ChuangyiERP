package com.feng.chuangyierp.model.po.sys;


public class SysUser {

  private long id;
  private String name;
  private String nc;
  private String password;
  private String jyw;
  private long roleid;


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNc() {
    return nc;
  }

  public void setNc(String nc) {
    this.nc = nc;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getJyw() {
    return jyw;
  }

  public void setJyw(String jyw) {
    this.jyw = jyw;
  }

  public long getRoleid() {
    return roleid;
  }

  public void setRoleid(long roleid) {
    this.roleid = roleid;
  }
}
