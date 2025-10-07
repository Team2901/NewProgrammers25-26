package org.firstinspires.ftc.teamcode.teleop;

//import com.bylazar.ftcontrol.panels.Panels;
//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.telemetry.TelemetryManager;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


@TeleOp(name = "Clara Teleop V6") // teleop
public class Clara extends OpMode {

   String name;
   DcMotorEx FrontRight;
   DcMotorEx FrontLeft;
   DcMotorEx BackRight;
   DcMotorEx BackLeft;
   double powerMultiply = 1;
   double speed = 0.6;
    IMU imu;

    double wheelDiametermm = 96;
    double wheelCircumferencemm = wheelDiametermm *2 *Math.PI;

    double wheelCircumferencein = wheelCircumferencemm * 0.0393701;
    double ticksPerRev = 1338; //for goBilda yellowjacket motors

    int targetTicks;
    boolean testingAMotor = false;
    private GoBildaPinpointDriver odo;

    //panelsTelemetry = Panels.getTelemetry;
    //private static TelemetryManager panelsTelemetry = null;


    @Override
    public void init() {
        FrontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        FrontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        name = "Clara";
        FrontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        FrontLeft.setDirection(DcMotorEx.Direction.REVERSE);
        FrontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        FrontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackRight = hardwareMap.get(DcMotorEx.class, "backRight");
        BackRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BackRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BackLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
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
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        odo.setOffsets(-7, -3, DistanceUnit.MM);
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
        //panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();
        //panelsTelemetry.addLine("Hello World");

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
    if (testingAMotor){
        FrontRight.setPower(0.5);
        telemetry.addData("currentPosition", FrontRight.getCurrentPosition());
        }
    else{
            double joyStickAngle = Math.atan2(-gamepad1.right_stick_y, gamepad1.right_stick_x) - Math.toRadians(90);
            double magnitude = Math.hypot(-gamepad1.right_stick_y, gamepad1.right_stick_x);
            //telemetry.addData("joyStickAngle", joyStickAngle);
            double forward = magnitude * Math.cos(joyStickAngle);
            double right = magnitude * Math.sin(joyStickAngle);
            telemetry.addData("forward", forward);
            telemetry.addData("right", right);
            if ((Math.abs(forward) > 0) || (Math.abs(forward) > 0)) {
                move(forward, right, gamepad1.left_stick_x);
            }
        }

    //if (gamepad1.yWasPressed()){
        if(gamepad1.y){
            telemetry.addLine("y pressed");
            moveDistanceInInches(5*12);
        }
        telemetry.addData("targetTicks", targetTicks);
        telemetry.addData("currentTicks", FrontLeft.getCurrentPosition());
        if (targetTicks > 0){
            if (isDoneMoving()){
                stopMoving();
            }
        }
        if (gamepad1.xWasPressed()){
            testingAMotor = true;
            telemetry.addLine("x pressed");
        }
        if (gamepad1.xWasReleased()){
            testingAMotor = false;
            FrontRight.setPower(0);
        }
    telemetry.addData("X offset", odo.getXOffset(DistanceUnit.MM));
    telemetry.addData("Y offset", odo.getYOffset(DistanceUnit.MM));
    telemetry.addData("Device Version Number", odo.getDeviceVersion());
    telemetry.addData("Heading Scalar", odo.getYawScalar());


    }

public void   stopMoving(){
        FrontRight.setPower(0);
        FrontLeft.setPower(0);
        BackLeft.setPower(0);
        BackRight.setPower(0);
        targetTicks = 0;
}
public void moveDistanceInInches(double distance){

    targetTicks = (int)(distance / wheelCircumferencein * ticksPerRev + FrontRight.getCurrentPosition());
    FrontRight.setPower(0.5);
    FrontLeft.setPower(0.5);
    BackRight.setPower(0.5);
    BackLeft.setPower(0.5);
}
public boolean isDoneMoving(){
        return(FrontRight.getCurrentPosition() >= targetTicks);
}



public void move(double forward, double right, double turn) {
    FrontRight.setPower((forward-turn-right) *speed);
    BackRight.setPower((forward-turn+right) *speed);
    FrontLeft.setPower((forward+turn+right) *speed);
    BackLeft.setPower((forward+turn-right) *speed);
}
}


