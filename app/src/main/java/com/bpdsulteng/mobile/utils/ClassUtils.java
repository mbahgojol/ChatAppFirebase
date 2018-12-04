package com.bpdsulteng.mobile.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by knalb on 04/10/18.
 * Email : profghozimahdi@gmail.com
 * No Tpln : 0857-4124-4919
 * Profesi : Android Developer
 */
final public class ClassUtils {
    public static String convertToString(InputStream inputStream) {
        StringBuffer string = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                string.append(line + "\n");
            }
        } catch (IOException e) {
        }
        return string.toString();
    }
}
