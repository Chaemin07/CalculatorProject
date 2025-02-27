package com.example.calculator;


import java.util.Scanner;

public class Calculator {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        // 크기가 2인 long타입 배열 선언
        long[] calcParams = new long[2];
        // 중간 계산 결과, 저장 부족하면 늘릴 생각
        String[] interimCalcResult = new String[10];
        // 계산 결과
        long result = 0;
        int cnt=0;
        String token = "";
        String operator = "";


        while (!(token.equals("q") || token.equals("Q") || token.equals("exit"))){
            System.out.println("==============================================");
            System.out.println("1. 계산");
            System.out.println("2. 계산 기록보기");
            System.out.println("종료: \"exit\", \"q\",\"Q\" 입력하기");

            // token값을 이용한 switch문
            switch (token = sc.next()){
                case "1":       // 계산
                    System.out.println("1. 계산을 선택하셨습니다!");
                    // 두 개의 양의 정수 입력
                    for (int i = 0; i < 2; i++) {
                        System.out.println("계산할 "+i+"번째 양의 정수를 입력해주세요");
                        if (sc.hasNextLong()) {
                            calcParams[i] = sc.nextLong();
                            System.out.println("현재 입력된 값은 " + calcParams[i]);
                        } else {
                            i-=1;
                            if(i<-1){
                                i=0;
                            }
                            sc.next();
                            System.out.println("잘못된 값이 입력되었습니다! 현재 i = "+i);
                        }
                    }
                    System.out.println("사용할 연산자를 입력해주세요!");
                    // TODO operator입력 오류 버퍼 문제? #########################
                    sc.next();
                    // TODO swtich문 작성 #########################
                    switch (operator = sc.nextLine()) {
                        case "+": // 더하기
                            result = calcParams[0] + calcParams[1];
                            break;
                        case "-": // 빼기
                            result = calcParams[0] - calcParams[1];
                            break;
                        case "*": // 곱하기
                            result = calcParams[0] * calcParams[1];
                            break;
                        case "/": // 나누기 - 몫
                            result = calcParams[0] / calcParams[1];
                            break;
                        case "%": // 나누기 - 나머지
                            result = calcParams[0] % calcParams[1];
                            break;
                        default:
                            System.out.println("다시 입력해주세요.");
                            sc.next();

                    }
                    // TODO 입력 오류(유효하지 않는 값) 수정 #########################

                    // 연산자 입력
                    String calcOperator = sc.next();
                    for (int i = 0; i < 2; i++) {
                        System.out.println((i+1)+"번째 입력된 값: "+calcParams[i]);
                    }
                    System.out.println("입력된 연산자: "+calcOperator);

                    System.out.println("= " + result);
                    interimCalcResult[cnt++] = String.valueOf(calcParams[0])+" "+operator +
                            " "+String.valueOf(calcParams[1])+" =  "+String.valueOf(result);

                    System.out.println("==============================================");
                    break;

                case "2":       // 계산 기록
                    System.out.println("2. 계산 기록보기를 선택하셨습니다!");
                    for(int i=0; i<cnt; i++){
                        System.out.println(i+"번째 결과 : "+interimCalcResult[i]);
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

    public static void clear() {
        for (int i = 0; i < 17; i++) {
            System.out.println();
        }
    }
}
