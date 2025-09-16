package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "ScarletFirstTeleop")
public class ScarletFirstTeleop extends OpMode {  String scarletFirstTeleop = ("phoebe");
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    @Override
    public void init() {
    frontRight = hardwareMap.dcMotor.get("frontRight");
    frontLeft = hardwareMap.dcMotor.get("frontLeft");
    backRight = hardwareMap.dcMotor.get("backRight");
    backLeft = hardwareMap.dcMotor.get("backLeft");
    }

    @Override
    public void loop() {
frontRight.setPower(gamepad1.right_stick_y*-1);
backRight.setPower(gamepad1.right_stick_y);

    }
}
