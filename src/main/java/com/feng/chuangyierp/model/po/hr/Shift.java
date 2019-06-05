package com.feng.chuangyierp.model.po.hr;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Shift {

  private long id;
  private String bcmc;
  private Timestamp sbkssj;
  private Timestamp sbjssj;
  private Timestamp ksxxsj;
  private Timestamp jsxxsj;
  private long sfcr;
  private DateFormat dateFormat=new SimpleDateFormat("HH:mm:ss");


  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getBcmc() {
    return bcmc;
  }

  public void setBcmc(String bcmc) {
    this.bcmc = bcmc;
  }


  public String getSbkssj() {
      return sbkssj!=null?dateFormat.format(sbkssj):"";
  }

  public void setSbkssj(Timestamp sbkssj) {
    this.sbkssj = sbkssj;
  }


  public String getSbjssj() {
      return sbjssj!=null?dateFormat.format(sbjssj):"";
  }

  public void setSbjssj(Timestamp sbjssj) {
    this.sbjssj = sbjssj;
  }


  public String getKsxxsj() {
      return ksxxsj!=null?dateFormat.format(ksxxsj):"";
  }

  public void setKsxxsj(Timestamp ksxxsj) {
    this.ksxxsj = ksxxsj;
  }


  public String getJsxxsj() {
      return jsxxsj!=null?dateFormat.format(jsxxsj):"";
  }

  public void setJsxxsj(Timestamp jsxxsj) {
    this.jsxxsj = jsxxsj;
  }


  public long getSfcr() {
    return sfcr;
  }

  public void setSfcr(long sfcr) {
    this.sfcr = sfcr;
  }

}
