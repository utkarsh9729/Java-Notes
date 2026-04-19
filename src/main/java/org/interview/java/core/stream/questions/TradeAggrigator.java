package org.interview.java.core.stream.questions;


/*
*
* The Stock Market Aggregator
You are given a Stream of raw Trade objects representing a single day of trading.

Trade contains: ticker (String), price (double), volume (int), timestamp (long epoch millis).

Task: Build a Custom Collector to generate "Candlesticks". You need to group trades by ticker and aggregate them into a Candle object. A Candle contains:

open: Price of the trade with the earliest timestamp.

close: Price of the trade with the latest timestamp.

high: Highest price in the stream.

low: Lowest price in the stream.

totalVolume: Sum of all volumes.

Your pipeline should:

Group by ticker.

Use a custom Collector.of(...) as the downstream collector to aggregate the Trade stream into a single Candle object for that ticker.

Output: Map<String, Candle>.
*
* */
public class TradeAggrigator {

    public static void main(String[] args) {

    }
}
