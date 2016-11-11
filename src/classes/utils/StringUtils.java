/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.utils;

/**
 *
 * @author rnexer
 */
public class StringUtils {

    public static String removeStringBeforeCharacter(String textLine, char character) {
        String result = "";
        int len = textLine.length();
        boolean foundSpace = false;
        for (int i = 0; i < len; i++) {
            if (!foundSpace && textLine.charAt(i) == character) {
                foundSpace = true;
            } else if (foundSpace) {
                result += textLine.charAt(i);
            }
        }
        return result;
    }

    public static boolean isOneCharacterLine(String textLine, char character) {
        int len = textLine.length();
        for (int i = 1; i < len; i++) {
            if (textLine.charAt(i) != character) {
                return false;
            }
        }
        return true;
    }

    public static String removeContinuesCharacters(String textLine, char character) {
        String text = "";
        int len = textLine.length();
        for (int i = 0; i < len; i++) {
            if (textLine.charAt(i) == character) {
                if (!((i < len - 1 && textLine.charAt(i + 1) == character) || (text.length() == 0))) {
                    text += textLine.charAt(i);
                }
            } else {
                text += textLine.charAt(i);
            }
        }
        return text;
    }
    
}
