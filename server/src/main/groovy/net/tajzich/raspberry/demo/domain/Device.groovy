package net.tajzich.raspberry.demo.domain

import net.tajzich.raspberry.demo.exception.NoSuchPeripheryException
import net.tajzich.raspberry.demo.exception.NoSuchPinException

/**
 * Device contains peripherals, pins etc
 *
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/11/13
 */
public interface Device {

    /**
     * @return name of the device
     */
    String getName()

    /**
     * @param number number of pin
     * @return pin for given number
     * @throws NoSuchPinException if pin for given number is not found!
     */
    Pin getPin(int number) throws NoSuchPinException

    /**
     * @return list of device's pins
     */
    List<Pin> getPins()

    /**
     * @return list of connected peripherals
     */
    List<Periphery> getPeripherals()

    /**
     *
     * @param address address of periphery
     * @return periphery for given address
     * @throws NoSuchPeripheryException
     */
    Periphery getPeriphery(int address) throws NoSuchPeripheryException

    /**
     * Writes voltage to real pins
     *
     * @param pins pin list to be updated
     */
    void write(List<Pin> pins)
}
