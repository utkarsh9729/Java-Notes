package org.interview.java.core.functionalInterface;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class SupplierExample {


    public static void main(String[] args) {



        Supplier<Double> ceilSupplier = Math::random;
        System.out.println(ceilSupplier.get());

    }
}
