package com.back;

public class Calc {

  public static int run(String calcStr) {
    return calculate(calcStr.replace(" ", ""));
  }

  private static int calculate(String calcStr) {
    // 제일 안쪽 괄호 부터 계산 -> 괄호 없는 일반식 만들기 위함.
    int openIndex = indexOfOpen(calcStr);
    if (openIndex != -1) {
      int closeIndex = calcStr.indexOf(")");

      String front = calcStr.substring(0, openIndex);
      int num = calculate(calcStr.substring(openIndex + 1, closeIndex));
      String back = calcStr.substring(closeIndex + 1);

      System.out.println(front + num + back);

      return calculate(front + num + back);
    }

    // + - 기준으로 전부 분리해서 * 먼저 연산 수행되게끔.
    // 앞에서 부터 계산 되게 하기 위해 역탐색
    int calcStrLen = calcStr.length();
    for (int i = calcStrLen - 1; i >= 1; i--) {
      char c = calcStr.charAt(i);
      if (c == '+' || c == '-') {
        // 부호 앞이 숫자인지 확인
        char prev = calcStr.charAt(i - 1);
        if (Character.isDigit(prev)) {
          int left = calculate(calcStr.substring(0, i));
          int right = calculate(calcStr.substring(i + 1));
          if (c == '-') {
            right = -right;
          }

          return left + right;
        }
      }
    }

    // * 연산 수행하여 위로 보내기
    for (int i = calcStrLen - 1; i >= 0; i--) {
      if (calcStr.charAt(i) == '*') {
        return calculate(calcStr.substring(0, i)) * calculate(calcStr.substring(i + 1));
      }
    }

    // 숫자만 남음
    return Integer.parseInt(calcStr);
  }

  private static int indexOfOpen(String calcStr) {
    int openIndex = -1;
    for (int i = 0; i < calcStr.length(); i++) {
      char c = calcStr.charAt(i);
      if (c == '(') {
        openIndex = i;
      } else if (c == ')') {
        break;
      }
    }
    return openIndex;
  }
}
