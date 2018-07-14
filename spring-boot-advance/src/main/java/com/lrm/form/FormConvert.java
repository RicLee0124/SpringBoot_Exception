package com.lrm.form;

public interface FormConvert<S,T> {
    T convert(S s);
}
