// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot {
  private final AddressableLED led;
  private final AddressableLEDBuffer ledBuffer;
  private int hue = 0;
  private int ledCount = 0;
  private int countOff = 60;
  private boolean turningOff = false;

  
  public Robot() {
   
    led = new AddressableLED(0);
    ledBuffer = new AddressableLEDBuffer(60);
    led.setLength(ledBuffer.getLength());
    led.setData(ledBuffer);
    led.start();
  }

  @Override
  public void robotPeriodic() {
  //   for(int i = 0; i <= ledBuffer.getLength(); i++) {
  //     int gradientHue = (hue + (i * 180 / ledBuffer.getLength())) % 180;
  //     ledBuffer.setHSV(i, gradientHue, 255, 128);
  //   }

  //   hue = (hue + 3) % 180;
  //   led.setData(ledBuffer);
   for(int i = 0; i < ledBuffer.getLength(); i++) {
      if(i < ledCount) {
        int gradientBuffer = (hue +(i * 180 / ledBuffer.getLength())) % 180;
        ledBuffer.setHSV(i, gradientBuffer, 255, 128);
      } else {
        ledBuffer.setRGB(i, 0, 0 ,0);
      }
      // if(i == countOff) {
      //   --countOff;
      //   ledBuffer.setRGB(i,0,0,0);
      // } else {
      //   int gradientBuffer = (hue +(i * 180 / ledBuffer.getLength())) % 180;
      //   ledBuffer.setHSV(i, gradientBuffer, 255, 128);
      // }

    }
      if(!turningOff) {
        ++ledCount;
        if(ledCount >= ledBuffer.getLength()) {
          turningOff = true;
        }
      }else {
        if(ledCount < ledBuffer.getLength()) {
          turningOff = false;
        }
      }
      // if(!turningOff) {
      //   --countOff;
      //   if(countOff <= ledBuffer.getLength()){
      //     turningOff = true;
      //   }
      // } else {

      // }if(countOff >= ledBuffer.getLength()){
      //     turningOff = false;
      //   }
    hue = (hue + 3) % 180;
    led.setData(ledBuffer);
  }
}

