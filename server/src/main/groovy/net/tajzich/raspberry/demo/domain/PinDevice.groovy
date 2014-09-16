package net.tajzich.raspberry.demo.domain

import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.exception.NoSuchPinException


public interface PinDevice {

    /**
    * @param number number of pin
    * @return pin for given number
    * @throws net.tajzich.raspberry.demo.exception.NoSuchPinException if pin for given number is not found!
    */
    Pin getPin(int number) throws NoSuchPinException

    /**
     * @return list of device's pins
     */
    List<Pin> getPins()
}