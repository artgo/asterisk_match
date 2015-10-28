package com.artemgolubev.template;

public class App {
    public static void main( String[] args ) {
        System.out.println( PatternMatcher.matches("abcdefghijklmnop", "a?c*e?**?l*???") );
    }
}
