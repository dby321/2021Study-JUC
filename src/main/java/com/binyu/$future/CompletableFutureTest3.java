package com.binyu.$future;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class CompletableFutureTest3 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<NetMall> list = new ArrayList<>();

        list.add(new NetMall("jingdong", "mysql", 12.4));
        list.add(new NetMall("dangdang", "mysql", 12.5));
        list.add(new NetMall("taobao", "mysql", 12.6));
        long startTime=System.currentTimeMillis();
        List<String> priceByCompletableFuture = getPriceByCompletableFuture(list);
        priceByCompletableFuture.forEach(one -> System.out.println(one));
        long endTime=System.currentTimeMillis();
        System.out.println(endTime-startTime);
        long startTime1=System.currentTimeMillis();
        List<String> priceList = getPrice(list);
        priceList.forEach(one-> System.out.println(one));
        long endTime1=System.currentTimeMillis();
        System.out.println(endTime1-startTime1);
    }

    private static List<String> getPrice(List<NetMall> list) {
        return list.stream().map(netMall -> String.format(netMall.getProductName() + " in %s price is %.2f", netMall.getMallName(), netMall.calPrice())
        ).collect(Collectors.toList());
    }

    private static List<String> getPriceByCompletableFuture(List<NetMall> list) {
        return list.stream().map(netMall ->
                CompletableFuture.supplyAsync(() -> String.format(netMall.getProductName() + " in %s price is %.2f", netMall.getMallName(), netMall.calPrice()))
        ).collect(Collectors.toList()).stream().map(stringCompletableFuture -> stringCompletableFuture.join()).collect(Collectors.toList());
    }
}

@Data
@AllArgsConstructor
class NetMall {
    private String mallName;
    private String productName;
    private Double price;

    public Double calPrice(){
        double v = ThreadLocalRandom.current().nextDouble();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return v;
    }
}
