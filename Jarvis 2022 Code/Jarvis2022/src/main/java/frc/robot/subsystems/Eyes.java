// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Eyes extends SubsystemBase {
  Servo leftEye = new Servo(2);
  Servo rightEye = new Servo(3);

  double pos = 0;
  double direction = 1;
  /** Creates a new Eyes. */
  public Eyes() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    leftEye.set(0);
    rightEye.set(0);

  }
}
