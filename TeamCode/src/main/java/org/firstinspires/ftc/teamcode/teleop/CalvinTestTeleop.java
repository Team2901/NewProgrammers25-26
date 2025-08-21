package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.ImprovedGamepad;
import org.firstinspires.ftc.teamcode.hardware.OutreachHardware;

@TeleOp(name = "CalvinTestTeleop", group = "Test")
public class CalvinTestTeleop extends OpMode {
    OutreachHardware robot = new OutreachHardware();
    ImprovedGamepad gamepad;

    public void moveInches(int inches) {
        int leftTarget = robot.leftDrive.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);
        int rightTarget = robot.rightDrive.getCurrentPosition() + (int) (inches * robot.COUNTS_PER_INCH);

        robot.leftDrive.setTargetPosition(leftTarget);
        robot.rightDrive.setTargetPosition(rightTarget);
    }
    @Override
    public void init() {
        gamepad = new ImprovedGamepad(gamepad1, new ElapsedTime(), "Gamepad");
        robot.init(this.hardwareMap);
    }

    @Override
    public void loop() {
        gamepad.update();

        if(gamepad.a.isInitialPress()){
            moveInches(3 * 12);
        }

        telemetry.addData("Left Count", robot.leftDrive.getCurrentPosition());
        telemetry.addData("Right Count", robot.rightDrive.getCurrentPosition());

        telemetry.addData("Left Target", robot.leftDrive.getTargetPosition());
        telemetry.addData("Right Target", robot.leftDrive.getTargetPosition());

        double leftMotorPower = 0;
        double rightMotorPower = 0;

        leftMotorPower += gamepad.left_stick_y.getValue() / 2;
        rightMotorPower += gamepad.left_stick_y.getValue() / 2;

        if(gamepad.right_stick_x.getValue() > 0) {
            leftMotorPower += gamepad.right_stick_x.getValue() / 2;
        } else {
            rightMotorPower += Math.abs(gamepad.right_stick_x.getValue() /2);
        }

        robot.leftDrive.setPower(leftMotorPower);
        robot.rightDrive.setPower(rightMotorPower);
    }
}
