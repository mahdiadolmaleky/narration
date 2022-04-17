package com.hit.narration.validation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ShebaCodeValidation {

    static Map<String, String> banks = new HashMap<>();

    static {
        banks.put("21", "پست بانک");
        banks.put("75", "اعتباری عسگریه");
        banks.put("54", "بانک پارسيان");
        banks.put("57", "بانک پاسارگاد");
        banks.put("64", "بانک گردشگري");
        banks.put("53", "بانک کارآفرين");
        banks.put("16", "بانک کشاورزي");
        banks.put("62", "بانک آینده");
        banks.put("55", "بانک اقتصاد نوين");
        banks.put("63", "بانک انصار");
        banks.put("69", "بانک ايران زمين");
        banks.put("18", "بانک تجارت");
        banks.put("20", "بانک توسعه صادرات");
        banks.put("65", "بانک حکمت ايرانيان");
        banks.put("78", "بانک خاور میانه");
        banks.put("66", "بانک دي");
        banks.put("13", "بانک رفاه");
        banks.put("15", "بانک سپه");
        banks.put("56", "بانک سامان");
        banks.put("58", "بانک سرمايه");
        banks.put("59", "بانک سينا");
        banks.put("61", "بانک شهر");
        banks.put("19", "بانک صادرات ايران");
        banks.put("11", "بانک صنعت و معدن");
        banks.put("70", "بانک قرض الحسنه رسالت");
        banks.put("52", "بانک قوامین");
        banks.put("10", "بانک مرکزی");
        banks.put("14", "بانک مسکن");
        banks.put("95", "بانک مشترک ایران و ونزویلا");
        banks.put("12", "بانک ملت");
        banks.put("17", "بانک ملي ايران");
        banks.put("22", "توسعه تعاون");
        banks.put("93", "شاپرک");
        banks.put("60", "قرض الحسنه مهر");
        banks.put("51", "موسسه اعتباري توسعه");
        banks.put("73", "بانک سپه(کوثر سابق)");
        banks.put("80", "موسسه مالی واعتباری نور");
        banks.put("79", "بانک سپه (مهر اقتصاد سابق)");
    }

    public static boolean verifyIban(String iban) {
        if (iban.startsWith("IR") || iban.startsWith("ir")) {
            iban = iban.substring(2);
        }

        iban = numbersOf(iban);
        String cd = iban.substring(0, 2);
        iban = iban.substring(2) + "1827" + cd;
        int i = (new BigDecimal(iban)).remainder(new BigDecimal("97")).intValue();
        return i == 1;
    }

    public static String bankOf(String iban) {
        iban = numbersOf(iban);
        String cd = iban.substring(2, 5);
        return banks.get(String.valueOf(Integer.parseInt(cd)));
    }

    public static String numbersOf(String text) {
        return text.replaceAll("[^\\d]", "");
    }

    public static void test() {
        System.out.println(verifyIban("ir240730000003179111165449"));
        System.out.println(bankOf("ir240730000003179111165449"));
        System.out.println(verifyIban("ir240730000003179111165445"));
    }

}
