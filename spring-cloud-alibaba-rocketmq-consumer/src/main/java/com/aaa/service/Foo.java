package com.aaa.service;

/**
 * description: 描述
 *
 * @author 田留振(liuzhen.tian @ haoxiaec.com)
 * @version 1.0
 * @date 2019/11/6
 */
public class Foo {

    private int id;

    private String bar;

    public Foo() {
    }

    public Foo(int id, String bar) {
        this.id = id;
        this.bar = bar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBar() {
        return bar;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    @Override
    public String toString() {
        return "Foo{" + "id=" + id + ", bar='" + bar + '\'' + '}';
    }

}
