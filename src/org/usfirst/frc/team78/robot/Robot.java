
package org.usfirst.frc.team78.robot;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.usfirst.frc.team78.robot.commands.AUTO_blueLoadingStationGear;
import org.usfirst.frc.team78.robot.commands.AUTO_boilerGearBlue;
import org.usfirst.frc.team78.robot.commands.AUTO_boilerGearRed;
import org.usfirst.frc.team78.robot.commands.AUTO_doNothing;
import org.usfirst.frc.team78.robot.commands.AUTO_driveFor5;
import org.usfirst.frc.team78.robot.commands.AUTO_frontGear;
import org.usfirst.frc.team78.robot.commands.AUTO_gearBoilerStraight;
import org.usfirst.frc.team78.robot.commands.AUTO_redLoadingStationGear;
import org.usfirst.frc.team78.robot.commands.TESTAUTO_driveTillCurrentDraw;
import org.usfirst.frc.team78.robot.commands.gearIntake;
import org.usfirst.frc.team78.robot.subsystems.Chassis;
import org.usfirst.frc.team78.robot.subsystems.Climber;
import org.usfirst.frc.team78.robot.subsystems.Gear;
import org.usfirst.frc.team78.robot.subsystems.Intake;
import org.usfirst.frc.team78.robot.subsystems.Shooter;
import org.usfirst.frc.team78.robot.subsystems.Vision;
import org.usfirst.frc.team78.robot.subsystems.Climber;

import edu.wpi.cscore.CameraServerJNI;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
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
	public static final Compressor c = new Compressor(0);
	public static final PowerDistributionPanel PDP = new PowerDistributionPanel(0);
	public static OI oi;
	
	public static NetworkTable table;
	
	int countdown = 6;
	boolean isReleased = true;

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	
	@Override 
	public void robotInit() { 
		oi = new OI();

		//auto chooser 
		chooser = new SendableChooser<>();
		
		chooser.addObject("Do nothing", new AUTO_doNothing());								//Will run if no choice is made
		chooser.addObject("Drive for 5", new AUTO_driveFor5());								//Robot only drives enough for movement bonus
		chooser.addObject("Front Gear", new AUTO_frontGear());								//Middle gear either alliance
		chooser.addObject("Boiler Gear BLUE", new AUTO_boilerGearBlue());					//untested	Boiler side peg for Blue Alliance
		chooser.addObject("Boiler Gear RED", new AUTO_boilerGearRed());						//Boiler side peg for Red Alliance
		chooser.addObject("Loading Gear BLUE", new AUTO_blueLoadingStationGear());			//Loading zone side peg for Blue Alliance
		chooser.addDefault("Loading Gear RED", new AUTO_redLoadingStationGear());			//Loading zone side peg for Red Alliance

		
		
		SmartDashboard.putData("Auto mode", chooser);
		//end auto chooser
		
		table = NetworkTable.getTable("DataTable");
		
		//Turn on compressor
    	c.setClosedLoopControl(true);
    	
    	new Thread(() -> {
    	//camera
    		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
    		camera.setVideoMode(VideoMode.PixelFormat.kMJPEG, 200, 160, 10);
//    		camera.setResolution(240, 180);
//    		camera.setFPS(5);
    		

    	}).start();
    	
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
//		tDashboard.putNumber("peg", Robot.vision.getGearPegX());
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

//		SmartDashboard.putNumber("Starboard motor '.get' ", chassis.starboardFront.getPosition());
//		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());
//		SmartDashboard.putNumber("nav-X", Robot.chassis.getAngle());
		
		SmartDashboard.putNumber("Port current 'PI' ", Robot.chassis.getPortCurrent());
		SmartDashboard.putNumber("Starboard current 'Compressor' ", Robot.chassis.getStarboardCurrent());
		
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
		
//		SmartDashboard.putNumber("starboard motor '.get' ", chassis.starboardFront.getPosition());
//		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());	
		
		chassis.motorInit();
		climber.climberInit();
		
		//UNCOMMENT TO HAVE LIGHTS TURN OFF IN TURBO MODE
		//gear.lightOn();
	}

	/**
	 * This function is called periodically during operator control
	 */ 
	@Override
	public void teleopPeriodic() {
		
		SmartDashboard.putNumber("starboard motor '.get' ", chassis.starboardFront.getPosition());
		SmartDashboard.putNumber("port motor '.get' ", chassis.portFront.getPosition());
//				
//		boolean bool = false;
//		if(Robot.gear.intakeMotor.get() != 0){
//			bool = true;
//		}
//		SmartDashboard.putBoolean("gear intake wheels", bool);
//		SmartDashboard.putNumber("current", Robot.gear.getCurrent());
//		
//		boolean currentDraw = Robot.gear.currentDraw;
//		SmartDashboard.putBoolean("gear intake current draw", currentDraw);
//		
//		SmartDashboard.putNumber("nav-X", Robot.chassis.getAngle());
		 
		//countdown
		
//		if(Robot.oi.manipulatorBack.get() && isReleased){
//			countdown--;
//			isReleased = false;
//		}
//		if(!Robot.oi.manipulatorBack.get()){
//			isReleased = true;
//		}
//		
//		SmartDashboard.putNumber("Gears Left", countdown);
		
		SmartDashboard.putNumber("Port current 'PI' 4", Robot.chassis.getPortCurrent());
		SmartDashboard.putNumber("Starboard current 'Compressor' 3", Robot.chassis.getStarboardCurrent());
		
		SmartDashboard.putNumber("drive motor 2", Robot.chassis.starboardTop.getOutputCurrent());
		SmartDashboard.putNumber("drive motor 1", Robot.chassis.starboardRear.getOutputCurrent());
		
		SmartDashboard.putNumber("drive motor 5", Robot.chassis.portTop.getOutputCurrent());
		SmartDashboard.putNumber("drive motor 6", Robot.chassis.portRear.getOutputCurrent());
		
		SmartDashboard.putNumber("motor 3", PDP.getCurrent(0));
		SmartDashboard.putNumber("motor 1", PDP.getCurrent(1));
		SmartDashboard.putNumber("motor 2", PDP.getCurrent(2));
		
		SmartDashboard.putNumber("motor 4", PDP.getCurrent(13));
		SmartDashboard.putNumber("motor 5", PDP.getCurrent(14));
		SmartDashboard.putNumber("motor 6", PDP.getCurrent(15));
		
		SmartDashboard.putNumber("Total Current", PDP.getTotalCurrent());
		
		
	
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
