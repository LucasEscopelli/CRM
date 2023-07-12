package com.crm.util;

import org.apache.commons.lang3.Validate;

public class Check {

    public static void isTrue(boolean expression, String message) {
        try {
            Validate.isTrue(expression, message);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    public static <T> T notNull(T object, String message) {
        try {
            return Validate.notNull(object, message);
        } catch (Exception e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }
}
