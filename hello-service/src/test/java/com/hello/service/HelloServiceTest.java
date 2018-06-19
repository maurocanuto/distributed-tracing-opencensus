package com.hello.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

    @InjectMocks
    private HelloService helloService;

    @Test
    public void printHelloTest() {
        Assert.assertEquals("Hello from Service", helloService.printHello());
    }
}
