package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "ClaraJoystickTester")
public class ClaraJoystickTester extends OpMode {

   String name;
   DcMotorEx FrontRight;
   DcMotorEx FrontLeft;
   DcMotorEx BackRight;
   DcMotorEx BackLeft;
   double powerMultiply = 1;
   double speed = 0.6;
    IMU imu;

    double wheelDiametermm = 96;
    double wheelCircumferencemm = wheelDiametermm *2 *Math.PI;

    double wheelCircumferencein = wheelCircumferencemm * 0.0393701;
    double ticksPerRev = 1338; //for goBilda yellowjacket motors

    int targetTicks;




    @Override
    public void init() {
        FrontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        name = "Clara";
        FrontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        FrontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight = hardwareMap.get(DcMotorEx.class, "backRight");
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        BackLeft.setDirection(DcMotorEx.Direction.REVERSE);
        BackLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        imu = hardwareMap.get(IMU.class, "imu");
        // This needs to be changed to match the orientation on your robot
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.UP;
        RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD;

        RevHubOrientationOnRobot orientationOnRobot = new
                RevHubOrientationOnRobot(logoDirection, usbDirection);
        imu.initialize(new IMU.Parameters(orientationOnRobot));

    }

    @Override
    public void loop() {

        if (gamepad1.aWasPressed()) {
            telemetry.addLine("a was pressed");
        }
        //if (gamepad1.yWasPressed()) {
        if(gamepad1.y){
            this.telemetry.addLine("y was pressed" );
        }
        telemetry.addLine(String.valueOf(powerMultiply));

        double joyStickAngle = Math.atan2(-gamepad1.right_stick_y,gamepad1.right_stick_x) - Math.toRadians(90);
        double magnitude = Math.hypot(-gamepad1.right_stick_y, gamepad1.right_stick_x);
        //telemetry.addData("joyStickAngle", joyStickAngle);
        double forward = magnitude* Math.cos(joyStickAngle);
        double right = magnitude* Math.sin(joyStickAngle);
        telemetry.addData("forward", forward);
        telemetry.addData("right", right);
    }
}



