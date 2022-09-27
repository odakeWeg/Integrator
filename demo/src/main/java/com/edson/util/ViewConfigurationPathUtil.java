package com.edson.util;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ViewConfigurationPathUtil {
    //PATHs
    public static final String TEST_ROUTINE_PATH = "src/main/resources/com/edson/TestRoutine/";
    public static final String VIEW_PATH = "view/";
    public static final String TAG_CLASS_PATH = "src/main/java/com/edson/tags/";

    //COMMUNICATION SETTINGS
    public static final String HOST_ADDRESS = "localhost";
    public static final int PORT = 34502;
}

