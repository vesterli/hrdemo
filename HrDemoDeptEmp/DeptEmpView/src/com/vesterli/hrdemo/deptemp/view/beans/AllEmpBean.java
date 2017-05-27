package com.vesterli.hrdemo.deptemp.view.beans;

import java.math.BigDecimal;

import oracle.adf.model.BindingContext;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

public class AllEmpBean {
  
  private RichInputText sal;
  private RichInputText raise;

  public void setSal(RichInputText sal) {
    this.sal = sal;
  }

  public RichInputText getSal() {
    return sal;
  }

  public void setRaise(RichInputText raise) {
    this.raise = raise;
  }

  public RichInputText getRaise() {
    return raise;
  }


  public String SuggestRaise() {
    String oldSalaryString = (String)sal.getValue();
    BigDecimal oldSal = new BigDecimal(oldSalaryString);
    BigDecimal suggestedRaise = oldSal.multiply(new BigDecimal(0.05));
    suggestedRaise = suggestedRaise.setScale(0, BigDecimal.ROUND_DOWN);
    raise.setValue(suggestedRaise);
    AdfFacesContext.getCurrentInstance().addPartialTarget(raise);
    return null;
  }

  public String GiveRaise() {
    BindingContainer bc = BindingContext.getCurrent().getCurrentBindingsEntry();
    return null;
  }

}
