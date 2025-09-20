package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp(name = "MsToebes Teleop V3")
public class MsToebes extends OpMode {

    String name;
    DcMotorEx frontRight;

    @Override
    public void init() {
        frontRight = hardwareMap.get(DcMotorEx.class, "FrontRight");
        name = "Ms Toebes";
    }

    @Override
    public void loop() {
        this.telemetry.addLine("Hello, " + name );


        frontRight.setPower(-1*gamepad1.right_stick_y);
    }
}
