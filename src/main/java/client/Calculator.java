package client;

import factory.CalculatorFactory;
import function.CalculatorFunction;

import java.util.Stack;

public class Calculator {

    private static Calculator calculator;
    private CalculatorFactory<CalculatorFunction> calculatorFactory = CalculatorFactory.getInstance();

    private Calculator() {}

    public static Calculator getInstance() {
        // 초기화를 위한 더블 체크 락킹(Double-Checked Locking)
        if (calculator == null) {
            synchronized (Calculator.class) {
                if (calculator == null) {
                    calculator = new Calculator();
                }
            }
        }

        return calculator;
    }

    private String toPostfix(String infix){

        // A * B - (C + D) + E : infix
        // AB*CD+-E+           : postfix

        // 1. 연산자 및 '(' 를 push, pop 할 스택을 만든다.
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder();

        // 2. 입력받은 문자열에서 각각의 글자 단위로 검사한다.
        for(int i=0; i<infix.length(); i++){

            // 문자열에서 한글자 가져오기.
            char c = infix.charAt(i);

            // 3. 숫자(피연산자)를 만난다면 그냥 출력한다.
            if(isNumber(c)){
                sb.append(c);
            }
            // 4. 오른쪽(닫는) 괄호가 나오면,
            // 스택이 비어있지 않는 조건한에서 왼쪽(여는) 괄호를 만날떄까지 팝하며 출력한다.
            else if(c == ')'){
                while(!stack.isEmpty() && stack.peek() != '('){
                    sb.append(stack.pop());
                }

                // 위의 while문은 '(' 전까지 팝되었으므로,
                // '(' 은 출력하지 않고 버린다.(팝을 한다.)
                stack.pop();
            }
            // 5. 그외, 왼쪽(여는) 괄호를 만났을 경우나 연산자를 만나는 경우 .
            else{
                // 여는 괄호를 만나면 스택에 푸쉬한다.
                if(c == '('){
                    stack.push('(');
                }

                // 연산자를 만난다면,
                // 스택이 비어 있지 않은 조건하에서
                // 현재 자신보다 우선순위가 높은 연산자를 팝하며 출력한다.
                if(isOperator(c)){
                    while(!stack.isEmpty() && (getPrec(c) <= getPrec(stack.peek()))){
                        sb.append(stack.pop());
                    }

                    // 그리고나서 자신을 스택에 푸쉬한다.
                    stack.push(c);
                }
            }
        }

        // 6. 순환문이 끝나고 나면, 스택이 빌때까지 팝을 하며 출력한다.
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }

    private boolean isNumber(char c){
        return (c >= '0' && c <= '9');
    }

    private boolean isOperator(char c){
        return ((c == '+') || (c == '-') || (c == '*') || (c == '/'));
    }

    // 연산자별 우선순위 체크를 하는 메소드
    private int getPrec(char c){

        if((c == '+') || (c == '-'))
            return 1;
        if((c == '*') || (c == '/'))
            return 2;
        return 0;
    }


    public Number calculate(String input) throws Exception {
        String postfix = toPostfix(input);

        Stack<Number> stack = new Stack();

        // 1. 입력받은 수식의 처음부터 끝까지 검사한다.
        for(int i=0; i<postfix.length(); i++){

            char c = postfix.charAt(i);

            // 2. 숫자라면 스택에 푸쉬한다.
            if(isNumber(c)){
                stack.push(Integer.parseInt(String.valueOf(c)));
            }
            // 3. 연산자라면 먼저, 단항연산자인지 이항연산자인지 구분한다.
            // 단항연산자라면 스택에서 하나를 팝,
            // 이항연산자라면 스택에서 두개를 팝한다.
            // 먼저 스택에서 팝된 피연산자가 이항연산자 연산시의 두번째 피연산자가 된다.
            else if(isOperator(c)){

                // 일단, 이항연산자인 것을 전제조건으로 해본다. (스택에서 피연산자 두개 팝)
                int post = Integer.parseInt(stack.pop()+"");
                int pre = Integer.parseInt(stack.pop()+"");

                CalculatorFunction calculatorFunction = calculatorFactory.createCalculatorFunction(c);
                Number result = calculatorFunction.process(pre, post);

                // 4. 계산한 결과를 스택에 푸쉬한다.
                stack.push(result);
            }
        }

        // 5. 루프문이 종료되고 난 후에는 스택에 최종결과 값이 하나 저장되어있다.
        // 그 결과값을 팝하여 결과를 보여준다.
        return Integer.parseInt(stack.pop()+"");
    }
}
