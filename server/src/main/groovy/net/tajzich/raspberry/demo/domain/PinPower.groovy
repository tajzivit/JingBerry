package net.tajzich.raspberry.demo.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

import java.nio.ByteBuffer

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/9/13
 */
class PinPower {

    private static final Logger LOG = LoggerFactory.getLogger(PinPower)

    static int getPinPower(int number) {

        int power = 1

        if(number == 0) {
            return power
        }

        (1..number).each {
            power = power * 2
        }

        return power
    }

    static int getValue(List<Bit> bits) {

        int result = 0

        bits.findAll{it.set}.each {
            result += getPinPower(it.number)
        }

        LOG.info("Value of bits: $bits is $result")

        return result
    }

    static byte getValueInByte(List<Bit> bits) {

        if(bits.size() > 8) {
            throw new IllegalArgumentException("Cannot convert more than 8 bits into byte!")
        }

        return (byte)getValue(bits)
    }
}
