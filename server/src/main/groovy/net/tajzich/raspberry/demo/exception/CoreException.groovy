package net.tajzich.raspberry.demo.exception

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class CoreException extends RuntimeException {

    CoreException() {
    }

    CoreException(String s) {
        super(s)
    }

    CoreException(String s, Throwable throwable) {
        super(s, throwable)
    }

    CoreException(Throwable throwable) {
        super(throwable)
    }

    CoreException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1)
    }
}
