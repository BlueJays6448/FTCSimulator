/*
 * Copyright (c) 2015 Molly Nicholas
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
 * Neither the name of Molly Nicholas nor the names of its contributors may be used to
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

package com.qualcomm.robotcore.robot;

public enum RobotState {
  UNKNOWN(-1),
  NOT_STARTED(0),
  INIT(1),
  RUNNING(2),
  STOPPED(3),
  EMERGENCY_STOP(4);

  private int robotState;

  RobotState(int state) {
    this.robotState = (byte)state;
  }

  public byte asByte() {
    return (byte) robotState;
  }

  public static RobotState fromByte(int b) {
    for (RobotState robotState : RobotState.values()) {
      if (robotState.robotState == b) {
        return robotState;
      }
    }
    return UNKNOWN;
  }

  public String toString() {
    switch (this) {
      case UNKNOWN:             return "Unknown";
      case NOT_STARTED:         return "NotStarted";
      case INIT:                return "Init";
      case RUNNING:             return "Running";
      case STOPPED:             return "Stopped";
      case EMERGENCY_STOP:      return "EmergencyStop";
      default:                  return "InternalError";
    }
  }
}
