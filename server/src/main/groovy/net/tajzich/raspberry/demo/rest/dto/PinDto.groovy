package net.tajzich.raspberry.demo.rest.dto

import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.domain.Pin

@CompileStatic
class PinDto {

    int number
    String name
    boolean high

    PinDto() {
    }

    PinDto(Pin domain) {

        this.number = domain.number
        this.name = domain.name
        this.high = domain.isHigh()
    }

    boolean isHigh() {
        return high
    }
}
