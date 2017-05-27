package com.vesterli.hrdemo.master.view.beans;

import java.io.Serializable;

import oracle.adf.controller.TaskFlowId;
import oracle.adf.share.logging.ADFLogger;

public class PageSwitcherBean implements Serializable {
  private UiStateBean currentUiState;
  private String taskFlowId = "/WEB-INF/dept-emp-flow.xml#dept-emp-flow";
  private ADFLogger logger = ADFLogger.createADFLogger(PageSwitcherBean.class);

  public PageSwitcherBean() {
  }

  public TaskFlowId getDynamicTaskFlowId() {
    logger.fine("Returning task flow id '" + currentUiState.getCurrentTF() + "'");
    return TaskFlowId.parse(currentUiState.getCurrentTF());
  }

  public void setUiState(UiStateBean state) {
    currentUiState = state;
  }
}
