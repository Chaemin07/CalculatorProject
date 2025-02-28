package com.example.calculator;


import java.util.Scanner;

public class Calculator {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        // 크기가 2인 long타입 배열 선언
        long[] calcParams = new long[2];
        // 중간 계산 결과, 일단 10정도만
        String[] interimCalcResult = new String[10];
        // 계산 결과
        long result = 0;
        long calcParam = 0;
        int cnt=0;
        boolean switchFlag = true;
        String token = "";
        String operator = "";


        while (!(token.equals("q") || token.equals("Q") || token.equals("exit"))){
            System.out.println("==============================================");
            System.out.println("1. 계산하기");
            System.out.println("2. 계산 기록보기");
            // TODO 이전 계산기록 가져오기
            System.out.println("3. 계산 기록 가져오기");
            System.out.println("4. 종료: \"exit\", \"q\",\"Q\" 입력하기");
            result = 0;
            // token값을 이용한 switch문
            switch (token = sc.next()){
                case "1":       // 계산
                    System.out.println("1. 계산을 선택하셨습니다!");
                    // 두 개의 양의 정수 입력
                    for (int i = 0; i < 2; i++) {
                        System.out.println("계산할 "+(i+1)+"번째 양의 정수를 입력해주세요");
                        // 추후 String으로 입력받아 long,double로 수정
                        // 정수인지 판별
                        if (!sc.hasNextLong()) {
                            i-=1;
                            if(i<-1){
                                i=0;
                            }
                            System.out.println("잘못된 값이 입력되었습니다!\n다시 입력해주세요!");
                            // long -> String 버퍼 비우기
                            sc.nextLine();
                        }else{
                            // 양수인지 판별
                            calcParam = sc.nextLong();
                            System.out.println("현재 입력된 값은 " + calcParam);
                            if(calcParam>=0){
                                calcParams[i] = calcParam;
                            }else{
                                i-=1;
                                if(i<-1){
                                    i=0;
                                }
                                System.out.println("음수가 입력되었습니다!\n다시 입력해주세요!");
                            }
                        }

                    }
                    System.out.println("사용할 연산자를 입력해주세요!");
                    // long -> String 버퍼 비우기
                    sc.nextLine();

                    // switchFlag 초기화
                    switchFlag = true;
                    while(switchFlag){
                        switch (operator=inputOperator()) {
                            case "+": // 더하기
                                result = calcParams[0] + calcParams[1];
                                switchFlag = false;
                                break;
                            case "-": // 빼기
                                result = calcParams[0] - calcParams[1];
                                switchFlag = false;
                                break;
                            case "*": // 곱하기
                                result = calcParams[0] * calcParams[1];
                                switchFlag = false;
                                break;
                            case "/": // 나누기(몫)
                                if (calcParams[1] == 0) {
                                    System.out.println("0으로 나눌 수 없습니다");
                                    switchFlag = false;
                                    break;
                                }
                                result = calcParams[0] / calcParams[1];
                                switchFlag = false;
                                break;
                            case "%": // 나누기(나머지)
                                result = calcParams[0] % calcParams[1];
                                switchFlag = false;
                                break;
                            default:
                                System.out.println("다시 입력해주세요.");
                        }
                    }

                    // 연산자 입력
                    for (int i = 0; i < 2; i++) {
                        System.out.println((i+1)+"번째 입력된 값: "+calcParams[i]);
                    }
                    System.out.println("입력된 연산자: "+operator);

                    System.out.println("결과 :  " + result);
                    interimCalcResult[cnt++] = String.valueOf(calcParams[0]) + " " + operator +
                            " " + String.valueOf(calcParams[1]) + " =  " + String.valueOf(result);
                    break;

                case "2":       // 계산 기록
                    System.out.println("2. 계산 기록보기를 선택하셨습니다!");
                    for(int i=0; i<cnt; i++){
                        System.out.println((i+1)+"번째 결과 : "+interimCalcResult[i]);
                    }
                    break;
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
    // switch문 연산자 입력
    public static String inputOperator() {
        Scanner sc = new Scanner(System.in);
        String operator = "";
        operator = sc.next(); // 엔터까지 전부 처리?
        System.out.println("현재 입력된 operator는 " + operator);
        // 현재 버퍼에는?
        String currentBuffer = sc.nextLine();
        System.out.println("현재 버퍼 내용 : " + currentBuffer);
        System.out.println("현재 버퍼 크기 : " + currentBuffer.length());
        return operator;
    }
    // TODO n번째 interimCalcResult 파라미터로 가져와 '='로 StringTokenizer
}
