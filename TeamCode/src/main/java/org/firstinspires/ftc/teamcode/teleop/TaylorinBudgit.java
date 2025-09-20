package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.function.Function;

@TeleOp(name = "TaylorinBudgit")
public class TaylorinBudgit extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    double drive;
    String taylorinBudgit = "dog";

    IMU imu;
    double v2 = 10;
    @Override
    public void init() {

        frontRight = hardwareMap.dcMotor.get("frontRight");
        backRight = hardwareMap.dcMotor.get("backRight");
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        backLeft = hardwareMap.dcMotor.get("backLeft");

frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
backRight.setDirection(DcMotorSimple.Direction.REVERSE);
frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        telemetry.addLine(taylorinBudgit);

        imu = hardwareMap.get(IMU.class, "imu");
        // This needs to be changed to match the orientation on your robot
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));
    }

    @Override
    public void loop() {
        telemetry.addData("angle",imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES));
        telemetry.addLine(taylorinBudgit);
        double y= gamepad1.right_stick_y;
        double x= gamepad1.right_stick_x;

        double magnitude=Math.hypot(x,y);
        double angle=Math.atan2(y, x)- Math.toRadians(90);

        double forward=Math.cos(angle)*magnitude;
        double right=Math.sin(angle)*magnitude;
        move(forward, right, -0.25*gamepad1.left_stick_y);
    }


    public void move(double forward, double right, double turn) {
        telemetry.addLine(taylorinBudgit);
        frontRight.setPower(v2 * (forward-turn-right));
        frontLeft.setPower(v2 * (forward+turn+right));
        backRight.setPower(v2 * (forward-turn+right));
        backLeft.setPower(v2 * (forward+turn-right));
    }
}



