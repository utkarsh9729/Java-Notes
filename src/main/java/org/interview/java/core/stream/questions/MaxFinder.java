package org.interview.java.core.stream.questions;


import org.interview.java.core.stream.questions.models.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
*
* Finding the Max Object with Custom Comparator

Task: Given a List<Transaction> (id, value, date), find the transaction with the highest value.

Constraint: Do not use max(Comparator). Use reduce explicitly to find the maximum element.
*
* */
public class MaxFinder {

    public static void main(String[] args) {

        List<Transaction> transactions = Arrays.asList(new Transaction(1,10,"abnc"),new Transaction(2,20,"abcd"),new Transaction(3,1212,"xyz"));

        Comparator<Transaction> comparator = Comparator.comparingInt(Transaction::getValue);

        System.out.println(transactions.stream().max(comparator).map(e -> e.getValue()).orElse(-1));

//         Using reduce
        transactions.stream().reduce((x,y)-> x.getValue() > y.getValue()? x:y).ifPresent(System.out::println);
    }
}
