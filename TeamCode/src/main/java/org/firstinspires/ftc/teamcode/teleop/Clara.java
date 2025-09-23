package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

@TeleOp(name = "Clara Teleop V5")
public class Clara extends OpMode {

   String name;
   DcMotorEx FrontRight;
   DcMotorEx FrontLeft;
   DcMotorEx BackRight;
   DcMotorEx BackLeft;
   double powerMultiply = 1;
   double speed = 0.6;
    IMU imu;



    @Override
    public void init() {
        FrontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        name = "Clara";
        FrontLeft = hardwareMap.get(DcMotorEx.class, "FrontLeft");
        FrontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight = hardwareMap.get(DcMotorEx.class, "BackRight");
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft = hardwareMap.get(DcMotorEx.class, "BackLeft");
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
            /*
            FrontRight.setPower(-powerMultiply*gamepad1.right_stick_y));
            FrontLeft.setPower(-powerMultiply*gamepad1.left_stick_y));
            BackRight.setPower(-powerMultiply*gamepad1.right_stick_y));
            BackLeft.setPower(-powerMultiply*gamepad1.left_stick_y));
                        */
            powerMultiply = 2;
        }
        if (gamepad1.yWasPressed()) {
            /*
            FrontRight.setPower(*);
            FrontLeft.setPower(0.5*);
            BackLeft.setPower(0.5*)
            BackRight.setPower(0.5*);
            
            this.telemetry.addLine("Hello, "  + name );
            */
            powerMultiply = 0.5;


        }
        telemetry.addLine(String.valueOf(powerMultiply));
move(-gamepad1.right_stick_y, gamepad1.right_stick_x, gamepad1.left_stick_x);

    }
    public void move(double forward, double right, double turn) {
        FrontRight.setPower((forward-turn-right) *speed);
        BackRight.setPower((forward-turn+right) *speed);
        FrontLeft.setPower((forward+turn+right) *speed);
        BackLeft.setPower((forward+turn-right) *speed);
    }


}

