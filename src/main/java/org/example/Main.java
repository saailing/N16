package org.example;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // 缺陷1：潜在的 NullPointerException（Coverity 会标记 NULL_DEREFERENCE）
        String str = getNullableString();
        System.out.println(str.length());

        // 缺陷2：资源未关闭（Coverity 会标记 RESOURCE_LEAK）
        try {
            FileInputStream fis = new FileInputStream("missing.txt");
            int data = fis.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 缺陷3：不安全的对象比较（Coverity 会标记 EQUALS_INEFFICIENT）
        Object a = new Object();
        Object b = new Object();
        if (a.equals(b)) {
            System.out.println("Equal");
        }
    }

    // 可能返回 null 的方法
    private static String getNullableString() {
        return Math.random() > 0.5 ? "Hello" : null;
    }
}
