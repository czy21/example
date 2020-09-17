package com.team.application;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.UUID;

public class UUIDTest {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();

        System.out.print(uuid);
    }

    @Test
    public void bigDecimalTest() {
        BigDecimal ret1 = new BigDecimal("0.5");
        System.out.println("s");
    }

}
