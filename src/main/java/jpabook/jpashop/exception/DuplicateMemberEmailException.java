package jpabook.jpashop.exception;

public class DuplicateMemberEmailException extends RuntimeException {

    public DuplicateMemberEmailException() {
    }

    public DuplicateMemberEmailException(String message) {
        super(message);
    }

    public DuplicateMemberEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public DuplicateMemberEmailException(Throwable cause) {
        super(cause);
    }
}
