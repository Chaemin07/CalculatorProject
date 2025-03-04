package com.example.calculator;

import java.util.*;

public class Calculator {
    private int size;
    // 중간 계산값
    private long interimCalc;
    private boolean interimFlag;

    private long[] calcParams; // main에서 생성후 초기화
    // final로 참조값 수정 x
    private final Deque<String> calcLogs = new ArrayDeque<>();

    // 기본 10
    Calculator() {
        // 배열 초기화
        calcParams = new long[2];
        // 사이즈
        this.size = 10;
    }
    Calculator(int size) {
        this();
        this.size = size;
    }


    public long[] getCalcParams() {
        return calcParams;
    }

    // 계산 결과 로그 리턴
    public List<String> getCalcLogs() {
        // 수정 못하도록 복사본 리턴
        return Collections.unmodifiableList(new ArrayList<>(calcLogs));
    }

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
        while(true){
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

        result = interimCalcResult.get(idx-1);
        System.out.println("선택한 계산식은 \""+result+"\" 입니다.");
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
        System.out.println("현재 입력된 operator는 " + operator+" 입니다.");
        return operator;
    }

    // 실행 메뉴
    void menu() {
        System.out.println("🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰🟰");
        System.out.println("1\uFE0F⃣. 계산하기");
        System.out.println("2\uFE0F⃣. 계산 기록보기");
        System.out.println("3\uFE0F⃣. 계산 중간 결과값 가져오기");
        System.out.println("4\uFE0F⃣. 종료: \"exit\", \"q\",\"Q\" 입력하기");
    }
    // 계산할 매개변수 입력
    void setCalcParams() {
        Scanner sc = new Scanner(System.in);
        long calcParam = 0;
        for (int i = 0; i < 2; i++) {
            calcParam = 0;
            // TODO 중간 계산 결과값을 가져왔다면 어떻게 입력으로 가져올래?
            if (interimFlag&& (i==0)) {
                System.out.println("중간 결과값을 가져왔습니다.");
                calcParams[i] = interimCalc;
                interimFlag = false;
                continue;
            }

            System.out.println("계산할 " + (i + 1) + "번째 양의 정수를 입력해주세요.");
            // TODO 추후 String으로 입력받아 long,double로 수정
            // 정수인지 판별
            if (!sc.hasNextLong()) {
                i -= 1;
                if (i < -1) {
                    i = 0;
                }
                System.out.println("잘못된 값이 입력되었습니다!\n다시 입력해주세요.");
                // long -> String 버퍼 비우기
                sc.nextLine(); // 오류 메세지 2번 문제 체크중
            } else {
                // 양수인지 판별
                calcParam = sc.nextLong();
                System.out.println("현재 입력된 값은 " + calcParam + " 입니다.");
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

    public long getInterimCalc() {
        return interimCalc;
    }

    public void setInterimCalc(long interimCalc) {
        this.interimCalc = interimCalc;
    }

    // 입력값, 연산자, 계산 결과 확인
    void printResult(String operator, long result){
        String interimCalcResult="";
        // 연산자 입력
        for (int i = 0; i < 2; i++) {
            System.out.println((i+1)+"번째 입력된 값: "+calcParams[i]);
        }
        System.out.println("입력된 연산자: "+operator+ " 입니다.");


        // 계산 결과 저장
        if (calcParams[1] == 0 && operator.equals("/")) {
            System.out.println("결과 :  " + "❌ 0으로 나눌 수 없습니다!");
            interimCalcResult = String.valueOf(calcParams[0]) + " " + operator +
                    " " + String.valueOf(calcParams[1]) + " =  "+"❌ 0으로 나눌 수 없습니다!";
        }else {
            System.out.println("결과 :  " + result);
            interimCalcResult = String.valueOf(calcParams[0]) + " " + operator +
                    " " + String.valueOf(calcParams[1]) + " =  " + String.valueOf(result);
        }
        // 계산 결과 로그 추가
        addLog(interimCalcResult);
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
            return 0;
        }
        return calcParams[0]/calcParams[1];
    }
    // 나누기(나머지)
    long mod() {
        return calcParams[0]%calcParams[1];
    }
    // 제곱
    long power() {
        long result = 1;
        for (long i = 0; i < calcParams[1]; i++) {
            result *= calcParams[0];
        }
        return result;
    }
}
