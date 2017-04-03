package org.usfirst.frc.team78.robot.commands;

import org.usfirst.frc.team78.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Experimental auto. Did not work. Do not pursue further. JR - 20170403
 */
public class AUTO_gearBoilerStraight extends CommandGroup {

    public AUTO_gearBoilerStraight() {

    	
    	addSequential(new driveStraight(-10.5));
    	addSequential(new autoGearDown());
    	addSequential(new driveStraight(4.5));
    }
}
