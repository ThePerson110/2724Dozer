package frc.robot.subsystems.arm;

import static edu.wpi.first.wpilibj2.command.Commands.runOnce;

import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import java.util.Set;

public class ArmPitch extends SubsystemBase {
    public final TalonFX armPitch;
    private final PositionVoltage positionVoltage = new PositionVoltage(0);

    public ArmPitch() {
        armPitch = new TalonFX(ArmConfigs.MOTOR_1_ID, Constants.rioBus);
        armPitch.getConfigurator().apply(ArmConfigs.WRIST_CONFIGS);
    }

    public Command pitchPosition(double currentPositon) {
        return runOnce(() -> armPitch.setControl(positionVoltage.withPosition(currentPositon)));
    }

    public Command setPower(double power) {
        return runOnce(() -> armPitch.set(power));
    }

    public Command toggle(ArmPitch subsystem) {
        return Commands.defer(
                () -> {
                    if (armPitch.getPosition().getValueAsDouble() > 0.5) {
                        return pitchPosition(1);
                    } else {
                        return pitchPosition(0);
                    }
                },
                Set.of(subsystem));
    }

    public Command moveArmShovel( double power){
        return runEnd(()-> armPitch.set(power),()-> armPitch.set(0) );
    }
}
