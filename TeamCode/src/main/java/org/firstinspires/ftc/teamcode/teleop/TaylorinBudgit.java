package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

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


        telemetry.addLine(taylorinBudgit);
    }

    @Override
    public void loop() {
        telemetry.addLine(taylorinBudgit);
        frontRight.setPower(-1*gamepad1.right_stick_y);
        frontRight.setPower(-1*gamepad1.right_stick_x);
    }
}

