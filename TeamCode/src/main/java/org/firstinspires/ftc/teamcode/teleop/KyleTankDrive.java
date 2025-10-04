package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "TankDrive-Kyle")
public class KyleTankDrive extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    double power;

    @Override
    public void init() {
        telemetry.addLine("TankDrive");

        power = 1;

        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get("backLeft");
        backRight = hardwareMap.dcMotor.get("backRight");

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        backLeft.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        frontRight.setDirection(DcMotor.Direction.FORWARD);


    }

    @Override
    public void loop() {
        telemetry.addLine(String.valueOf(power));

        if (gamepad1.aWasPressed()) {
            power *= .5;
        }
        if (gamepad1.bWasPressed()) {
            power *= 2;
        }
        if (gamepad1.xWasPressed()) {
            power = 1;
        }

        frontLeft.setPower(gamepad1.left_stick_y * power);
        backLeft.setPower(gamepad1.left_stick_y * power);
        frontRight.setPower(gamepad1.left_stick_y * power);
        backRight.setPower(gamepad1.left_stick_y * power);




          /* frontleft.setPower(-gamepad1.left_stick_y);
        backleft.setPower(-gamepad1.left_stick_y);
        frontright.setPower(-gamepad1.right_stick_y);
        backright.setPower(-gamepad1.right_stick_y); */


        /* or multiply but it is the same thing
        frontleft.setPower(-1 * gamepad1.left_stick_y);
        backleft.setPower(-1 * gamepad1.left_stick_y);
        frontright.setPower(-1 * gamepad1.right_stick_y);
        backright.setPower(-1 * gamepad1.right_stick_y);
        */

    }
}
