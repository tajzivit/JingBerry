package net.tajzich.raspberry.demo.domain

import groovy.transform.CompileStatic

@CompileStatic
class Bit {

    int number
    boolean set

    Bit(int number, boolean set) {
        this.number = number
        this.set = set
    }

    @Override
    String toString() {
        return "number: $number, set: $set"
    }
}
