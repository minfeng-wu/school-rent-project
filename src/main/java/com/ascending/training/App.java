package com.ascending.training;

import org.apache.commons.lang3.StringUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public void isInputStringANumeric() {
        String text1 = "012abc5";
        String text2 = "012345";
        boolean result1 = StringUtils.isNumeric(text1);
        boolean result2 = StringUtils.isNumeric(text2);
        System.out.println((text1 + " is a numeric? " + result1));
        System.out.println((text2 + " is a numeric? " + result2));
//        logger.info((text1 + " is a numeric? " + result1));
//        logger.info((text2 + " is a numeric? " + result2));
    }
//    white_check_mark
//            eyes
//    raised_hands






    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
