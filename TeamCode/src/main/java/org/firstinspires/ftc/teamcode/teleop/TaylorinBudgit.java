package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import java.util.function.Function;

@TeleOp(name = "TaylorinBudgit")
public class TaylorinBudgit extends OpMode {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;
    double drive;
    String taylorinBudgit = "dog";
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
    }

    @Override
    public void loop() {
        telemetry.addLine(taylorinBudgit);
        frontRight.setPower(-1*gamepad1.right_stick_y);
        frontLeft.setPower(-1*gamepad1.right_stick_x);
        backRight.setPower(-1*gamepad1.right_stick_y);
        backLeft.setPower(-1*gamepad1.right_stick_x);
    }
}

