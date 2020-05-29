package com.op.arithmetic;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Author: NZY
 * @Date: 2020/5/25 16:35
 * 运用你所掌握的数据结构，设计和实现一个  LRU (最近最少使用) 缓存机制。它应该支持以下操作： 获取数据 get 和 写入数据 put 。
 * 获取数据 get(key) - 如果关键字 (key) 存在于缓存中，则获取关键字的值（总是正数），否则返回 -1。
 * 写入数据 put(key, value) - 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字/值」。当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 */
public class LRUCache {
    // 双向链表+HashMap
    Map<Integer, Integer> map;
    Stack<Integer> stack;
    int size;

    public LRUCache(int capacity) {
        stack = new Stack<>();
        map = new HashMap<>(capacity);
        size = capacity;
    }

    public int get(int key) {
        if (!stack.contains(key)) {
            return -1;
        }
        boolean flag = stack.remove(Integer.valueOf(key));
        stack.push(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if (stack.contains(key)) {
            stack.remove(Integer.valueOf(key));
        } else {
            if (stack.size() == size) {
                int count = stack.remove(0);
                map.remove(count);
            }
        }
        stack.push(key);
        map.put(key, value);
    }
}

