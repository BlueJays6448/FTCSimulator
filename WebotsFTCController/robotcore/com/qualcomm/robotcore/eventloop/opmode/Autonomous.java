/*
 * Copyright (c) 2015 Robert Atkinson
 *
 *    Ported from the Swerve library by Craig MacFarlane
 *    Based upon contributions and original idea by dmssargent.
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
 * Neither the name of Robert Atkinson, Craig MacFarlane nor the names of its contributors may be used to
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
package com.qualcomm.robotcore.eventloop.opmode;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Provides an easy and non-centralized way of determining the OpMode list
 * shown on an FTC Driver Station.  Put an {@link Autonomous} annotation on
 * your autonomous OpModes that you want to show up in the driver station display.
 *
 * If you want to temporarily disable an opmode, then set then also add
 * a {@link Disabled} annotation to it.
 *
 * @see TeleOp
 * @see Disabled
 * @see OpModeRegistrar
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autonomous
    {
    /**
     * The name to be used on the driver station display. If empty, the name of
     * the OpMode class will be used.
     * @return the name to use for the OpMode in the driver station.
     */
    String name() default "";

    /**
     * Optionally indicates a group of other OpModes with which the annotated
     * OpMode should be sorted on the driver station OpMode list.
     * @return the group into which the annotated OpMode is to be categorized
     */
    String group() default "";

    /**
     * The name of the TeleOp OpMode you'd like to have automagically preselected
     * on the Driver Station when selecting this Autonomous OpMode. If empty, then
     * nothing will be automagically preselected.
     *
     * @return see above
     */
    String preselectTeleOp() default "";
    }
