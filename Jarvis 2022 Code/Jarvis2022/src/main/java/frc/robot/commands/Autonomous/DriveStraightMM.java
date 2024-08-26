// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class DriveStraightMM extends CommandBase {
  /** Creates a new DriveStraight. */
  Drivetrain m_drivetrain;
  double sensorDistance;
  double targetDistance;

  public DriveStraightMM(Drivetrain m_drivetrain, double targetDistance) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
    this.targetDistance = targetDistance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.zeroSensors();
    m_drivetrain.rawDrive(0, 0);
    m_drivetrain.setStraightMotionSpeeds();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensorDistance = targetDistance * (1/(6 * Math.PI)) * Constants.kSensorUnitsPerRotation * Constants.kDrivetrainGearing;
    m_drivetrain.straightDrive(sensorDistance);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.advDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return 

    Math.abs(m_drivetrain.getAverageDistance()) - 50 <= Math.abs(sensorDistance) 
    && 
    Math.abs(sensorDistance) <= Math.abs(m_drivetrain.getAverageDistance()) + 50;
  }
}
