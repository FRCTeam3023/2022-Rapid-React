// RobotBuilder Version: 4.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

// ROBOTBUILDER TYPE: Subsystem.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Gains;



/**
 *
 */
public class IntakeShooter extends SubsystemBase {
    //shooter
    CANSparkMax _shooter = new CANSparkMax(10, MotorType.kBrushless);
    SparkMaxPIDController _shootPID;
    RelativeEncoder _shootEncoder;
    Gains shooterGains = new Gains(0.0004, 0, 0, .000185, 0, 1);

    TalonSRX _intakeArmMotor = new TalonSRX(11);

    VictorSPX _innerIntakeMotor  = new VictorSPX(12);

    DigitalInput ballLimit = new DigitalInput(0);


    public IntakeShooter() {
        //set up shooter
        _shooter.restoreFactoryDefaults();
        _shooter.setInverted(true);
        _shootPID = _shooter.getPIDController();
        _shootEncoder = _shooter.getEncoder();
        //set PID for shooter motor
        _shootPID.setP(shooterGains.kP);
        _shootPID.setI(shooterGains.kI);
        _shootPID.setD(shooterGains.kD);
        _shootPID.setIZone(shooterGains.kIzone);
        _shootPID.setFF(shooterGains.kF);
        _shootPID.setOutputRange(-shooterGains.kPeakOutput, shooterGains.kPeakOutput);
        
        _shooter.setClosedLoopRampRate(Constants.SHOOTER_ACCELERATION);

        _innerIntakeMotor.setNeutralMode(NeutralMode.Brake);

        if(SmartDashboard.getBoolean("Shooter Debug", Constants.SHOOTER_DEBUG)){
            SmartDashboard.putNumber("Shooter kp", shooterGains.kP);
            SmartDashboard.putNumber("Shooter kf", shooterGains.kF);
        }


    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        if(SmartDashboard.getBoolean("Shooter Debug", Constants.SHOOTER_DEBUG)){

            _shootPID.setP(SmartDashboard.getNumber("Shooter kp", shooterGains.kP));
            _shootPID.setFF(SmartDashboard.getNumber("Shooter kf", shooterGains.kF));
        }

    }

    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    //closed loop RPM for shooter
    public void shootRPM(double speedRPM){
        _shootPID.setReference(speedRPM, CANSparkMax.ControlType.kVelocity);

        if(SmartDashboard.getBoolean("Shooter Debug", Constants.SHOOTER_DEBUG)){
            System.out.println(_shootEncoder.getVelocity());
        }
    }

    public void setShooterOutput(double speed){
        _shooter.set(speed);
    }


    //individually controll the speed of the outer or inner intake belts
    public void intakeInner(double speed){
        _innerIntakeMotor.set(VictorSPXControlMode.PercentOutput, speed);
        
    }

    public void intakeOuter (double speed){
        _intakeArmMotor.set(TalonSRXControlMode.PercentOutput, speed);
    }

    public int getShootSpeed(){
        return (int) _shootEncoder.getVelocity();
    }
    //get ball limit switch data
    public boolean getBallLimit(){
        return ballLimit.get();
    }

}
