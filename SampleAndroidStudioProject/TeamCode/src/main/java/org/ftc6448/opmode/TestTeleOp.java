package org.ftc6448.opmode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.ftc6448.simulator.PlatformSupport;

@TeleOp(name = "TestTeleop")
@Disabled
public class TestTeleOp extends OpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();


    DcMotor motor1;
    DcMotor motor2;
    DcMotor motor3;
    DcMotor motor4;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        // Tell the driver that initialization is complete.
        telemetry.addData("Status", "Initialized");

        motor1 = hardwareMap.get(DcMotor.class, "frontRight");
        motor2 = hardwareMap.get(DcMotor.class, "frontLeft");
        motor3 = hardwareMap.get(DcMotor.class, "backRight");
        motor4 = hardwareMap.get(DcMotor.class, "backLeft");

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        double strafe = gamepad1.left_stick_x;
        double drive = -gamepad1.left_stick_y;
        double turn  = gamepad1.right_stick_x;

        if (PlatformSupport.isSimulator()) {
            //if your motors on the real robot were different than the simulator, this could reverse the direction
            drive=-drive;
        }

        motor1.setPower(drive);
        motor2.setPower(drive);
        motor3.setPower(drive);
        motor4.setPower(drive);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("Gamepad", "left_stick_y (%.2f), right_stick_x (%.2f)", drive, turn);

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
