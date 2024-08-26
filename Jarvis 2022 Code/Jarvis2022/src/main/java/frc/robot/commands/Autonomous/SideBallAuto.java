// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Autonomous;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
public class SideBallAuto extends SequentialCommandGroup {
  /** Creates a new SideBallAuto. */
  public SideBallAuto(Drivetrain m_drivetrain, Climber m_climber, IntakeShooter m_intakeShooter) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    double waitSeconds = SmartDashboard.getNumber("Auto Delay", 0);
    addCommands(
      new ReleaseIntake(m_climber),
      new RotateAngleMM(m_drivetrain, -22.3),

      new ParallelRaceGroup(
        new DriveStraightMM(m_drivetrain, 45),
        new IntakeCargo(m_intakeShooter)
      ),

      new RotateAngleMM(m_drivetrain, 167),

      new WaitCommand(waitSeconds),

      new DriveStraightMM(m_drivetrain, 86.5),
      new RotateAngleMM(m_drivetrain, 57.6),
      new DriveStraightMM(m_drivetrain, 12),
      
      new ParallelRaceGroup(
        new ShootCargoRPM(Constants.HIGH_SHOT_RPM, m_intakeShooter),
        new WaitCommand(4)
      )
    );
  }
}
