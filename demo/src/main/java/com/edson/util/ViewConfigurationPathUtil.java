package com.edson.util;

import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class ViewConfigurationPathUtil {
    //PATHs
    public static final String TEST_ROUTINE_PATH = "demo/src/main/resources/com/edson/TestRoutine/";
    public static final String VIEW_PATH = "view/";
    public static final String FULL_VIEW_PATH = "/com/edson/view/";
    public static final String TAG_CLASS_PATH = "src/main/java/com/edson/tags/";
    public static final String TEST_ROUTINE_NAME = "modbus"; //"REF_PRODUTO_AUTOMACAO";

    //COMMUNICATION SETTINGS
    public static final String HOST_ADDRESS = "localhost";
    public static final int PORT = 34502;

    //TAG PATH
    public static final String TAG_PROJECT_PATH = "com.edson.tag.";
}

