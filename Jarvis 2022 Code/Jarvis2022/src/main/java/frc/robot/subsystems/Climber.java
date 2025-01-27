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


import frc.robot.Constants;
import frc.robot.Gains;
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

/**
 *
 */
public class Climber extends SubsystemBase {

    WPI_TalonFX _climber = new WPI_TalonFX(5);

    TalonSRX _rotate = new TalonSRX(6);

    StringBuilder _sb = new StringBuilder();
    
    int _smoothing = 0;

    Gains kGains_Extd = new Gains(0.70, 0.0, 0.0, 0, 0, 1.0);

    Gains kGains_Rotate = new Gains(1.0, 0, 0, 0, 0, 0);

    DigitalInput climbLowLimit = new DigitalInput(2);

    double hoverPower = 0;
    double rotateHoverPower = 0;

    boolean thisPress = false;
    boolean previousPress = false;
    double lowerLimitSetpoint;




    /**
    *
    */
    public Climber() {
        _climber.configFactoryDefault();
        _climber.setNeutralMode(NeutralMode.Brake);
        _climber.set(TalonFXControlMode.PercentOutput, 0);

        /* Configure Sensor Source for Pirmary PID */
		_climber.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);

        /* set deadband to super small 0.001 (0.1 %).
            The default deadband is 0.04 (4 %) */
        _climber.configNeutralDeadband(0.001, Constants.kTimeoutMs);

        //_climber.setSensorPhase(false);
		_climber.setInverted(false);

        /* Set relevant frame periods to be at least as fast as periodic rate */
		_climber.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_climber.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);
		
		/* Set the peak and nominal outputs */
		_climber.configNominalOutputForward(0, Constants.kTimeoutMs);
		_climber.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_climber.configPeakOutputForward(1, Constants.kTimeoutMs);
		_climber.configPeakOutputReverse(-1, Constants.kTimeoutMs);
		
		/* Set Motion Magic gains in slot0 - see documentation */
		_climber.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_climber.config_kF(Constants.kSlotIdx, kGains_Extd.kF, Constants.kTimeoutMs);
		_climber.config_kP(Constants.kSlotIdx, kGains_Extd.kP, Constants.kTimeoutMs);
		_climber.config_kI(Constants.kSlotIdx, kGains_Extd.kI, Constants.kTimeoutMs);
		_climber.config_kD(Constants.kSlotIdx, kGains_Extd.kD, Constants.kTimeoutMs);

        
        
		/* Set acceleration and vcruise velocity - see documentation */
		_climber.configMotionCruiseVelocity(20000, Constants.kTimeoutMs);
		_climber.configMotionAcceleration(60000, Constants.kTimeoutMs);
        
		/* Zero the sensor once on robot boot up */
		_climber.setSelectedSensorPosition(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        _rotate.setSelectedSensorPosition(0);

        //_climber.configClearPositionOnLimitR(true, 30);






        _rotate.configFactoryDefault();
        _rotate.setNeutralMode(NeutralMode.Brake);

        _rotate.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.QuadEncoder, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        /* Set Motion Magic gains in slot0 - see documentation */
		_rotate.selectProfileSlot(Constants.kSlotIdx, Constants.kPIDLoopIdx);
		_rotate.config_kF(Constants.kSlotIdx, kGains_Rotate.kF, Constants.kTimeoutMs);
		_rotate.config_kP(Constants.kSlotIdx, kGains_Rotate.kP, Constants.kTimeoutMs);
		_rotate.config_kI(Constants.kSlotIdx, kGains_Rotate.kI, Constants.kTimeoutMs);
		_rotate.config_kD(Constants.kSlotIdx, kGains_Rotate.kD, Constants.kTimeoutMs);


        /* Set the peak and nominal outputs */
		_rotate.configNominalOutputForward(0, Constants.kTimeoutMs);
		_rotate.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_rotate.configPeakOutputForward(1, Constants.kTimeoutMs);
		_rotate.configPeakOutputReverse(-1, Constants.kTimeoutMs);

        /* Set relevant frame periods to be at least as fast as periodic rate */
		_rotate.setStatusFramePeriod(StatusFrameEnhanced.Status_13_Base_PIDF0, 10, Constants.kTimeoutMs);
		_rotate.setStatusFramePeriod(StatusFrameEnhanced.Status_10_MotionMagic, 10, Constants.kTimeoutMs);

        /* Set acceleration and vcruise velocity - see documentation */
		_rotate.configMotionCruiseVelocity(500, Constants.kTimeoutMs);
		_rotate.configMotionAcceleration(4000, Constants.kTimeoutMs);
        

        _rotate.setInverted(true);
        _rotate.setSensorPhase(true);
    }
    
    @Override
    public void periodic() {
        // This method will be called once per scheduler 
        
        if(SmartDashboard.getBoolean("Climber Pos Data", Constants.CLIMBER_POS_DATA)){
            System.out.print("Extend Pos: " + _climber.getSelectedSensorPosition());
            System.out.println(" Rotate Pos :" + _rotate.getSelectedSensorPosition());
        }
    }
    
    @Override
    public void simulationPeriodic() {
        // This method will be called once per scheduler run when in simulation
        
    }
    
        // Put methods for controlling this subsystem
        // here. Call these from Commands.

    public void climberSetpoint(double targetPos, boolean isCliming){


        //limit the bot extention to the max extention
        if(targetPos > Constants.CLIMBER_MAX_EXTENSION){
            targetPos = Constants.CLIMBER_MAX_EXTENSION;
        }

        thisPress = !climbLowLimit.get();
        //if it is pressed and newly pressed, assign to current value, stop at limit switch
        if(thisPress && thisPress != previousPress){
            lowerLimitSetpoint = Math.max(getClimberPos(), targetPos);
        }
        previousPress = thisPress;

        //limit the output to the position where it newly contacted the limit switch
        if(thisPress){
            targetPos = Math.max(lowerLimitSetpoint, targetPos);
        }



        //if the climber has the weight of the bot on it, 
        //give it some extra power to keep it hovering and allow it to get to the setpoint
        if(isCliming){
            hoverPower = Constants.CLIMBER_HOVER_POWER;
        }
        else{
            hoverPower = 0;
        }
        

        _climber.set(TalonFXControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, hoverPower);

        if(SmartDashboard.getBoolean("Climber Debug", Constants.CLIMBER_DEBUG)){
           
            double motorOutput = _climber.getMotorOutputPercent();

		    /* Prepare line to print */
		    _sb.append("\tOut%:");
		    _sb.append(motorOutput);

		    _sb.append(" \tVel:");
		    _sb.append(_climber.getSelectedSensorVelocity(Constants.kPIDLoopIdx));

            _sb.append(" \terr:");
		    _sb.append(_climber.getClosedLoopError(Constants.kPIDLoopIdx));

		    _sb.append(" \ttrg:");
		    _sb.append(targetPos);
        
            System.out.println(_sb.toString());
            _sb.setLength(0);
        }

        
    }
    
    public double getClimberPos(){
        return _climber.getSelectedSensorPosition();
    }

    public double getRotatePos(){
        return _rotate.getSelectedSensorPosition();
    }

    public double getClimberErr(){
        return _climber.getClosedLoopError();
    }

    public double getRotateErr(){
        return _rotate.getClosedLoopError();
    }

    public double getClimberPower(){
        double power = RobotContainer.getInstance().getLeftY();
        power *= Math.abs(power);
        if(Math.abs(power) < 0.05){
            power = 0;
        }
        return power;
    }

    public void setCLimberSpeed(double speed){
        double currentHeight = _climber.getSelectedSensorPosition();
        double currentRotate = _rotate.getSelectedSensorPosition();
        double maxHeight;

        double angle = (currentRotate - Constants.CLIMBER_VERTICAL)/(Constants.CLIMBER_MAX_PIVOT - Constants.CLIMBER_VERTICAL); //0-1 progress of tilt
        maxHeight = Constants.CLIMBER_CLEAR_EXTENSION + (angle * Math.abs(angle) * (Constants.CLIMBER_MAX_EXTENSION-Constants.CLIMBER_CLEAR_EXTENSION));
        
        // if(_rotate.getSelectedSensorPosition() >= Constants.CLIMBER_CLEAR_ANGLE){
        //     maxHeight = Constants.CLIMBER_MAX_EXTENSION;
        // }else{
        //     maxHeight = Constants.CLIMBER_CLEAR_EXTENSION;
        // }

        if(currentHeight >= maxHeight){
            climberSetpoint(maxHeight - 1000, false);
        }else if(currentHeight >= maxHeight - 2000){
           speed = Math.min(speed, 0);
            _climber.set(ControlMode.PercentOutput, speed);
        }else if(getClimberLimit()){
            speed = Math.max(speed, 0);
            _climber.set(ControlMode.PercentOutput, speed);
        }else{
            _climber.set(ControlMode.PercentOutput, speed);
        }

    }

    public void setClimberSpeedUnbounded(double speed){
        _climber.set(ControlMode.PercentOutput, speed);
    }


    public void rotateSetpoint(double targetPos, boolean isCliming){

        if(targetPos > Constants.CLIMBER_MAX_PIVOT){
            targetPos = Constants.CLIMBER_MAX_PIVOT;
        }
        if(targetPos < Constants.CLIMBER_MIN_PIVOT){
            targetPos = Constants.CLIMBER_MIN_PIVOT;
        }

        if(isCliming){
            rotateHoverPower = Constants.ROTATE_HOVER_POWER;
        }
        else{
            rotateHoverPower = 0;
        }

        _rotate.set(TalonSRXControlMode.MotionMagic, targetPos, DemandType.ArbitraryFeedForward, rotateHoverPower);


        if(SmartDashboard.getBoolean("Rotate Debug", Constants.ROTATE_DEBUG)){



            double motorOutput = _rotate.getMotorOutputPercent();
    
            /* Prepare line to print */
            _sb.append("\tOut%:");
            _sb.append(motorOutput);
    
            _sb.append("\tVel:");
            _sb.append(_rotate.getSelectedSensorVelocity(Constants.kPIDLoopIdx));
    
            _sb.append("\terr:");
            _sb.append(_rotate.getClosedLoopError(Constants.kPIDLoopIdx));
    
            _sb.append("\ttrg:");
            _sb.append(targetPos);
            
            System.out.println(_sb.toString());
            _sb.setLength(0);
        }

        
    }



    public void setRotateSpeed(double speed){
        if(_rotate.getSelectedSensorPosition() >= Constants.CLIMBER_MAX_PIVOT){
            speed = Math.min(speed, 0);
        }
        if(_rotate.getSelectedSensorPosition() <= Constants.CLIMBER_MIN_PIVOT){
            speed = Math.max(speed, 0);
        }
        _rotate.set(ControlMode.PercentOutput, speed);
    }

    public void zeroSensors(){
        _rotate.getSensorCollection().setQuadraturePosition(0, Constants.kTimeoutMs);
        _climber.getSensorCollection().setIntegratedSensorPosition(0, Constants.kTimeoutMs);
    }

    public boolean getClimberLimit(){
        return !climbLowLimit.get();
    }

}

