package com.ascending.training.model;

import com.amazonaws.services.directory.model.Trust;

import java.util.*;

public class test {
    public static String inverse(String test){
        int start = 0;
        char[] ch = test.toCharArray();
        for(int i = 0; i < test.length(); i++){
            if(ch[i] == ' ') {
                helper(ch, start, i - 1);
                start = i+1;
            }
        }
        if(start != 0){
            helper(ch, start, test.length() - 1);
        }
       helper(ch, 0, test.length() - 1);
        String answer = new String(ch);
        return answer;

    }

    public static void helper(char[] ch, int start, int end){
        int count = 0;
        for(int i = 0; i < (end - start)/2 + 1; i++) {
            char temp = ch[end - count];
            ch[end - count] = ch[start + count];
            ch[start + count] = temp;
            count++;
        }
    }

    public static void main(String[] args){
        String temp = "Im love";
        String answer = inverse(temp);

        System.out.print(answer);
    }
}
