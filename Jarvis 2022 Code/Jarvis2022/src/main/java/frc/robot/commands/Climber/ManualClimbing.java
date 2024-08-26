// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;

public class ManualClimbing extends CommandBase {

  private final Climber m_climber;
  private final Drivetrain m_drivetrain;
  /** Creates a new ManualClimbing. */
  public ManualClimbing(Climber subsystem, Drivetrain subsystem2) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_climber = subsystem;
    m_drivetrain = subsystem2;

    addRequirements(m_climber);
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drivetrain.advDrive(0, 0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_climber.setCLimberSpeed(-RobotContainer.getInstance().getRightY());
    m_climber.setRotateSpeed(RobotContainer.getInstance().getLeftY());
    
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
    return false;
  }
}
