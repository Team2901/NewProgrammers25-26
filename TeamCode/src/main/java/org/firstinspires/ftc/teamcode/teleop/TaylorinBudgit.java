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
    }

    @Override
    public void loop() {
        telemetry.addLine(taylorinBudgit);
  move(-gamepad1.right_stick_y, gamepad1.right_stick_x, -1*gamepad1.left_stick_y);
    }


    public void move(double forward, double right, double turn) {
        telemetry.addLine(taylorinBudgit);
        frontRight.setPower(v2 * (forward-turn-right));
        frontLeft.setPower(v2 * (forward+turn+right));
        backRight.setPower(v2 * (forward-turn+right));
        backLeft.setPower(v2 * (forward+turn-right));
    }
}



