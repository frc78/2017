
package org.usfirst.frc.team78.robot;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.usfirst.frc.team78.robot.commands.AUTO_boilerGearBlue;
import org.usfirst.frc.team78.robot.commands.AUTO_boilerGearRed;
import org.usfirst.frc.team78.robot.commands.AUTO_doNothing;
import org.usfirst.frc.team78.robot.commands.AUTO_driveFor5;
import org.usfirst.frc.team78.robot.commands.AUTO_frontGear;
import org.usfirst.frc.team78.robot.commands.gearIntake;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Climber;
import org.usfirst.frc.team78.robot.subsystems.Gear;
import org.usfirst.frc.team78.robot.subsystems.Intake;
import org.usfirst.frc.team78.robot.subsystems.Shooter;
import org.usfirst.frc.team78.robot.subsystems.Vision;
import org.usfirst.frc.team78.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTablesJNI;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team78.robot.commands.intake;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final Chassis chassis = new Chassis();
	public static final Vision vision = new Vision();
	public static final Shooter shooter = new Shooter();
	public static final Intake intake = new Intake();
	public static final Gear gear = new Gear();
	public static final Climber climber = new Climber();
	public static OI oi;
	
	public static NetworkTable table;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	@Override 
	public void robotInit() {
		oi = new OI();
//		chooser.addDefault("Default Auto", new ExampleCommand());
//		chooser.addObject("My Auto", new MyAutoCommand());
		
		//UNCOMMENT AUTO MODES
		
		chooser = new SendableChooser<>();
		//chooser.addObject("Drive for 5", new AUTO_driveFor5());
		//chooser.addObject("Do nothing", new AUTO_doNothing());
		//chooser.addObject("Front Gear", new AUTO_frontGear());
		chooser.addDefault("Boiler Gear Red", new AUTO_boilerGearRed());
		//chooser.addObject("Boiler Gear Blue", new AUTO_boilerGearBlue());
		SmartDashboard.putData("Auto mode", chooser);
		
		table = NetworkTable.getTable("DataTable");
	
		Compressor c = new Compressor(0);
    	c.setClosedLoopControl(true);
		
		//Shooter init 
		shooter.shooterMotorInit();
		shooter.shooterPort.setPosition(0);
		
		//Chassis init 
		chassis.motorInit();
		chassis.portFront.setPosition(0);
		chassis.starboardFront.setPosition(0);
		
		//Gear init
		gear.gearInit();
		
		//climber init
		climber.climberInit();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		SmartDashboard.putNumber("X0", Robot.vision.getGearX0());
		SmartDashboard.putNumber("X1", Robot.vision.getGearX1());
		SmartDashboard.putNumber("peg", Robot.vision.getGearPegX());
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		autonomousCommand = chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

		SmartDashboard.putNumber("Starboard motor '.get' ", chassis.starboardFront.getPosition());
		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());
		SmartDashboard.putNumber("nav-X", Robot.chassis.getAngle());
		
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {//
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		
		SmartDashboard.putNumber("starboard motor '.get' ", chassis.starboardFront.getPosition());
		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());	
		
		chassis.motorInit();
		climber.climberInit();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		SmartDashboard.putNumber("starboard motor '.get' ", chassis.starboardFront.getPosition());
		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());
				
//		SmartDashboard.putNumber("encoder", ft);
		
		SmartDashboard.putNumber("ultra volt",Robot.gear.getUltraVolt());
		SmartDashboard.putNumber("ultra val",Robot.gear.getUltraVal());
		
//		SmartDashboard.putBoolean("photo", Robot.gear.getPhotoSwitch());
		
		SmartDashboard.putNumber("current", Robot.gear.getCurrent());
		
		SmartDashboard.putNumber("nav-X", Robot.chassis.getAngle());
		
		SmartDashboard.putNumber("X0", Robot.vision.getGearX0());
		SmartDashboard.putNumber("X1", Robot.vision.getGearX1());
		SmartDashboard.putNumber("peg", Robot.vision.getGearPegX());
		
		Scheduler.getInstance().run();


		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
