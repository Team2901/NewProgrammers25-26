package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "ScarletFirstTeleop")
public class ScarletFirstTeleop extends OpMode {  String scarletFirstTeleop = ("phoebe");
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    double speed = 0.5;
    @Override
    public void init() {
    frontRight = hardwareMap.dcMotor.get("frontRight");
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    backRight = hardwareMap.dcMotor.get("backRight");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
    backRight.setDirection(DcMotorSimple.Direction.REVERSE);
    frontLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    backLeft.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
frontRight.setPower(gamepad1.right_stick_y*-speed);
backRight.setPower(gamepad1.right_stick_y*-speed);
frontLeft.setPower(gamepad1.left_stick_y*-speed);
backLeft.setPower(gamepad1.left_stick_y*-speed);

    }
}
