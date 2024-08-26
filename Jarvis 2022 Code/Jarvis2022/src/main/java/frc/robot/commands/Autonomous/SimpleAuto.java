// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.IntakeShooter.IntakeCargo;
import frc.robot.commands.IntakeShooter.ReleaseIntake;
import frc.robot.commands.IntakeShooter.ShootCargoRPM;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeShooter;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class SimpleAuto extends SequentialCommandGroup {
  /** Creates a new SimpleAuto. */
  public SimpleAuto(Climber m_climber, IntakeShooter m_intakeShooter, Drivetrain m_drivetrain) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ParallelRaceGroup(
        new WaitCommand(2.5),
        new ShootCargoRPM(Constants.LOW_SHOT_RPM, m_intakeShooter)
      ),

      new DriveStraightMM(m_drivetrain, -2),

      new ParallelCommandGroup(
        new ReleaseIntake(m_climber),
        new RotateAngleMM(m_drivetrain, 180)
      ),

      new ParallelRaceGroup(
        new DriveStraightMM(m_drivetrain, 4),
        new IntakeCargo(m_intakeShooter)
      ),

      new RotateAngleMM(m_drivetrain, 180),

      new DriveStraightMM(m_drivetrain, 6),

      new ParallelRaceGroup(
        new WaitCommand(2.5),
        new ShootCargoRPM(Constants.LOW_SHOT_RPM, m_intakeShooter)
      )
      
    );
  }
}
