package frc.robot.subsystems.drive;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
// uncommeted imports for inverting motors
// import com.ctre.phoenix6.configs.MotorOutputConfigs;
// import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

// import com.ctre.phoenix6.signals.InvertedValue;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;

public class Drivetrain extends SubsystemBase {
    private final TalonFX leftLeader;
    private final TalonFX rightLeader;
    private final TalonFX leftFollower;
    private final TalonFX rightFollower;

    public Drivetrain() {
        leftLeader = new TalonFX(DrivetrainConfigs.LEFT_LEADER_ID, Constants.rioBus);
        rightLeader = new TalonFX(DrivetrainConfigs.RIGHT_LEADER_ID, Constants.rioBus);
        leftFollower = new TalonFX(DrivetrainConfigs.LEFT_FOLLOWER_ID, Constants.rioBus);
        rightFollower = new TalonFX(DrivetrainConfigs.RIGHT_FOLLOWER_ID, Constants.rioBus);

        /*One config reused for every motor. To invert a motor:
           1. Uncomment the config line below (once).
           2. Uncomment that motor's two lines (set value + apply).
           3. Swap Clockwise_Positive <-> CounterClockwise_Positive if it still goes the wrong way.
         Step 5 = leaders, Step 8 = followers.
*/
         TalonFXConfiguration driveConfig = new TalonFXConfiguration();
 
         driveConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
         leftLeader.getConfigurator().apply(driveConfig);

         driveConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
         rightLeader.getConfigurator().apply(driveConfig);


 
         driveConfig.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
         leftFollower.getConfigurator().apply(driveConfig);

         driveConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
         rightFollower.getConfigurator().apply(driveConfig);
       
    }

    @Override
    public void periodic() {}

    public void moveLeftSide(double power) {
        leftLeader.set(power);
         leftFollower.set(power);
    }

    public void moveRightSide(double power) {
       rightLeader.set(power);
        rightFollower.set(power);
    }

    // turnPower = rotation (positive = counterclockwise/left),
    // rightPower = forward/back (the parameter name is misleading).
    public void cheesyDrive(double turnPower, double rightPower) {
        double left = rightPower - turnPower;
        double right = rightPower + turnPower;

        // If either side is over 100%, scale both down so the turn ratio stays the same.
        double max = Math.max(Math.abs(left), Math.abs(right));
        if (max > 1.0) {
            left /= max;
            right /= max;
        }

        moveLeftSide(left);
        moveRightSide(right);
    }
}

// code is set up to test leaders if they are going wrong way invert them if they are going the right way comment
// out leaders and uncomment followers and test them if they are going the wrong way invert them
