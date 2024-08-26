

package frc.robot.commands.IntakeShooter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants;
import frc.robot.subsystems.IntakeShooter;



/**
 *
 */
public class ShootCargo extends CommandBase {

        private final IntakeShooter m_intake;
        private JoystickButton toggleButton;
        private double shotRPM;


    public ShootCargo(IntakeShooter subsystem, JoystickButton toggleButton) {
        this.toggleButton = toggleButton;
        m_intake = subsystem;
        addRequirements(m_intake);
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        if(toggleButton.get()){
            shotRPM = SmartDashboard.getNumber("Low Shot RPM", Constants.LOW_SHOT_RPM);
        }
        else{
            shotRPM = SmartDashboard.getNumber("High Shot RPM", Constants.HIGH_SHOT_RPM);
        }

        m_intake.shootRPM(shotRPM);
        if(m_intake.getShootSpeed() > (.95 * shotRPM)){
            m_intake.intakeInner(SmartDashboard.getNumber("Conveyor Speed", Constants.CONVEYOR_SPEED));
            m_intake.intakeOuter(SmartDashboard.getNumber("Intake Speed", Constants.INTAKE_SPEED)/2);
        }
        

        

    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_intake.setShooterOutput(0);
        m_intake.intakeInner(0);
        m_intake.intakeOuter(0);
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
