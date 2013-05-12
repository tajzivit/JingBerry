package net.tajzich.raspberry.demo.domain

import net.tajzich.raspberry.demo.exception.NoSuchPinException

/**
 * Periphery interface
 *
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/11/13
 */
public interface Periphery {

    /**
     * @return name of periphery
     */
    String getName()

    /**
     * @return address of periphery
     */
    int getAddress()

    /**
     * @return list of pins
     */
    List<Pin> getPins()

    /**
     * @param number number of pin
     * @return pin for given number
     * @throws NoSuchPinException if pin for given number is not found!
     */
    Pin getPin(int number) throws NoSuchPinException

    /**
     * Writes give data to real HW
     * @param data data to be written
     */
    void write(byte data)

    /**
     * Toggle pin's voltage
     * @param pinNumber
     */
    void toggle(int pinNumber)

    /**
     * Same as (@Link Periphery.write) it byte data will be generated against pins
     * @param pins
     * @return real written data
     */
    byte setOutput(List<Pin> pins)
}
