package com.example.vector.game;

public record GameTitle(String title) {

  public String getNormalizedTitle() {
    return title.toLowerCase().replace(" ", "_");
  }

}
