package net.tajzich.raspberry.demo.exception

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class NoSuchPinException extends CoreException {

    NoSuchPinException(int number) {
        super("Device doesn't have pin for $number!")
    }
}
