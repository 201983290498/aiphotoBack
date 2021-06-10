package com.example.demo;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
  public static void main(String[] args) {
    Date data = new Date();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
    System.out.println(simpleDateFormat.format(data));
  }
}
