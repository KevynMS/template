package com.flakkeeverhuizers.controller.common;

import lombok.Value;

@Value
public class GlobalErrorResponse {
    String type;
    String message;
}
