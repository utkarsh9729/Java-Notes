package org.interview.java.core.stream.questions;


/*
*
* Custom Functional Interface & Exception Handling Streams don't handle checked exceptions well.

Task: Define a custom functional interface ThrowingFunction<T, R, E extends Exception> that can throw an exception.

Write a wrapper method wrap(ThrowingFunction<T, R, E> f) that returns a standard Function<T, R> and wraps any exception in a RuntimeException.

Usage: Stream a list of file paths (Strings) and use your wrapper to map them to FileInputStream objects (which throws FileNotFoundException) without a try-catch block inside the lambda.
*
* */
public class ExceptionHandlerUsingStream {

    public static void main(String[] args) {

    }
}
