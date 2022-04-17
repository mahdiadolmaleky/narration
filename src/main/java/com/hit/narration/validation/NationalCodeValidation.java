package com.hit.narration.validation;

import java.util.stream.IntStream;

public class NationalCodeValidation {

    public static boolean isValid(String input) {
        if (!input.matches("^\\d{10}$"))
            return false;

        int check = Integer.parseInt(input.substring(9, 10));

        int sum = IntStream.range(0, 9)
            .map(x -> Integer.parseInt(input.substring(x, x + 1)) * (10 - x))
            .sum() % 11;

        return (sum < 2 && check == sum) || (sum >= 2 && check + sum == 11);
    }

    public static void test() {
        System.out.println(isValid("0075661713"));
        System.out.println(isValid("0075661714"));
    }

}
