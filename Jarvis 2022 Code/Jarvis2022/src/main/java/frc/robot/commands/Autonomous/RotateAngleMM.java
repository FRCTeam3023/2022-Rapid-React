// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

public class RotateAngleMM extends CommandBase {
  /** Creates a new RotateAngleMM. */
  Drivetrain m_drivetrain;
  double degrees;
  double sensorUnitTarget;
  public RotateAngleMM(Drivetrain m_drivetrain, double degrees) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_drivetrain = m_drivetrain;
    addRequirements(m_drivetrain);
    this.degrees = degrees;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.zeroSensors();
    m_drivetrain.setLeftInvert(true);
    m_drivetrain.rawDrive(0, 0);
    m_drivetrain.setTurnMotionSpeeds();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    sensorUnitTarget = degrees * (Constants.kEncoderUnitsPerRotation/360);

    m_drivetrain.straightDrive(sensorUnitTarget);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drivetrain.advDrive(0, 0);
    m_drivetrain.setLeftInvert(false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return 

    Math.abs(m_drivetrain.getAverageDistance()) - 50 <= Math.abs(sensorUnitTarget) 
    && 
    Math.abs(sensorUnitTarget) <= Math.abs(m_drivetrain.getAverageDistance()) + 50;

  }
}
