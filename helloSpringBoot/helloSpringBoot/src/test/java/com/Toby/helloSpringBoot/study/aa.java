package com.Toby.helloSpringBoot.study;

interface MathOperation {
    int operate(int a, int b);
}

public class aa {
    public static int add(int a, int b) {
        return a + b;
    }

    public static void main(String[] args) {
        MathOperation operation = aa::add; // 정적 메서드 참조
        int result = operation.operate(5, 3);
        System.out.println(result); // 출력: 8
    }
}