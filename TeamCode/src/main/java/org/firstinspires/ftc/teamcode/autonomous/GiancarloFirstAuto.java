package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "TaylorinBudgit")
public class GiancarloFirstAuto extends AbstractAutonomous {
    @Override
    public void runOpMode() throws InterruptedException {
        robot.init(this.hardwareMap);
        waitForStart();
        move(10);
        turnRight(3);

    }
}
