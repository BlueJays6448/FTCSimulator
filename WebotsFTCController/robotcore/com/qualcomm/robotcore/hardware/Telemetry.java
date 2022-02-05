package com.qualcomm.robotcore.hardware;

public class Telemetry {

    public static String telemetryText = "";
    public static boolean shouldUpdateTelemetry = false;

    public static void addData(String caption, Object... data) {
        telemetryText = "\n" + caption + ": " + data.toString();
    }

    /**
     * Add data for display by telemetry
     * @param caption
     * @param data  data object to display
     */
    public static void addData(String caption, Object data) {
        telemetryText = "\n" + caption + ": " + data.toString();
    }

    /**
     * Clear the telemetry display, then write any data that has been added since the previous update.
     */
    public static void update() {
        shouldUpdateTelemetry = true;
    }
}
