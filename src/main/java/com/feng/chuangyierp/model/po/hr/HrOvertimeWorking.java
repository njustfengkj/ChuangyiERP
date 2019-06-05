package com.feng.chuangyierp.model.po.hr;


public class HrOvertimeWorking {

  private long gh;
  private String sbjbkssj;
  private String sbjbjssj;
  private double sbjbsc;
  private String sjjbkssj;
  private String sjjbjssj;
  private double sjjbsc;


  public long getGh() {
    return gh;
  }

  public void setGh(long gh) {
    this.gh = gh;
  }


  public String getSbjbkssj() {
    return sbjbkssj.substring(0,19);
  }

  public void setSbjbkssj(String sbjbkssj) {
    this.sbjbkssj = sbjbkssj;
  }


  public String getSbjbjssj() {
    return sbjbjssj.substring(0,19);
  }

  public void setSbjbjssj(String sbjbjssj) {
    this.sbjbjssj = sbjbjssj;
  }


  public double getSbjbsc() {
    return sbjbsc;
  }

  public void setSbjbsc(double sbjbsc) {
    this.sbjbsc = sbjbsc;
  }


  public String getSjjbkssj() {
    return sjjbkssj;
  }

  public void setSjjbkssj(String sjjbkssj) {
    this.sjjbkssj = sjjbkssj;
  }


  public String getSjjbjssj() {
    return sjjbjssj;
  }

  public void setSjjbjssj(String sjjbjssj) {
    this.sjjbjssj = sjjbjssj;
  }


  public double getSjjbsc() {
    return sjjbsc;
  }

  public void setSjjbsc(double sjjbsc) {
    this.sjjbsc = sjjbsc;
  }

}
