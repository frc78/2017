package org.usfirst.frc.team78.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AUTO_boilerGearBlue extends CommandGroup {

    public AUTO_boilerGearBlue() {
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
    	
    	addSequential(new driveStraight(-2.9)); //Initial Drive (ID)
    	addSequential(new turn(40)); //Turn Angle (<)
    	addSequential(new driveStraight(-6.3)); //Drive After Turn (DAT)
    	addSequential(new autoGearDown());
    	addSequential(new driveStraight(4.5));
    	
     	addSequential(new turn(-0.5));
    }
}
