package com.example.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Scanner sc = new Scanner(System.in);
        // main에서 사용
        long result = 0;

        String operator = "";
        boolean switchFlag = true;
        String token = "";

        while (!(token.equals("4") || token.equals("q") || token.equals("Q") || token.equals("exit"))) {
            calculator.menu();
            // 계산결과 초기화
            result = 0;
            // token값을 이용한 switch문
            switch (token = sc.next()) {
                case "1":       // 계산
                    System.out.println("1. 계산을 선택하셨습니다!");
                    // 두 개의 양의 정수 입력
                    calculator.setCalcParams();
                    // long -> String 버퍼 비우기
                    sc.nextLine();
                    // switchFlag 초기화
                    switchFlag = true;
                    while (switchFlag) {
                        System.out.println("+, -, *, /, % 중 사용할 연산자를 입력해주세요!");
                        switch (operator = calculator.inputOperator()) {  // 연산자 입력
                            case "+": // 더하기
                                result = calculator.add();
                                switchFlag = false;
                                break;
                            case "-": // 빼기
                                result = calculator.subtract();
                                switchFlag = false;
                                break;
                            case "*": // 곱하기
                                result = calculator.multiply();
                                switchFlag = false;
                                break;
                            case "/": // 나누기(몫)
                                result = calculator.divide();
                                switchFlag = false;
                                break;
                            case "%": // 나누기(나머지)
                                result = calculator.mod();
                                switchFlag = false;
                                break;
                            case "^":
                                // TODO 제곱
                                switchFlag = false;
                                break;
                            default:
                                System.out.println("다시 입력해주세요.");
                        }
                    }
                    // 계산 결과 확인
                    calculator.printResult(operator, result);
                    break;

                case "2":       // 계산 기록
                    System.out.println("2. 계산 기록보기를 선택하셨습니다!");
                    String[] interimCalcResult = calculator.getInterimCalcResult();
                    for (int i = 0; i < calculator.getCount(); i++) {
                        System.out.println((i + 1) + "번째 결과 : " + interimCalcResult[i]);
                    }
                    break;
                case "3":
                case "4":
                case "exit":    // 종료
                case "q":       // 종료
                case "Q":       // 종료
                    System.out.println("종료를 선택하셨습니다!");
                    break;
                default:
                    System.out.println("다시 입력해주세요");
                    break;
            }

        }
    }
}
