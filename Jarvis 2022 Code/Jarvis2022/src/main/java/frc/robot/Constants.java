// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package frc.robot;
/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {

    public static final class LEDDefaultColor{
        public static final int r = 3;
        public static final int g = 144;
        public static final int b = 252;
    }


    public static final String DRIVE_TYPE = "Arcade Drive"; // Tank Drive  or Arcade Drive
    

    
    public static final boolean SHOOTER_DEBUG = false; //shows shooter RPM
    public static final boolean CLIMBER_DEBUG = false; //Climber MM data
    public static final boolean ROTATE_DEBUG = false;  //Rotate MM data
    public static final boolean DRIVETRAIN_DEBUG = false; //Drivetrain MM data
    public static final boolean CLIMBER_POS_DATA = false; //Shows Climber position data, for climbing setpoints

    
    
    
    public static final double DRIVE_MULTIPLIER = 0.7; // max value of drive speed, take into account this is squared
    public static final double FAST_DRIVE_MULTIPLIER = 0.8;

    public static int kPIDLoopIdx = 0;
    public static int kTimeoutMs = 30;
    public static int kSlotIdx = 0;

    public static final double CLIMBER_SPEED = 1;
    public static final double ROTATE_SPEED = 0.3;
    

    public static final double LOW_SHOT_RPM = 2000;
    public static final double HIGH_SHOT_RPM = 2650;
    public static final double SHOOTER_ACCELERATION = 1;
    public static final double INTAKE_SPEED = 0.8;
    public static final double CONVEYOR_SPEED = 0.7;

    public static final double kNeutralDeadband = 0.001;
    public static final double kEncoderUnitsPerRotation = 109000 / 2;

    public static final double kSensorUnitsPerRotation = 2457;
    public static final double kDrivetrainGearing = 6;



    public static final double DRIVE_DEADBAND = 0.03;


    public static final int CLIMBER_MAX_PIVOT = 3800;
    public static final int CLIMBER_VERTICAL = -3260;
    public static final int CLIMBER_MIN_PIVOT = -7400;

    public static final int CLIMBER_MAX_EXTENSION = 484000;
    public static final int CLIMBER_CLEAR_EXTENSION = 372000;
    public static final double CLIMBER_CLEAR_ANGLE = 0;

    public static final double CLIMBER_HOVER_POWER = -0.07;
    public static final double ROTATE_HOVER_POWER = -0.1;











    
}

