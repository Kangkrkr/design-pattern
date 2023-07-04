package main;

import client.Calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("수식을 입력 해주세요 :");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        try {
            Calculator calculator = Calculator.getInstance();
            Number result = calculator.calculate(input);

            System.out.println("출력 결과 :");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
