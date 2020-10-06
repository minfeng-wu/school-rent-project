package com.ascending.training.init;

public class DeletLater {
    public static void reverse(char[] arr){
        int start = 0, end = 0;
        if(arr == null || arr.length == 0){
            return;
        }

        for(int i = 0; i < arr.length; i++){
            if(arr[i] == ' '){
                end = i-1;
                reverseSub(arr,start,end);
                start = i+1;
            }
        }

        reverseSub(arr, 0, arr.length - 1);
    }

    public static void reverseSub(char[]arr, int start, int end){
        while(start < end){
            swap(arr,start,end);
            start++;
            end--;
        }
    }

    public static void swap(char[]arr, int start, int end){
        char temp = arr[start];
        arr[start] = arr[end];
        arr[end] = temp;
    }

//    public static void main(String[] args){
//        String test = "This is a test!";
//        char[] charTest = test.toCharArray();
//        reverse(charTest);
//
//        System.out.print(charTest);
//    }
}
