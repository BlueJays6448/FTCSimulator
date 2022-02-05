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

package com.qualcomm.robotcore.util;

import java.util.Calendar;

import org.firstinspires.ftc.robotcore.internal.files.LogOutputStream;

import com.qualcomm.robotcore.exception.RobotCoreException;

/**
 * Allows consistent logging across all RobotCore packages
 */
@SuppressWarnings("WeakerAccess")
public class RobotLog {

  /*
   * Currently only supports android style logging, but may support more in the future.
   */

  /*
   * Only contains static utility methods
   */
  private RobotLog() {}

  //------------------------------------------------------------------------------------------------
  // State
  //------------------------------------------------------------------------------------------------

  public static final String OPMODE_START_TAG = "******************** START - OPMODE %s ********************";
  public static final String OPMODE_STOP_TAG  = "******************** STOP - OPMODE %s ********************";

  private static final Object globalErrorLock = new Object();
  private static String       globalErrorMessage = "";
  private static final Object globalWarningLock = new Object();
  private static String       globalWarningMessage = "";
  private static double       msTimeOffset = 0.0;
  private static boolean      globalErrorMsgSticky = false;
  private static boolean      globalWarningMsgSticky = false;

  public  static final String TAG = "RobotCore";

  private static String matchLogFilename = null;

  /*
   * Prefixing with exec causes the call to destory to kill logcat instead of it's spawning shell.
   */
  private static final String logcatCommandRaw     = "logcat";
  private static final String logcatCommand        = "exec " + logcatCommandRaw;
  private static final int    kbLogcatQuantum      = 4 * 1024;
  private static final int    logcatRotatedLogsMax = 4;
  private static final String logcatFormat         = "threadtime";
  private static final String logcatFilter         = "UsbRequestJNI:S UsbRequest:S art:W ThreadPool:W System:W ExtendedExtractor:W OMXClient:W MediaPlayer:W dalvikvm:W  *:V";

  private static Calendar matchStartTime     = null;

  //------------------------------------------------------------------------------------------------
  // Time Synchronization
  //------------------------------------------------------------------------------------------------

  /** Processes the reception of a set of NTP timestamps between this device (t0 and t3) and
   * a remote device (t1 and t2) with whom it is trying to synchronize time. Our current implementation
   * is very, very crude: we just calculate the instantaneous offset, and remember. But that's probably
   * good enough for trying to coordinate timestamps in logs.
   */
  public static void processTimeSynch(long t0, long t1, long t2, long t3) {
    if (t0==0 || t1==0 || t2==0 || t3==0)
      return; // invalid packet data

    // https://en.wikipedia.org/wiki/Network_Time_Protocol
    // offset is how much the time source is ahead of us (ie: not behind)
    double offset = ((t1 - t0) + (t2 - t3)) / 2.0;
    setMsTimeOffset(offset);
  }

  // Records the time difference between this device and a device with whom we are synchronizing our time
  public static void setMsTimeOffset(double offset) {
    msTimeOffset = offset;
  }

  public static long getRemoteTime() {
	  //TODO: get simulation time
    return System.currentTimeMillis();
  }

  public static long getRemoteTime(long localTime) {
    return (long)(localTime + msTimeOffset + 0.5);
  }

  public static long getLocalTime(long remoteTime) {
    return (long)(remoteTime - msTimeOffset + 0.5);
  }

  //------------------------------------------------------------------------------------------------
  // Logging API
  //------------------------------------------------------------------------------------------------

  public static void a(String format, Object... args) { v(String.format(format, args)); }
  public static void a(String message) {
    internalLog(ASSERT, TAG, message);
  }
  public static void aa(String tag, String format, Object... args) { vv(tag, String.format(format, args)); }
  public static void aa(String tag, String message) {
    internalLog(ASSERT, tag, message);
  }
  public static void aa(String tag, Throwable throwable, String format, Object... args) { vv(tag, throwable, String.format(format, args)); }
  public static void aa(String tag, Throwable throwable, String message) {
    internalLog(ASSERT, tag, throwable, message);
  }

  public static void v(String format, Object... args) { v(String.format(format, args)); }
  public static void v(String message) {
    internalLog(VERBOSE, TAG, message);
  }
  public static void vv(String tag, String format, Object... args) { vv(tag, String.format(format, args)); }
  public static void vv(String tag, String message) {
    internalLog(VERBOSE, tag, message);
  }
  public static void vv(String tag, Throwable throwable, String format, Object... args) { vv(tag, throwable, String.format(format, args)); }
  public static void vv(String tag, Throwable throwable, String message) {
    internalLog(VERBOSE, tag, throwable, message);
  }

  public static void d(String format, Object... args) { d(String.format(format, args)); }
  public static void d(String message) {
    internalLog(DEBUG, TAG, message);
  }
  public static void dd(String tag, String format, Object... args) { dd(tag, String.format(format, args)); }
  public static void dd(String tag, String message) {
    internalLog(DEBUG, tag, message);
  }
  public static void dd(String tag, Throwable throwable, String format, Object... args) { dd(tag, throwable, String.format(format, args)); }
  public static void dd(String tag, Throwable throwable, String message) {
    internalLog(DEBUG, tag, throwable, message);
  }

  public static void i(String format, Object... args) { i(String.format(format, args)); }
  public static void i(String message) {
    internalLog(INFO, TAG, message);
  }
  public static void ii(String tag, String format, Object... args) { ii(tag, String.format(format, args)); }
  public static void ii(String tag, String message) {
    internalLog(INFO, tag, message);
  }
  public static void ii(String tag, Throwable throwable, String format, Object... args) { ii(tag, throwable, String.format(format, args)); }
  public static void ii(String tag, Throwable throwable, String message) {
    internalLog(INFO, tag, throwable, message);
  }

  public static void w(String format, Object... args) { w(String.format(format, args)); }
  public static void w(String message) {
    internalLog(WARN, TAG, message);
  }
  public static void ww(String tag, String format, Object... args) { ww(tag, String.format(format, args)); }
  public static void ww(String tag, String message) {
    internalLog(WARN, tag, message);
  }
  public static void ww(String tag, Throwable throwable, String format, Object... args) { ww(tag, throwable, String.format(format, args)); }
  public static void ww(String tag, Throwable throwable, String message) {
    internalLog(WARN, tag, throwable, message);
  }

  public static void e(String format, Object... args) { e(String.format(format, args)); }
  public static void e(String message) {
    internalLog(ERROR, TAG, message);
  }
  public static void ee(String tag, String format, Object... args) { ee(tag, String.format(format, args)); }
  public static void ee(String tag, String message) {
    internalLog(ERROR, tag, message);
  }
  public static void ee(String tag, Throwable throwable, String format, Object... args) { ee(tag, throwable, String.format(format, args)); }
  public static void ee(String tag, Throwable throwable, String message) {
    internalLog(ERROR, tag, throwable, message);
  }
  
	public static void addGlobalWarningMessage(String string) {
		ww("[GLOBAL]", string);
	}

  public static void internalLog(int priority, String tag, String message) {
	  switch (priority) {
	  case INFO:
		  System.out.println("[INFO] "+tag+" "+message);
		  break;
	  case DEBUG:
		  System.out.println("[DEBUG] "+tag+" "+message);
		  break;
	  case ERROR:
		  System.out.println("[ERROR] "+tag+" "+message);
		  break;
	  case WARN:
		  System.out.println("[WARN] "+tag+" "+message);
		  break;
	  case ASSERT:
		  System.out.println("[ASSERT] "+tag+" "+message);
		  break;
	  }
  }

  public static void internalLog(int priority, String tag, Throwable throwable, String message) {
    internalLog(priority, tag, message);
    logStackTrace(tag, throwable);
  }

  public static void logExceptionHeader(Exception e, String format, Object... args) {
    String message = String.format(format, args);
    RobotLog.e("exception %s(%s): %s [%s]", e.getClass().getSimpleName(), e.getMessage(), message, getStackTop(e));
  }

  public static void logExceptionHeader(String tag, Exception e, String format, Object... args) {
    String message = String.format(format, args);
    RobotLog.ee(tag, "exception %s(%s): %s [%s]", e.getClass().getSimpleName(), e.getMessage(), message, getStackTop(e));
  }

  private static StackTraceElement getStackTop(Exception e) {
    StackTraceElement[] frames = e.getStackTrace();
    return frames.length > 0 ? frames[0] : null;
  }

  /** @deprecated obsolete capitalization */
  @Deprecated
  public static void logStacktrace(Throwable e) {
    logStackTrace(e);
  }

  public static void logStackTrace(Throwable e) {
    logStackTrace(TAG, e);
  }

  public static void logStackTrace(Thread thread, String format, Object... args) {
    String message = String.format(format, args);
    RobotLog.e("thread id=%d tid=%d name=\"%s\" %s", thread.getId(), ThreadPool.getTID(thread), thread.getName(), message);
    logStackFrames(thread.getStackTrace());
  }

  public static void logStackTrace(Thread thread, StackTraceElement[] stackTrace) {
    RobotLog.e("thread id=%d tid=%d name=\"%s\"", thread.getId(), ThreadPool.getTID(thread), thread.getName());
    logStackFrames(stackTrace);
  }

  public static void logStackTrace(String tag, Throwable e) {
    if (e != null) {
      e.printStackTrace(LogOutputStream.printStream(tag));
    }
  }

  private static void logStackFrames(StackTraceElement[] stackTrace) {
    for (StackTraceElement frame : stackTrace) {
      RobotLog.e("    at %s", frame.toString());
    }
  }

  public static void logAndThrow(String errMsg) throws RobotCoreException {
    w(errMsg);
    throw new RobotCoreException(errMsg);
  }

  //------------------------------------------------------------------------------------------------
  // Error and Warning Messages
  //------------------------------------------------------------------------------------------------


  /**
   * Causes all calls to clearGlobalWarningMsg() to be ignored.  Use with caution.
   * @param sticky true, ignore clears, false, don't ignore.
   */
  public static void setGlobalWarningMsgSticky(boolean sticky) {
    globalWarningMsgSticky = sticky;
  }

  
  
  protected static String getStringStatic(Class clazz, String name) {
    try {
      return (String)(clazz.getField(name).get(null));
    } catch (Exception ignored) {
      return "";
    }
  }

  protected static int getIntStatic(Class clazz, String name) {
    try {
      return (clazz.getField(name).getInt(null));
    } catch (Exception ignored) {
      return 0;
    }
  }

  public static void logBytes(String tag, String caption, byte[] data, int cb) {
    logBytes(tag, caption, data, 0, cb);
  }



  public static void logBytes(String tag, String caption, byte[] data, int ibStart, int cb) {
    int cbLine = 16;
    char separator = ':';
    for (int ibFirst = ibStart; ibFirst < cb; ibFirst += cbLine) {
      StringBuilder line = new StringBuilder();
      for (int i = 0; i < cbLine; i++) {
        int ib = i + ibFirst;
        if (ib >= cb)
            break;
        line.append(String.format("%02x ", data[ib]));
      }
      vv(tag, "%s%c %s", caption, separator, line.toString());
      separator = '|';
    }
  }
  
  public static final int INFO=1;
  public static final int DEBUG=2;
  public static final int ERROR=3;
  public static final int WARN=4;
  public static final int VERBOSE=5;
  public static final int ASSERT=6;

}
