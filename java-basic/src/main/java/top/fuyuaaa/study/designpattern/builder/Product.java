package top.fuyuaaa.study.designpattern.builder;

import lombok.Getter;
import lombok.Setter;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-07 14:59
 */
@Getter
@Setter
@SuppressWarnings("all")
public class Product {
    private String buildA;
    private String buildB;
    private String buildC;
    private String buildD;
    @Override
    public String toString() {
        return buildA+"\n"+buildB+"\n"+buildC+"\n"+buildD+"\n"+"房子验收完成";
    }
}
