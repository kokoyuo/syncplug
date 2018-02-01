package com.syncplug.beantrans;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/1/11.
 */


@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface TransFiled {
    String value();
}
