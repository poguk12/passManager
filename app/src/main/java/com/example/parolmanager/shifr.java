package com.example.parolmanager;

public class shifr {
    private final String text;
    private final int shift;

    public shifr(String text, int shift) {
        this.text = text;
        this.shift = shift;
    }

    public shifr(String text) {
        this(text, 3);
    }

    public String encrypt() {
        StringBuilder result = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) (base + (c - base + shift + 26) % 26);
            }
            result.append(c);
        }
        return result.toString();
    }
    public String decrypt() {
        return new shifr(text, 26 - (shift % 26)).encrypt();
    }

    @Override
    public String toString() {
        return encrypt();
    }
}