package frc.robot.subsystems.arm;

import static edu.wpi.first.wpilibj2.command.Commands.runOnce;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import java.util.Set;

public class ArmPivot extends SubsystemBase {
    public final TalonFX armPivot;
    private final PositionVoltage armPosition = new PositionVoltage(0);

    public ArmPivot() {
        armPivot = new TalonFX(ArmConfigs.MOTOR_2_ID, Constants.rioBus);
        armPivot.getConfigurator().apply(ArmConfigs.WRIST_CONFIGS);
    }

    public Command pivotPosition(double currentPosition) {
        return runOnce(() -> armPivot.setControl(armPosition.withPosition(currentPosition)));
    }

    public Command toggle(ArmPivot subsystem) {
        return Commands.defer(
                () -> {
                    if (armPivot.getPosition().getValueAsDouble() > 0.5) {
                        return pivotPosition(1);
                    } else {
                        return pivotPosition(0);
                    }
                },
                Set.of(subsystem));
    }
     public Command moveArmPivot( double power){
        return runEnd(()-> armPivot.set(power),()-> armPivot.set(0) );
    }


}
