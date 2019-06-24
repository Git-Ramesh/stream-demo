package com.rs.app;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.function.ToIntFunction;

public class StreamDemo {
	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
		Function<Integer, ToIntFunction<Integer>> fun = t -> value -> t * value;
		numbers.stream().filter(StreamDemo::isEven).map(value -> fun.apply(2).applyAsInt(value)).forEachOrdered(StreamDemo::printIt);
	}

	private static void sleep(long timeout, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(timeout);
		} catch (InterruptedException ignore) {
		}
	}

	private static void printIt(Object obj) {
		System.out.println("printIt: (" + obj + ") " + Thread.currentThread());
		System.out.println(obj);
	}

	private static Integer doubleIt(Integer number) {
		sleep(2, TimeUnit.SECONDS);
		System.out.println("doubleIt: (" + number + ") " + Thread.currentThread());
		return number * 2;
	}

	private static boolean isEven(Integer num) {
		System.out.println("isEven: (" + num + ") " + Thread.currentThread());
		return num % 2 == 0;
	}

}
