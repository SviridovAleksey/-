class MyArraySizeException extends Exception {
    protected MyArraySizeException(Throwable e) {
        initCause(e);
    }
}