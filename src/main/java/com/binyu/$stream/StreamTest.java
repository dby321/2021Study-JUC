package com.binyu.$stream;

/**
 * @BelongsProject: 2021Study-JUC
 * @BelongsPackage: com.binyu.forkjoin
 * @Author: Dong Binyu
 * @CreateTime: 2021-10-26 16:02
 * @Description:
 */
import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamTest {

    public static void main(String[] args) {
        int i = Runtime.getRuntime().availableProcessors();

        // for
        System.out.println(measureSumPerf(StreamTest::iterativeSum, 10_000_000) + " msecs");
        // Stream.iterate
        System.out.println(measureSumPerf(StreamTest::sequentialSum, 10_000_000) + " msecs");
        // parallel Stream.iterate
        System.out.println(measureSumPerf(StreamTest::parallelSum, 10_000_000) + " msecs");
        // LongStream.rangeClosed
        System.out.println(measureSumPerf(StreamTest::rangedSum, 10_000_000) + " msecs");
        // parallel LongStream.rangeClosed
        System.out.println(measureSumPerf(StreamTest::parallelRangedSum, 10_000_000) + " msecs");
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .parallel()
                .reduce(0L, Long::sum);
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(0L, Long::sum);
    }


    public static long parallelSum(long n) {
        return Stream.iterate(0L, i -> i + 1)
                .limit(n)
                .parallel()
                .reduce(0L ,Long::sum);
    }


    public static long sequentialSum(long n) {
        // 生成自然数无限流
        return Stream.iterate(0L, i -> i + 1)
                // 限制到前n个数
                .limit(n)
                // 对所有数字求和来归纳流
                .reduce(0L, Long::sum);
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 1L; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long measureSumPerf(Function<Long, Long> adder, long n) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            long sum = adder.apply(n);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) {fastest = duration;}
        }
        return fastest;
    }

}
