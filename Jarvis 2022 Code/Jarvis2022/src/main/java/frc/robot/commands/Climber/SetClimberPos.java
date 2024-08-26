// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class SetClimberPos extends CommandBase {
  private final Climber m_climber;
  private double extendPos;
  private double rotatePos;
  private boolean isClimbing;
  /** Creates a new SetElevatorPos. */
  public SetClimberPos(double extendPos, double rotatePos, boolean isClimbing, Climber m_climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.extendPos = extendPos;
    this.rotatePos = rotatePos;
    this.isClimbing = isClimbing;


    this.m_climber = m_climber;
    addRequirements(m_climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    // targetPos = (-RobotContainer.getInstance().getRightY()) * (Constants.CLIMBER_MAX_EXTENSION);
    // double rotatePos = (RobotContainer.getInstance().getLeftY()) * 8000;
    m_climber.climberSetpoint(extendPos, isClimbing);
    m_climber.rotateSetpoint(rotatePos, isClimbing);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_climber.setCLimberSpeed(0);
    m_climber.setRotateSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(Math.abs(extendPos - m_climber.getClimberPos()) < 150 && Math.abs( rotatePos - m_climber.getRotatePos()) < 150){
      return true;

    }
    else {
      return false;
    }
  }
}
