package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_blueLoadingStationGear extends CommandGroup {

    public AUTO_blueLoadingStationGear() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	addSequential(new driveStraight(-3));
    	addSequential(new turn(-47));
    	//-6.0 fell short, added .75 feet to both red and blue loading station autos after Qualification Match 14 on Saturday, March 25, 2017 at RIDE
    	addSequential(new driveStraightDistanceTime(-6.55, 5000));
    	addSequential(new autoGearDown());
    	//Timer.delay(1.5);
    	
    	//Wait 1.5 seconds for gear deploy
    	addSequential(new wait(1500));
    	
    	addSequential(new driveStraight(0.3), 0.05);
    	addSequential(new driveStraight(3));
    	addSequential(new gearOuttake("out", 0.5), 0.5);
    	addSequential(new gearOuttake("out", 0), 0.5);
    	addSequential(new driveStraight(2));
    	addSequential(new gearUp(), 0.5);
    	addSequential(new turn(0.5));
    	addSequential(new driveStraightFast(-25));
    }
}