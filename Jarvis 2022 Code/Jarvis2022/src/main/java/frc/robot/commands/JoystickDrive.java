
package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

/**
 *
 */
public class JoystickDrive extends CommandBase {

    private final Drivetrain m_drivetrain;
    private double multiplier;
    Joystick leftStick;
    Joystick rightStick;

    public JoystickDrive(Drivetrain subsystem, double driveMultiplier, Joystick leftStick, Joystick rightStick) {

        m_drivetrain = subsystem;
        addRequirements(m_drivetrain);
        multiplier = driveMultiplier;
        this.leftStick = leftStick;
        this.rightStick = rightStick;

    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        // Set the left and right speeds based on joystick input times input constant

        // String DriveType = SmartDashboard.getString("Drive Type", Constants.DRIVE_TYPE);

        // if (DriveType == "Tank Drive") {
        //     double leftSpeed = multiplier * leftStick.getY();
        //     double rightSpeed = multiplier * rightStick.getY();
        //     m_drivetrain.advDrive(leftSpeed, rightSpeed);
        // }
        // else if(DriveType == "Arcade Drive"){
        //     double forward = multiplier * rightStick.getY();
        //     double turn = 0.1 * rightStick.getTwist();
        //     m_drivetrain.arcadeDrive(forward, turn);
        // }

        double forward = multiplier * rightStick.getY();
            double turn = SmartDashboard.getNumber("Turn Multiplier", 0.5) * rightStick.getTwist();
            m_drivetrain.arcadeDrive(forward, turn);

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drivetrain.rawDrive(0, 0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean runsWhenDisabled() {
        return false;
    }
}
