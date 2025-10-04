package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.bylazar.telemetry.TelemetryManager;
@TeleOp(name = "ScarletFirstTeleop")
public class ScarletFirstTeleop extends OpMode {  String scarletFirstTeleop = ("phoebe");
    public DcMotor frontRight;
    public DcMotor frontLeft;
    public DcMotor backRight;
    public DcMotor backLeft;
    double speed = 0.5;
    double wheelDiametermm = 96;
    double wheelCircumferencemm = wheelDiametermm *2 *Math.PI;

    private static TelemetryManager panelsTelemetry = null;

    IMU imu;
    private GoBildaPinpointDriver odo;

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

    imu = hardwareMap.get(IMU.class, "imu");
    // This needs to be changed to match the orientation on your robot
    RevHubOrientationOnRobot.LogoFacingDirection logoDirection =
            RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
    RevHubOrientationOnRobot.UsbFacingDirection usbDirection =
            RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD;

    RevHubOrientationOnRobot orientationOnRobot = new
            RevHubOrientationOnRobot(logoDirection, usbDirection);
    imu.initialize(new IMU.Parameters(orientationOnRobot));

    odo = hardwareMap.get(GoBildaPinpointDriver.class,"odo");
    odo.setOffsets(-7, -3, DistanceUnit.MM);
    odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
    odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
    odo.resetPosAndIMU();
    panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
    panelsTelemetry.addLine("Hello World");




    }

    @Override
    public void loop() {

        double joyStickAngle = Math.atan2(-gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.toRadians(90);
        double magnitude = Math.hypot(-gamepad1.right_stick_y, gamepad1.right_stick_x);
        telemetry.addData("joyStickAngle", joyStickAngle);
        double forward = magnitude * Math.cos(joyStickAngle);
        double right = magnitude * Math.sin(joyStickAngle);
        telemetry.addData("forward", forward);
        telemetry.addData("right", right);
        move(forward, right, gamepad1.left_stick_x);
        if (gamepad1.yWasPressed()){
            telemetry.addLine("y pressed");

        }
        telemetry.addData("X offset", odo.getXOffset(DistanceUnit.MM));
        telemetry.addData("Y offset", odo.getYOffset(DistanceUnit.MM));
        telemetry.addData("Device Version Number:", odo.getDeviceVersion());
        telemetry.addData("Heading Scalar", odo.getYawScalar());

    }
    public void move(double forward, double right, double turn) {
        frontRight.setPower((forward-turn-right) *speed);
        backRight.setPower((forward-turn+right) *speed);
        frontLeft.setPower((forward+turn+right) *speed);
        backLeft.setPower((forward+turn-right) *speed);
    }
}
