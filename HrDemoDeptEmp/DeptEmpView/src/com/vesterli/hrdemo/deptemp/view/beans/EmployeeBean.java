package com.vesterli.hrdemo.deptemp.view.beans;

import java.math.BigDecimal;

import java.util.Iterator;
import java.util.List;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.binding.BindingContainer;

import oracle.jbo.Key;
import oracle.jbo.Row;
import oracle.jbo.RowSetIterator;

import org.apache.myfaces.trinidad.model.RowKeySet;


public class EmployeeBean {
  ADFLogger logger = ADFLogger.createADFLogger(EmployeeBean.class);

  private RichInputText sal;
  private RichInputText raise;
  private RichTable empTab;

  public void setEmpTab(RichTable empTab) {
    this.empTab = empTab;
  }

  public RichTable getEmpTab() {
    return empTab;
  }

  public EmployeeBean() {
  }

  /*
        public void SalaryValidator(FacesContext facesContext, UIComponent uIComponent, Object object)
            throws ValidatorException{
          if (object != null) {
            int sal = Integer.valueOf(object.toString());
              if((sal % 10) != 0) {
                throw new ValidatorException(
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Salary should be round number", null));
                }
            }

        }
    */
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

  public String MultiRaise() {
    logger.fine("Entering MultiRaise()");
    RowKeySet selectedEmps = getEmpTab().getSelectedRowKeys();
    Iterator selIter = selectedEmps.iterator();

    logger.finer("Get iterator for all VO records");
    BindingContainer bc = BindingContext.getCurrent().getCurrentBindingsEntry();
    DCBindingContainer dcb =(DCBindingContainer)bc;
    DCIteratorBinding empIter = dcb.findIteratorBinding("EmployeesInDeptVOIterator");
    RowSetIterator rsi = empIter.getRowSetIterator();

    logger.finer("Loop over selected employees, give raise");
    Row currEmp = null;
    String oldSalString;
    BigDecimal oldSal;
    BigDecimal newSal;
    while (selIter.hasNext()) {
      Key key = (Key)((List)selIter.next()).get(0);
      currEmp = rsi.getRow(key);
      logger.finest("Working on " + currEmp.getAttribute("EmployeeId"));
      oldSalString = (String)currEmp.getAttribute("SalaryString");
      oldSal = new BigDecimal(oldSalString);
      logger.finest("Old sal", oldSal);
      newSal = oldSal.multiply(new BigDecimal(1.05)).setScale(0, BigDecimal.ROUND_DOWN);
      currEmp.setAttribute("SalaryString", newSal.toString());
      logger.finest("New sal", currEmp.getAttribute("SalaryString"));
    }

    logger.finer("Done increasing salaries, refreshing UI");
    AdfFacesContext.getCurrentInstance().addPartialTarget(empTab);
    return null;
  }

  public String PrintNames() {
    RowKeySet selectedEmps = getEmpTab().getSelectedRowKeys();
    Iterator selIter = selectedEmps.iterator();
    BindingContainer bc = BindingContext.getCurrent().getCurrentBindingsEntry();
    DCBindingContainer dcb =(DCBindingContainer)bc;
    DCIteratorBinding empIter = dcb.findIteratorBinding("EmployeesInDeptVOIterator");
    RowSetIterator rsi = empIter.getRowSetIterator();
    Row curr = null;
    while (selIter.hasNext()) {
      Key key = (Key)((List)selIter.next()).get(0);
      curr = rsi.getRow(key);
      logger.finest("Selected: " + curr.getAttribute("LastName"));
    }
    return null;
  }
}
