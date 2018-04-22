package com.tifone.unit_test;

import com.tifone.unit_test.testcase.Calculator;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;

import java.util.Calendar;

import static org.mockito.AdditionalMatchers.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by tongkao.chen on 2018/3/21.
 */

public class CalculatorTest {

    /**
     * @Mock注解
     * 1. 简化初始化的代码
     * 2. 提供程序的可读性
     * 3. 验证错误更容易区分，因为用字段名标识mock
     * 注意：需在Test case执行之前对MockitoAnnotations进行初识化，即MockitoAnnotations.initMock(this);
     */
    @Mock private Calculator mCalculator;

    @Captor
    private ArgumentCaptor<CalculatorCallback> callback;

    @Before
    public void setupCalculatorTest() {
        System.out.println("setupCalculatorTest");
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAdd() {
        assertEquals(3, new Calculator().add(1, 2));
        assertEquals(9, new Calculator().add(4, 5));
    }

    @Test
    public void testAdd2() {
        Calculator calculator = mock(Calculator.class);
        /**
         * 将add()方法存根；传入的参数是anyInt()参数匹配器，表示参数可以为任意int类型的值
         * 存根的方法返回5.
         */
        when(calculator.add(anyInt(), anyInt())).thenReturn(5);
        int result = calculator.add(1, 3);
        /**
         * 验证add()方法是否被调用一次
         */
        verify(calculator).add(1, 3);
        int result2 = calculator.add(1, 3);
        int result3 = calculator.add(1, 3);
        /**
         * 验证add()方法是否调用3次。
         * times(int)决定需要验证的调用次数
         * 验证调用add方法的传参要与实际调用的参数一致（类型和值都要一致）
         */
        verify(calculator, times(3)).add(1, 3);
        //assertEquals(5, calculator.add(1000,199));

        when(calculator.mov(anyInt(), anyInt())).thenReturn(5);
        calculator.mov(not(eq(1)), anyInt());
        verify(calculator).mov(0, 0);

        /**
         * 通过跑出异常的方式存储空方法
         */
        doThrow(new NullPointerException()).when(calculator).mov(1, 2);
        calculator.mov(1, 3);
    }

    @Test
    public void testAppend() {
        Calculator calculator = mock(Calculator.class);
        calculator.append("append first");
        calculator.append("append first1");

        calculator.append("append second");
        InOrder inOrder = inOrder(calculator);
        inOrder.verify(calculator).append("append first");
        inOrder.verify(calculator).append("append second");

        /**
         * 确保在模拟的过程中某些交互不会发生
         */
        calculator.append("One");
        verify(calculator).append("One");
        //确定append("Two")不会被调用
        verify(calculator, never()).append("Two");

        Calculator calculator1 = mock(Calculator.class);
        Calculator calculator2 = mock(Calculator.class);
//        calculator1.append("a");
        verifyZeroInteractions(calculator1, calculator2);

        /**
         * 找到多余的调用
         */
        calculator1.append("A");
//        calculator1.append("B");
//        verify(calculator1).append("A");
        verifyNoMoreInteractions(calculator1);

        mCalculator.append("C");
        verify(mCalculator).append("C");

        /**
         * 对连续调用的存根（迭代器模式的存根）
         * 第一次调用打印valuesA，第二次调用则抛出RuntimeException
         */
        when(mCalculator.append("IteratorA"))
                .thenReturn("valuesA")
                .thenThrow(new RuntimeException());
        System.out.println(mCalculator.append("IteratorA"));
        //System.out.println(mCalculator.append("IteratorA"));

    }

    @Test
    public void testCallback() {
        mCalculator.setCallback(new CalculatorCallback() {
            @Override
            public void start() {
                System.out.println("start -----");
            }
        });
        verify(mCalculator).setCallback(callback.capture());
        callback.getValue().start();

        ArgumentCaptor captor = ArgumentCaptor.forClass(String.class);
        Calculator calculator = mock(Calculator.class);
        calculator.append("this is an argument");
        calculator.append("this is an argument2");
        System.out.println(calculator.append("this is an argument3"));
        verify(calculator, times(3)).append((String) captor.capture());
        System.out.println(captor.getAllValues());

    }

    @Test
    public void testSpy() {
        /**
         * spy方法可以创建一个局部的mock，可以访问到calculator的真实对象
         */
        Calculator calculator = spy(new Calculator());
        assertEquals("aaa", calculator.append("aaa"));
        assertEquals("aaa - bbb", calculator.append("bbb"));

        Calculator calculator2 = mock(Calculator.class);
        /**
         * 这种方式也可以访问calculator的真实对象，但是需要保证相应的方法的实现是独立的，如与其他状态有关联，那么就会有问题。
         */
        when(calculator2.append2("ccc")).thenCallRealMethod();
        System.out.println(calculator2.append2("ccc"));

    }
}
