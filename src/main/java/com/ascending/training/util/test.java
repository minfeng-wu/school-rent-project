package com.ascending.training.util;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;



public class test {
        public static int helper( ArrayList<ArrayList<Double>> input){
            double k = (input.get(1).get(1) - input.get(input.size() -1).get(1))/
                    (input.get(1).get(0) - input.get(input.size() -1).get(0));
            double b = input.get(1).get(1) - k*input.get(1).get(0);

            if(input.get(0).get(1) == input.get(0).get(0)*k + b){
                return 1;
            }
            else{
                return-1;
            }
        }

        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            ArrayList<ArrayList<Double>> input = new ArrayList<>();
            while(sc.hasNext()){
                ArrayList<Double> single = new ArrayList<>();
                single.add(sc.nextDouble());
                single.add(sc.nextDouble());
                input.add(single);
            }
            System.out.println(input);
        }

}
