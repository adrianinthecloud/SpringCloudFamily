package com.osfocus.springcloud.userconsumer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

public class HystrixTest extends HystrixCommand {

    protected HystrixTest(HystrixCommandGroupKey group) {
        super(group);
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        // the program will execute a run and blocking in get(). if there was an exception occurring, then
        // it will execute the fallback method
        Future<String> futureResult = new HystrixTest(HystrixCommandGroupKey.Factory.asKey("ext")).queue();
        String result = "";
        try {
            result = futureResult.get();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("Result："+result);
    }

    @Override
    protected Object run() throws Exception {
        // TODO Auto-generated method stub
        System.out.println("business logic");
        int i = 1/0; // trigger an /zero exception
        return "ok";
    }

    @Override
    protected Object getFallback() {
        // TODO Auto-generated method stub
        return "getFallbackgetFallback";
    }



}

