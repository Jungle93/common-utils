package com.jungle.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 内存单位转换工具。
 *
 * @author jungle
 * @version V1.0
 * @date 2018/11/15 19:15
 * @Title: MD5Utils.java
 * @Package com.jungle.common.utils
 * @Description: MD5工具类
 * copyright © 2021- github.com
 */
public final class MemoryUnitConvertor {

    /**
     * 单位层级。
     */
    private static final BigDecimal UNIT_LEVEL_1024 = BigDecimal.valueOf(1024);
    /**
     * 字节数量。
     */
    private BigDecimal bytes;

    private MemoryUnitConvertor(long bytes) {

        this.bytes = BigDecimal.valueOf(bytes);
    }

    public static MemoryUnitConvertor instance() {

        return new MemoryUnitConvertor(0);
    }

    public MemoryUnitConvertor setBytes(long bytes) {

        this.bytes = BigDecimal.valueOf(bytes);
        return this;
    }

    public MemoryUnitConvertor setKilobytes(long kilobytes) {

        this.bytes = BigDecimal.valueOf(kilobytes)
            .multiply(UNIT_LEVEL_1024);
        return this;
    }

    public MemoryUnitConvertor setMegaBytes(long megabytes) {

        this.bytes = BigDecimal.valueOf(megabytes)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024);
        return this;
    }

    public MemoryUnitConvertor setGigabytes(long gigabytes) {

        this.bytes = BigDecimal.valueOf(gigabytes)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024);
        return this;
    }

    public MemoryUnitConvertor setTerabytes(long terabytes) {

        this.bytes = BigDecimal.valueOf(terabytes)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024)
            .multiply(UNIT_LEVEL_1024);
        return this;
    }

    public String toBytesString() {

        return this.bytes.longValue() + " Byte";
    }

    public String toKilobytesString() {

        return this.bytes
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .toPlainString()
            + " Kb";
    }

    public String toMegaBytesString() {

        return this.bytes
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .toPlainString()
            + " Mb";
    }

    public String toGigabytesString() {

        return this.bytes
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .toPlainString()
            + " Gb";
    }

    public String toTerabytesString() {

        return this.bytes
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .divide(UNIT_LEVEL_1024, 20, RoundingMode.HALF_UP)
            .toPlainString()
            + " Tb";
    }
}
