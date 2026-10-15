package frc.robot.subsystems.arm;

import com.ctre.phoenix6.configs.MotionMagicConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.GravityTypeValue;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;

public class ArmConfigs {
    public static int MOTOR_1_ID = 54;
    public static int MOTOR_2_ID = 55;

    public static Slot0Configs ARM_CONFIGS = new Slot0Configs()
            .withKP(0)
            .withKI(0)
            .withKD(0)
            .withKS(0)
            .withKV(0)
            .withKA(0)
            .withKG(0)
            .withGravityType(GravityTypeValue.Arm_Cosine)
            .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseVelocitySign);

    public static Slot0Configs WRIST_CONFIGS = new Slot0Configs()
            .withKP(0)
            .withKI(0)
            .withKD(0)
            .withKS(0)
            .withKV(0)
            .withKA(0)
            .withKG(0)
            .withGravityType(GravityTypeValue.Arm_Cosine)
            .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseVelocitySign);

    public static TalonFXConfiguration armMotorConfigs = new TalonFXConfiguration()
            .withSlot0(ARM_CONFIGS)
            .withMotionMagic(
                    new MotionMagicConfigs().withMotionMagicAcceleration(2).withMotionMagicCruiseVelocity(1));

    public static TalonFXConfiguration wristMotorConfigs = new TalonFXConfiguration()
            .withSlot0(WRIST_CONFIGS)
            .withMotionMagic(
                    new MotionMagicConfigs().withMotionMagicAcceleration(2).withMotionMagicCruiseVelocity(1));
}
