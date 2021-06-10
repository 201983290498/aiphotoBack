package com.example.demo.AI.entity;

public class DeleteFace {
  private  String requestId;
  private String state;

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public DeleteFace() {
  }

  public DeleteFace(String requestId, String state) {
    this.requestId = requestId;
    this.state = state;
  }
}
