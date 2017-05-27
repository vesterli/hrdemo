package com.vesterli.hrdemo.master.view.beans;

import java.io.Serializable;

import oracle.adf.share.logging.ADFLogger;

public class UiStateBean implements Serializable {
  ADFLogger logger = ADFLogger.createADFLogger(UiStateBean.class);
  private String currentTF = "/WEB-INF/dept-emp-flow.xml#dept-emp-flow";

  public void setCurrentTF(String currentTF) {
    logger.fine("setting current TF '" + currentTF + "'");
    this.currentTF = currentTF;
  }

  public String getCurrentTF() {
    return currentTF;
  }
}
