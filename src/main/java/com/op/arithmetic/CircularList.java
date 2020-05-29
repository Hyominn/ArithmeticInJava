package com.op.arithmetic;

import java.util.HashSet;
import java.util.Set;

/**
 * 环形链表
 * @Author: NZY
 * @Date: 2020/5/26 10:49
 */
public class CircularList {
    public static void Main(String[] arg) {
    }

    /*
     * 给定一个链表，判断链表中是否有环。
     * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
     * 如果 pos 是 -1，则在该链表中没有环。
     */
    public int findDuplicateByDichotomy(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int num : nums) {
                if (num <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;
            }
        }
        return ans;
    }

    public boolean hasCycleByHash(ListNode head) {
        //我们遍历所有结点并在哈希表中存储每个结点的引用（或内存地址）。
        // 如果当前结点为空结点 null（即已检测到链表尾部的下一个结点），那么我们已经遍历完整个链表，并且该链表不是环形链表。
        // 如果当前结点的引用已经存在于哈希表中，那么返回 true（即该链表为环形链表）。
        // 时间复杂度：O(n)，对于含有 nn 个元素的链表，我们访问每个元素最多一次。添加一个结点到哈希表中只需要花费 O(1)的时间。
        // 空间复杂度：O(n)，空间取决于添加到哈希表中的元素数目，最多可以添加 n 个元素。
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            } else {
                nodesSeen.add(head);
            }
            head = head.next;
        }
        return false;
    }

    public boolean hasCycleByDoublePointer(ListNode head) {
        //通过使用具有 不同速度 的快、慢两个指针遍历链表，空间复杂度可以被降低至 O(1)。慢指针每次移动一步，而快指针每次移动两步。
        //如果列表中不存在环，最终快指针将会最先到达尾部，此时我们可以返回 false。
        //现在考虑一个环形链表，把慢指针和快指针想象成两个在环形赛道上跑步的运动员（分别称之为慢跑者与快跑者）。
        // 而快跑者最终一定会追上慢跑者。
        //时间复杂度：O(n)
        //空间复杂度：O(1)
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /*
    * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    * 如果 pos 是 -1，则在该链表中没有环。
     */
    public ListNode detectCycleByHash(ListNode head) {
        // 同方法 hasCycleByHash
        Set<ListNode> visited = new HashSet<ListNode>();

        ListNode node = head;
        while (node != null) {
            if (visited.contains(node)) {
                return node;
            }
            visited.add(node);
            node = node.next;
        }

        return null;
    }

    private ListNode getIntersect(ListNode head) {
        ListNode tortoise = head;
        ListNode hare = head;

        // A fast pointer will either loop around a cycle and meet the slow
        // pointer or reach the `null` at the end of a non-cyclic list.
        while (hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;
            if (tortoise == hare) {
                return tortoise;
            }
        }

        return null;
    }

    public ListNode detectCycleByFloyd (ListNode head) {
        //时间复杂度：O(n)
        //空间复杂度：O(1)
        if (head == null) {
            return null;
        }

        // If there is a cycle, the fast/slow pointers will intersect at somenode.
        // Otherwise, there is no cycle, so we cannot find an intersection to a cycle.
        // 如果列表是有环的，快指针和慢指针最后会同时指向同一个节点，因此被称为 相遇
        // 没有相遇则不存在环
        ListNode intersect = getIntersect(head);
        if (intersect == null) {
            return null;
        }

        // To find the intersection to the cycle, we have two pointers traverse at the same speed
        // -- one from the front of the list, and the other from the point of intersection.
        // 第一次相遇之后 慢指针从第一次相遇点开始 快指针从初始节点开始并速度与慢指针一致 第二次相遇点为开始入环的第一个节点
        ListNode ptr1 = head;
        ListNode ptr2 = intersect;
        while (ptr1 != ptr2) {
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return ptr1;
    }

    /*
     * * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设只有一个重复的整数，找出这个重复的数。
     * 不能更改原数组（假设数组是只读的）。
     * 只能使用额外的 O(1) 的空间。
     * 时间复杂度小于 O(n2) 。
     * 数组中只有一个重复的数字，但它可能不止重复出现一次。
     */
    public int findDuplicate(int[] nums) {
        //时间复杂度：O(n)「Floyd 判圈算法」时间复杂度为线性的时间复杂度。
        //空间复杂度：O(1) 我们只需要常数空间存放若干变量。
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    // Definition for singly-linked list.
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
