package com.vesterli.hrdemo.deptemp.view.beans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.input.RichInputText;

public class DepartmentBean {
  ADFLogger logger = ADFLogger.createADFLogger(DepartmentBean.class);
  
  private RichInputText dname;

  public void setDname(RichInputText dname) {
    this.dname = dname;
  }

  public RichInputText getDname() {
    return dname;
  }

  public DepartmentBean() {
  }

  public String ShowMessage() {
    FacesContext fctx = FacesContext.getCurrentInstance();
    FacesMessage fm1 = new FacesMessage("General message");
    fm1.setSeverity(FacesMessage.SEVERITY_WARN);
    fctx.addMessage(null, fm1);
    return null;
  }

  public String ShowAnother() {
    FacesContext fctx = FacesContext.getCurrentInstance();
    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,
        "Summary", "This is the detailed message");
    String dnameItem = getDname().getClientId(fctx);
    logger.finer("Dname field is " + dnameItem);
    fctx.addMessage(dnameItem, fm);
    return null;
  }
  
  public void doStuff() {
    logger.fine("Doing stuff");
  }
}
