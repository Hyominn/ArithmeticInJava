package com.op.inheritance;

import lombok.*;

/**
 * @Author: NZY
 * @Date: 2020/3/19 17:46
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Person {
    private String Name;

    public abstract String getDescription();
    //public abstract String getParent();
}
