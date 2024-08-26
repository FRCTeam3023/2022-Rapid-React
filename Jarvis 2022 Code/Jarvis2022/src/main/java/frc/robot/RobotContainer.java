

package frc.robot;

import frc.robot.commands.JoystickDrive;
import frc.robot.commands.ZeroEncoders;
import frc.robot.commands.Autonomous.CenterLeftBallAuto;
import frc.robot.commands.Autonomous.CenterRightBallAuto;
import frc.robot.commands.Autonomous.DriveStraightMM;
import frc.robot.commands.Autonomous.RotateAngleMM;
import frc.robot.commands.Autonomous.SideBallAuto;
import frc.robot.commands.Climber.ClimberDown;
import frc.robot.commands.Climber.ClimberUp;
import frc.robot.commands.Climber.ManualClimbing;
import frc.robot.commands.Climber.SetClimberPos;
import frc.robot.commands.Climber.Stages.Stage1;
import frc.robot.commands.IntakeShooter.IntakeCargo;
import frc.robot.commands.IntakeShooter.ShootCargo;
import frc.robot.commands.IntakeShooter.reverseIntake;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;



/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private static RobotContainer m_robotContainer = new RobotContainer();

  // The robot's subsystems
  public final Climber m_climber = new Climber();
  public final IntakeShooter m_intakeShooter = new IntakeShooter();
  public final Drivetrain m_drivetrain = new Drivetrain();
  public final LEDsubsystem m_ledSubsystem = new LEDsubsystem();
  public final Eyes m_eyes = new Eyes();

  // Joysticks
  private final Joystick rightJoystick = new Joystick(1);
  private final Joystick leftJoystick = new Joystick(0);


    JoystickButton fastDriveButton = new JoystickButton(rightJoystick, 2);

    JoystickButton manualClimbButton = new JoystickButton(rightJoystick, 3);

    JoystickButton shootButton = new JoystickButton(rightJoystick, 1);
    JoystickButton shootToggleButton = new JoystickButton(rightJoystick, 5);

    JoystickButton intakeButton = new JoystickButton(leftJoystick, 1);

    JoystickButton zeroButton = new JoystickButton(leftJoystick, 6);

    JoystickButton straightDriveButton = new JoystickButton(leftJoystick, 11);
    JoystickButton angleDriveButton = new JoystickButton(leftJoystick, 12);

    JoystickButton reverseIntakeButton = new JoystickButton(leftJoystick, 4);

    JoystickButton zeroClimberPosButton = new JoystickButton(rightJoystick, 8);

    JoystickButton unboundedClimbDownButton = new JoystickButton(rightJoystick, 12);
    JoystickButton unboundedClimbUpButton = new JoystickButton(rightJoystick, 10);

    JoystickButton stageOneClimbButton = new JoystickButton(rightJoystick, 4);
    //JoystickButton stageTwoClimbButton = new JoystickButton(rightJoystick, 6);
    //JoystickButton stageThreeClimbButton = new JoystickButton(rightJoystick, 5);



  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
  * The container for the robot.  Contains subsystems, OI devices, and commands.
  */
  private RobotContainer() {

    //SmartDashboard inputs
    SmartDashboard.putNumber("Intake Speed", Constants.INTAKE_SPEED);
    SmartDashboard.putNumber("Conveyor Speed", Constants.CONVEYOR_SPEED);
    SmartDashboard.putNumber("Low Shot RPM", Constants.LOW_SHOT_RPM);
    SmartDashboard.putNumber("High Shot RPM", Constants.HIGH_SHOT_RPM);

    SmartDashboard.putNumber("Drive Multiplier", Constants.DRIVE_MULTIPLIER);
    SmartDashboard.putNumber("Fast Drive Multiplier", Constants.FAST_DRIVE_MULTIPLIER);

    SmartDashboard.putBoolean("Climber Pos Data", Constants.CLIMBER_POS_DATA);
    SmartDashboard.putBoolean("Drivetrain Debug", Constants.DRIVETRAIN_DEBUG);
    SmartDashboard.putBoolean("Climber Debug", Constants.CLIMBER_DEBUG);
    SmartDashboard.putBoolean("Rotate Debug", Constants.ROTATE_DEBUG);
    SmartDashboard.putBoolean("Shooter Debug", Constants.SHOOTER_DEBUG);

    SmartDashboard.putString("Drive Type", Constants.DRIVE_TYPE);
    SmartDashboard.putNumber("Turn Multiplier", 0.45);

    SmartDashboard.putNumber("Auto Delay", 0);

    SmartDashboard.putNumber("S P", Drivetrain.kGains_Distanc.kP);
    SmartDashboard.putNumber("S I", Drivetrain.kGains_Distanc.kI);
    SmartDashboard.putNumber("S D", Drivetrain.kGains_Distanc.kD);
    SmartDashboard.putNumber("S F", Drivetrain.kGains_Distanc.kF);

    SmartDashboard.putNumber("T P", Drivetrain.kGains_Turns.kP);
    SmartDashboard.putNumber("T I", Drivetrain.kGains_Turns.kI);
    SmartDashboard.putNumber("T D", Drivetrain.kGains_Turns.kD);
    SmartDashboard.putNumber("T F", Drivetrain.kGains_Turns.kF);


       
    // Configure the button bindings
    configureButtonBindings();

    // Configure default commands
        
    m_drivetrain.setDefaultCommand(new JoystickDrive( m_drivetrain, SmartDashboard.getNumber("Drive Multiplier", Constants.DRIVE_MULTIPLIER), leftJoystick, rightJoystick ) );



    // Configure autonomous sendable chooser
    m_chooser.setDefaultOption("Left Center", new CenterLeftBallAuto(m_drivetrain, m_climber, m_intakeShooter));
    m_chooser.addOption("Right Center", new CenterRightBallAuto(m_drivetrain, m_climber, m_intakeShooter));
    m_chooser.addOption("Side Ball", new SideBallAuto(m_drivetrain, m_climber, m_intakeShooter));

    SmartDashboard.putData("Auto Mode", m_chooser);
  }

  public static RobotContainer getInstance() {
    return m_robotContainer;
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // Create some buttons
    fastDriveButton.whenHeld(new JoystickDrive(m_drivetrain, SmartDashboard.getNumber("Fast Drive Multiplier", Constants.FAST_DRIVE_MULTIPLIER), leftJoystick, rightJoystick));

    manualClimbButton.whenHeld(new ManualClimbing(m_climber, m_drivetrain));

    shootButton.whenHeld(new ShootCargo(m_intakeShooter, shootToggleButton));

    intakeButton.whenHeld(new IntakeCargo(m_intakeShooter));

    reverseIntakeButton.whenHeld(new reverseIntake(m_intakeShooter));

    zeroButton.whenHeld(new ZeroEncoders(m_climber));

    zeroClimberPosButton.whenHeld(new SetClimberPos(0, 0, false, m_climber));

    straightDriveButton.whenHeld(new DriveStraightMM(m_drivetrain, 3));
    angleDriveButton.whenHeld(new RotateAngleMM(m_drivetrain, 180));

    unboundedClimbDownButton.whenHeld(new ClimberDown(m_climber));
    unboundedClimbUpButton.whenHeld(new ClimberUp(m_climber));

    stageOneClimbButton.whenHeld(new Stage1(m_climber));
    //stageTwoClimbButton.whenHeld(new Stage2(m_climber));
    //stageThreeClimbButton.whenHeld(new Stage3(m_climber));
    
  }

public Joystick getLeftJoystick() {
        return leftJoystick;
    }

public Joystick getRightJoystick() {
        return rightJoystick;
    }

public double getLeftY(){
  return leftJoystick.getY();
}

public double getRightY(){
  return rightJoystick.getY();
}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
  */
  public Command getAutonomousCommand() {
    // The selected command will be run in autonomous
    return m_chooser.getSelected();
  }

  

}

