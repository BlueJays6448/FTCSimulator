package org.ftc6448.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.ftc6448.simulator.PlatformSupport;

@Autonomous(name = "Test")
@Disabled
public class TestAutonomous extends LinearOpMode {

    protected final ElapsedTime runtime = new ElapsedTime();
    protected int cycleCount;
    protected double fps;

    public void runOpMode() throws InterruptedException {

        waitForStart();
        runtime.reset();

        long startTime=PlatformSupport.getCurrentTimeMillis();

        DcMotor motor1 = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor motor2 = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor motor3 = hardwareMap.get(DcMotor.class, "backRight");
        DcMotor motor4 = hardwareMap.get(DcMotor.class, "backLeft");

        int frameCount=0;
        while (opModeIsActive()) {


            if (runtime.milliseconds()>5000) {
                motor1.setPower(0);
                motor2.setPower(0);
                motor3.setPower(0);
                motor4.setPower(0);
                telemetry.addData("State", "Stopped");
            }
            else {
                motor1.setPower(0.2);
                motor2.setPower(0.2);
                motor3.setPower(0.2);
                motor4.setPower(0.2);
                telemetry.addData("State", "Running");
            }

            cycleCount++;
            frameCount++;

            long curTime=PlatformSupport.getCurrentTimeMillis();
            if (frameCount >=10) {
                fps=(frameCount/((curTime-startTime)/1000.0));
                startTime=curTime;
                frameCount=0;
            }

            telemetry.addData("FPS", "Loop speed - "+fps+" at elapsed time "+runtime.milliseconds());

            telemetry.update();


            //on the real robot, this method call does nothing
            //on the simulator, it forces the opmode to sync its loop
            //to Webots simulator time steps
            PlatformSupport.waitForSimulatorTimeStep();
        }
    }

}
