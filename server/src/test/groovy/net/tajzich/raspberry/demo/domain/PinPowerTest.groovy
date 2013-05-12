package net.tajzich.raspberry.demo.domain

import org.junit.Before
import org.junit.Test

import java.nio.ByteBuffer

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/9/13
 */
class PinPowerTest {

    PinPower power

    @Before
    void setUp() {

        //128 64 32 16 8 4 2 1
        power = new PinPower()
    }

    @Test
    void testGetPinPower1() {

        int power = power.getPinPower(0)

        assert power == 1
    }

    @Test
    void testGetPinPower2() {

        int power = power.getPinPower(1)

        assert power == 2
    }

    @Test
    void testGetPinPower4() {

        int power = power.getPinPower(2)

        assert power == 4
    }

    @Test
    void testGetPinPower8() {

        int power = power.getPinPower(3)

        assert power == 8
    }

    @Test
    void testGetPinPower16() {

        int power = power.getPinPower(4)

        assert power == 16
    }

    @Test
    void testGetPinPower32() {

        int power = power.getPinPower(5)

        assert power == 32
    }

    @Test
    void testGetPinPower64() {

        int power = power.getPinPower(6)

        assert power == 64
    }

    @Test
    void testGetPinPower128() {

        int power = power.getPinPower(7)

        assert power == 128
    }

    @Test
    void testGetValueInByte_Bit0Set() {

        def bits = createByte()

        bits.first().set = true

        byte expected = 1

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit1Set() {

        def bits = createByte()

        bits[1].set = true

        byte expected = 2

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit2Set() {

        def bits = createByte()

        bits[2].set = true

        byte expected = 4

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit3Set() {

        def bits = createByte()

        bits[3].set = true

        byte expected = 8

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit4Set() {

        def bits = createByte()

        bits[4].set = true

        byte expected = 16

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit5Set() {

        def bits = createByte()

        bits[5].set = true

        byte expected = 32

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Bit6Set() {

        def bits = createByte()

        bits[6].set = true

        byte expected = 64

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByteLastBitSet() {

        def bits = createByte()

        bits.last().set = true

        byte expected = 128

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum12() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true

        byte expected = 3

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum123() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true

        byte expected = 7

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum1234() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true
        bits[3].set = true

        byte expected = 15

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum12345() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true
        bits[3].set = true
        bits[4].set = true

        byte expected = 31

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum123456() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true
        bits[3].set = true
        bits[4].set = true
        bits[5].set = true

        byte expected = 63

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum1234567() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true
        bits[3].set = true
        bits[4].set = true
        bits[5].set = true
        bits[6].set = true

        byte expected = 127

        assert expected == PinPower.getValueInByte(bits)
    }

    @Test
    void testGetValueInByte_Sum12345678() {

        def bits = createByte()

        bits[0].set = true
        bits[1].set = true
        bits[2].set = true
        bits[3].set = true
        bits[4].set = true
        bits[5].set = true
        bits[6].set = true
        bits[7].set = true

        byte expected = 255

        assert expected == PinPower.getValueInByte(bits)
    }

    private List<Bit> createByte() {

        List<Bit> bits = []

        8.times {
            bits << new Bit(it, false)
        }

        return bits
    }
}
