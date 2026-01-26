package org.interview.java.core.stream.questions;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class Reverser implements Collector<String,StringBuilder,String> {

    @Override
    public Supplier<StringBuilder> supplier() {
        return StringBuilder::new;
    }

    @Override
    public BiConsumer<StringBuilder, String> accumulator() {
        return (sb,s) -> {
            if(sb.length()>0){
                sb.append("|");
            }
            sb.append(new StringBuilder(s).reverse());
        };
    }

    @Override
    public BinaryOperator<StringBuilder> combiner() {
        return (sb,sb1)->
        {
            if(sb.length()>0 && sb1.length()>=0){
                return sb.append("|").append(sb1);
            }
            return sb.append(sb1);
        };
    }

    @Override
    public Function<StringBuilder, String> finisher() {

         return StringBuilder::toString;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }
}
