/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readers;

import java.util.regex.Pattern;

/**
 *
 * @author cvtlab6
 */
public class PasswordEmailReader {
    public static String read(String data){
        return data.split(Pattern.quote("Operation"))[0];
    }
}
