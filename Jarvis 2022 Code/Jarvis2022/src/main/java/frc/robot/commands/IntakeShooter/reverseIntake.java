// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeShooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeShooter;

public class reverseIntake extends CommandBase {
  IntakeShooter m_intakeShooter;
  /** Creates a new reverseIntake. */
  public reverseIntake(IntakeShooter m_intakeShooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.m_intakeShooter = m_intakeShooter;
    addRequirements(m_intakeShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeShooter.shootRPM(-1000);
    m_intakeShooter.intakeInner(-1);
    m_intakeShooter.intakeOuter(-.5);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_intakeShooter.setShooterOutput(0);
    m_intakeShooter.intakeInner(0);
    m_intakeShooter.intakeOuter(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
