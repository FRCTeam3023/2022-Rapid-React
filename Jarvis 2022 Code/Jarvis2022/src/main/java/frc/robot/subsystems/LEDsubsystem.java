// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class LEDsubsystem extends SubsystemBase {


  AddressableLED leftLED = new AddressableLED(1);
  // AddressableLED rightLED = new AddressableLED(2);

  AddressableLEDBuffer leftBuffer = new AddressableLEDBuffer(15);
  // AddressableLEDBuffer rightBuffer = new AddressableLEDBuffer(60);

  /** Creates a new LEDsubsystem. */
  public LEDsubsystem() {
    leftLED.setLength(leftBuffer.getLength());
    // rightLED.setLength(rightBuffer.getLength());

    // rightLED.start();
    
    leftLED.setData(leftBuffer);
    // rightLED.setData(rightBuffer);


    leftLED.start();



    // setColor(Constants.LEDDefaultColor.r, Constants.LEDDefaultColor.g, Constants.LEDDefaultColor.b);
    
   
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // setLeftColor(Constants.LEDDefaultColor.r, Constants.LEDDefaultColor.g, Constants.LEDDefaultColor.b);
    

  }


  public void setLeftColor(int r, int g, int b){

    for (var i = 0 ; i < leftBuffer.getLength(); i++){
      leftBuffer.setRGB(i, r, g, b);
    }

    leftLED.setData(leftBuffer);

  }

  // public void setRightColor(int r, int g, int b){
  //   for(var i = 0; i < leftBuffer.getLength(); i++){
  //     rightBuffer.setRGB(i, r, g, b);
  // }

  //   rightLED.setData(rightBuffer);

  // }

  // public void setColor(int r, int g, int b){
  //   setLeftColor(r, g, b);
  //   setRightColor(r, g, b);
  // }


}
