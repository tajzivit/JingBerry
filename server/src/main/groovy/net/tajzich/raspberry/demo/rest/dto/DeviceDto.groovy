package net.tajzich.raspberry.demo.rest.dto

import net.tajzich.raspberry.demo.domain.Device

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class DeviceDto {

    String name
    List<PeripheryDto> peripherals = []
    List<PinDto> pins = []

    DeviceDto() {
    }

    DeviceDto(Device domain) {

        this.name = domain.name

        domain.peripherals.each {
            peripherals << new PeripheryDto(it)
        }
    }
}
