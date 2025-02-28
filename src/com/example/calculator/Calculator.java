package com.example.calculator;


import java.io.BufferedReader;
import java.util.Scanner;

public class Calculator {
    private int count;

    public String[] getInterimCalcResult() {
        return interimCalcResult;
    }

    public int getCount() {
        return count;
    }

    private String[] interimCalcResult; // 중간 계산 결과 → static이여야 할까?
    private long[] calcParams; // main에서 생성후 초기화


    Calculator() {
        calcParams = new long[2];
        // 사이즈
        interimCalcResult = new String[10];
    }


    public long[] getCalcParams() {
        return calcParams;
    }

    public String inputOperator() {
        Scanner sc = new Scanner(System.in);
        String operator = "";
        operator = sc.next(); // 엔터까지 전부 처리?
        System.out.println("현재 입력된 operator는 " + operator);
        // 삭제 예정 :현재 버퍼에는?
        String currentBuffer = sc.nextLine();
        System.out.println("현재 버퍼 내용 : " + currentBuffer);
        System.out.println("현재 버퍼 크기 : " + currentBuffer.length());

        return operator;
    }

    // 실행 메뉴
    void menu() {
        System.out.println("==============================================");
        System.out.println("1. 계산하기");
        System.out.println("2. 계산 기록보기");
        System.out.println("3. 계산 기록 가져오기");
        System.out.println("4. 종료: \"exit\", \"q\",\"Q\" 입력하기");
    }
    void setCalcParams() {
        Scanner sc = new Scanner(System.in);
        long calcParam = 0;
        for (int i = 0; i < 2; i++) {
            calcParam = 0;
            System.out.println("계산할 " + (i + 1) + "번째 양의 정수를 입력해주세요");
            // TODO 추후 String으로 입력받아 long,double로 수정
            // 정수인지 판별
            if (!sc.hasNextLong()) {
                i -= 1;
                if (i < -1) {
                    i = 0;
                }
                System.out.println("잘못된 값이 입력되었습니다!\n다시 입력해주세요!");
                // long -> String 버퍼 비우기
                sc.nextLine(); // 오류 메세지 2번 문제 체크중
            } else {
                // 양수인지 판별
                calcParam = sc.nextLong();
                System.out.println("현재 입력된 값은 " + calcParam);
                if (calcParam >= 0) {
                    calcParams[i] = calcParam;
                } else {
                    i -= 1;
                    if (i < -1) {
                        i = 0;
                    }
                    System.out.println("음수가 입력되었습니다!\n다시 입력해주세요!");
                }
            }

        }
        this.calcParams = calcParams;
    }
    // 입력값, 연산자, 계산 결과 확인
    void printResult(String operator, long result){
        // 연산자 입력
        for (int i = 0; i < 2; i++) {
            System.out.println((i+1)+"번째 입력된 값: "+calcParams[i]);
        }
        System.out.println("입력된 연산자: "+operator);

        System.out.println("결과 :  " + result);
        // 계산 결과 저장
        interimCalcResult[count++] = String.valueOf(calcParams[0]) + " " + operator +
                " " + String.valueOf(calcParams[1]) + " =  " + String.valueOf(result);
    }
    // 더하기
    long add() {
        return calcParams[0]+calcParams[1];
    }
    // 빼기
    long subtract() {
        return calcParams[0]-calcParams[1];
    }
    // 곱하기
    long multiply() {
        return calcParams[0]*calcParams[1];
    }
    // 나누기(몫)
    long divide() {
        if (calcParams[1] == 0) {
            System.out.println("❌ 0으로 나눌 수 없습니다!");
            return 0;
        }
        return calcParams[0]/calcParams[1];
    }
    // 나누기(나머지)
    long mod() {
        return calcParams[0]%calcParams[1];
    }
    // 제곱
    long power(long a, long b) {
        long result = 1;
        for (long i = 1; i <= b; i++) {
            result *= a;
        }
        return result;
    }
}
