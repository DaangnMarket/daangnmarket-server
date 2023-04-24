package com.clone.daangnmarketserver.user.presentation.dto;

public class UpdateRequest {

  private String name;

  protected UpdateRequest() {
  }

  public UpdateRequest(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
