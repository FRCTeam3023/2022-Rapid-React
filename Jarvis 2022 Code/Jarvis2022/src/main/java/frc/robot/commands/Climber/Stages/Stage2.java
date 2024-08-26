// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.Climber.Stages;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Climber.SetClimberPos;
import frc.robot.subsystems.Climber;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class Stage2 extends SequentialCommandGroup {
  /** Creates a new Stage2. */
  public Stage2(Climber m_climber) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new SetClimberPos(Constants.CLIMBER_CLEAR_EXTENSION, Constants.CLIMBER_VERTICAL, false, m_climber),
      new SetClimberPos(200000, Constants.CLIMBER_VERTICAL, true, m_climber),
      new SetClimberPos(200000, Constants.CLIMBER_MIN_PIVOT, true, m_climber),
      new SetClimberPos(-30000, Constants.CLIMBER_MIN_PIVOT, true, m_climber),
      new SetClimberPos(-30000, -6150, true, m_climber),
      new SetClimberPos(103000, -5540, true, m_climber),
      new SetClimberPos(103000, Constants.CLIMBER_MAX_PIVOT, true, m_climber),
      new SetClimberPos(Constants.CLIMBER_MAX_EXTENSION, Constants.CLIMBER_MAX_PIVOT, true, m_climber)
    );
  }
}
