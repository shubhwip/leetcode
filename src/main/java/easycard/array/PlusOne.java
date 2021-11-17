package easycard.array;

public class PlusOne {
    public int[] plusOne(int[] digits) {
        int carry = 0;
        int size = digits.length;
        digits[size - 1] = digits[size - 1] + 1;
        if (digits[size - 1] > 9) {
            digits[size - 1] = digits[size - 1] % 10;
            carry = 1;
        }
        for (int i = size - 2; i >= 0; i--) {
            digits[i] = digits[i] + carry;
            carry = 0;
            if (digits[i] > 9) {
                digits[i] = digits[i] % 10;
                carry = 1;
            }
        }
        if (carry > 0) {
            int[] newDigits = new int[size + 1];
            for (int i = 1; i < newDigits.length; i++) {
                newDigits[i] = digits[i - 1];
            }
            newDigits[0] = carry;
            return newDigits;

        }
        return digits;
    }
}
