package com.codimiracle.libs.lumehttp;

public class MultipartFormData extends FormData {
    @Override
    protected Enctype getEnctype() {
        return Enctype.MULTIPART;
    }

    @Override
    protected String getFormRequestData() {
        return null;
    }
}
