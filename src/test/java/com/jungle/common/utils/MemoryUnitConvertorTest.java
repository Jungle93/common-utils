package com.jungle.common.utils;

import org.junit.Test;

public class MemoryUnitConvertorTest {

    @Test
    public void testOf() {

        final MemoryUnitConvertor instance = MemoryUnitConvertor.instance().setGigabytes(2);
        final String bytesString = instance.toBytesString();
        final String kilobytesString = instance.toKilobytesString();
        final String megaBytesString = instance.toMegaBytesString();
        final String gigabytesString = instance.toGigabytesString();
        final String terabytesString = instance.toTerabytesString();
    }


}