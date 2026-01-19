// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.LEDPattern;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.util.Color;
import pabeles.concurrency.ConcurrencyOps.Reset;

public class Robot extends TimedRobot {
  private final AddressableLED led;
  private final AddressableLEDBuffer ledBuffer;
  private int hue = 0;
  private int ledCount = 0;
  private boolean turningOff = false;
  private int redCount = 0;
  private int greenCount = 0;
  private int blueCount = 0;
  private int countYellow = 0;
  private LEDPattern red = LEDPattern.solid(Color.kRed);
  private LEDPattern green = LEDPattern.solid(Color.kGreen);
  private LEDPattern blue = LEDPattern.solid(Color.kBlue);
  private LEDPattern yellow = LEDPattern.solid(Color.kYellow);

  
  public Robot() {
   
    led = new AddressableLED(0);
    ledBuffer = new AddressableLEDBuffer(60);
    led.setLength(ledBuffer.getLength());
    led.setData(ledBuffer);
    led.start();
  }

  @Override
  public void robotPeriodic() {
  if(!turningOff){
    ++ledCount;
    if(ledCount >= ledBuffer.getLength()) {
      turningOff = true;
    }
  } else {
    --ledCount;
    if(ledCount <= 0) {
      turningOff = false;
    }
  }
  for(int i = 0; i < ledBuffer.getLength(); i++) {
     if(ledCount <= 15) {
      ledBuffer.setRGB(i, 255, 0, 0);
     } else if(ledCount > 15 && ledCount <= 30){
      ledBuffer.setRGB(i, 0, 255, 0);
     } else if(ledCount > 30 && ledCount <= 45) {
      ledBuffer.setRGB(i, 0, 0, 255);
     } else {
       int gradientHue = (hue +(i * 180 / ledBuffer.getLength())) % 180;
       ledBuffer.setHSV(i, gradientHue,255, 128);
     }
   }
   hue = (hue + 1) % 180;
   led.setData(ledBuffer);
  }

  //  if(!turningOff) {
  //   ++ledCount;
  //    if(ledCount >= ledBuffer.getLength()) {
  //      turningOff = true;
  //    }
  //  } else {
  //    --ledCount;
  //    if(ledCount <= 0) {
  //     turningOff = false;
  //    }
  //  }

  //  for(int i = 0; i < ledBuffer.getLength(); i++) {
  //     if(i < ledCount) {
  //       int gradientBuffer = (hue +(i * 180 / ledBuffer.getLength())) % 180;
  //       ledBuffer.setHSV(i, gradientBuffer, 255, 128);
       
  //     } else {
  //       ledBuffer.setRGB(i, 0, 0 ,0);
  //     }
  //   }
  //   hue = (hue + 3) % 180;
  //   led.setData(ledBuffer);
  
}

