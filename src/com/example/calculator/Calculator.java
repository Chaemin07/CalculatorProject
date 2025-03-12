package com.example.calculator;

import java.util.*;

public class Calculator {
    private int size;
    // 중간 계산값
    long interimCalc;
    boolean interimFlag;
    // 계산 파라미터
    private long calcParam1;
    private long calcParam2;
    // final로 참조값 수정 x
    private final Deque<String> calcLogs = new ArrayDeque<>();

    // 기본 10
    Calculator() {
        // 사이즈
        this.size = 10;
    }

    Calculator(int size) {
        this();
        this.size = size;
    }

    // 계산 결과 로그 리턴
    public List<String> getCalcLogs() {
        // 수정 못하도록 복사본 리턴
        return Collections.unmodifiableList(new ArrayList<>(calcLogs));
    }
    // 계산 로그 입력
    public void addLog(String interimCalcResult) {
        calcLogs.addLast(interimCalcResult);

        // 로그 최대 개수 제한
        if (calcLogs.size() > this.size) {
            calcLogs.removeFirst();
        }
    }

    // 중간 계산값 가져오기
    String interimCalc() {

        List<String> interimCalcResult = getCalcLogs();
        String result = "";
        int idx = 0;
        int size = interimCalcResult.size();

        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < size; i++) {
            System.out.println((i + 1) + "번째 결과 : " + interimCalcResult.get(i));
        }
        while (true) {
            // 인덱스는 0부터 시작
            System.out.println("가져올 수식의 인덱스 번호를 선택해 주세요.");
            try {
                idx = sc.nextInt();
                if (idx <= 0) {
                    System.out.println("1 ~ " + size + "범위의 정수를 입력해 주세요!");
                    continue;
                }
                break;

            } catch (Exception e) {
                System.out.println("범위 안의 인덱스를 입력해 주세요!");
                sc.next();
            }
        }

        result = interimCalcResult.get(idx - 1);
        System.out.println("선택한 계산식은 \"" + result + "\" 입니다.");
        StringTokenizer st = new StringTokenizer(result, "=");

        st.nextToken();
        // 연산 결과값(공백 제거)만 리턴
        return st.nextToken().trim();
    }


    // 연산자 입력
    public String inputOperator() {
        Scanner sc = new Scanner(System.in);
        String operator = "";
        operator = sc.next(); // 엔터까지 전부 처리?
        System.out.println("현재 입력된 operator는 " + operator + " 입니다.");
        return operator;
    }

    // 실행 메뉴
    void menu() {
        System.out.println("🟰".repeat(30));
        System.out.println("1\uFE0F⃣. 계산하기");
        System.out.println("2\uFE0F⃣. 계산 기록보기");
        System.out.println("3\uFE0F⃣. 계산 중간 결과값 가져오기");
        System.out.println("4\uFE0F⃣. 종료: \"exit\", \"q\",\"Q\" 입력하기");
    }

    public long getInterimCalc() {
        return interimCalc;
    }

    public void setInterimCalc(long interimCalc) {
        this.interimCalc = interimCalc;
    }

    // 계산할 매개변수 입력
    public void setCalcParams() {
        calcParam1 = setParam();
        calcParam2 = setParam();
    }
    // 중간 계산값 가져오기
    private long setParam() {
        if (interimFlag) {
            System.out.println(interimCalc+"을 불러왔습니다.");
            interimFlag = false;
            return interimCalc;
        }
        return getValidNumber();
    }
    // 유효성 검사
    private long getValidNumber() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("양의 정수를 입력해주세요.");
            if (!sc.hasNextLong()) {
                System.out.println("잘못된 값이 입력되었습니다!\n다시 입력해주세요.");
                // long -> String 버퍼 비우기
                sc.nextLine(); // 오류 메세지 2번 문제 체크중
            } else {
                // 양수인지 판별
                long param = sc.nextLong();
                if (param >= 0) {
                    System.out.println("현재 입력된 값은 " + param + " 입니다.");
                    return param;
                } else {
                    System.out.println("음수가 입력되었습니다!\n다시 입력해주세요!");
                }
            }
        }
    }

    // 연산자 유효성 검사 main에서
    Operator getValidOperator() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("+, -, *, /, %, ^ 중 사용할 연산자를 입력해주세요.");
            String op = sc.nextLine();
            Operator operator = Operator.fromSymbol(op);
            if (operator != null) {
                return operator;
            }
            System.out.println("잘못된 연산자입니다! 다시 입력해주세요!");
        }
    }

    // 계산 결과 확인
    String printResult(String operator, long result) {
        String interimCalcResult = "";
        // 연산자 입력
        System.out.println("🟰".repeat(30));
        System.out.println("1번째 입력된 값: " + calcParam1);
        System.out.println("2번째 입력된 값: " + calcParam2);
        System.out.println("입력된 연산자: " + operator + " 입니다.");
        if (operator.equals("/") && calcParam2 == 0) {
            System.out.println("결과 :  " + "❌ 0으로 나눌 수 없습니다!");
            interimCalcResult = "❌ 0으로 나눌 수 없습니다!";
            return interimCalcResult;
        }
        interimCalcResult = calcParam1 + " " + operator + " " + calcParam2 + " = " + result;
        System.out.println(interimCalcResult);
        return interimCalcResult;
    }


    // 더하기
    long add() {
        return calcParam1 + calcParam2;
    }

    // 빼기
    long subtract() {
        return calcParam1 - calcParam2;
    }

    // 곱하기
    long multiply() {
        return calcParam1 * calcParam2;
    }

    // 나누기(몫)
    long divide() {
        if (calcParam2 == 0) {
            return 0;
        }
        return calcParam1 / calcParam2;
    }

    // 나누기(나머지)
    long mod() {
        return calcParam1 % calcParam2;
    }

    // 제곱
    long power() {
        long result = 1;
        for (long i = 0; i < calcParam2; i++) {
            result *= calcParam1;
        }
        return result;
    }
}
