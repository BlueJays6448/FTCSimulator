/*
 * Copyright (c) 2014, 2015 Qualcomm Technologies Inc
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * (subject to the limitations in the disclaimer below) provided that the following conditions are
 * met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions
 * and the following disclaimer in the documentation and/or other materials provided with the
 * distribution.
 *
 * Neither the name of Qualcomm Technologies Inc nor the names of its contributors may be used to
 * endorse or promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS LICENSE. THIS
 * SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 * OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF
 * THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.qualcomm.robotcore.hardware;

public class Gamepad {

    /**
     * left analog stick horizontal axis
     */
    public float left_stick_x = 0f;

    /**
     * left analog stick vertical axis
     */
    public float left_stick_y = 0f;

    /**
     * right analog stick horizontal axis
     */
    public float right_stick_x = 0f;

    /**
     * right analog stick vertical axis
     */
    public float right_stick_y = 0f;

    /**
     * dpad up
     */
    public boolean dpad_up = false;

    /**
     * dpad down
     */
    public boolean dpad_down = false;

    /**
     * dpad left
     */
    public boolean dpad_left = false;

    /**
     * dpad right
     */
    public boolean dpad_right = false;

    /**
     * button a
     */
    public boolean a = false;

    /**
     * button b
     */
    public boolean b = false;

    /**
     * button x
     */
    public boolean x = false;

    /**
     * button y
     */
    public boolean y = false;

    /**
     * button guide - often the large button in the middle of the controller. The OS may
     * capture this button before it is sent to the app; in which case you'll never
     * receive it.
     */
    public boolean guide = false;

    /**
     * button start
     */
    public boolean start = false;

    /**
     * button back
     */
    public boolean back = false;

    /**
     * button left bumper
     */
    public boolean left_bumper = false;

    /**
     * button right bumper
     */
    public boolean right_bumper = false;

    /**
     * left stick button
     */
    public boolean left_stick_button = false;

    /**
     * right stick button
     */
    public boolean right_stick_button = false;

    /**
     * left trigger
     */
    public float left_trigger = 0f;

    /**
     * right trigger
     */
    public float right_trigger = 0f;

}
