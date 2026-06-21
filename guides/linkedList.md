---
authors:
  - Jesse
title: Linked List
status: published
source: CodeBlog
---

- 707 implement LinkedList

## 反转

prev为null

- 链表分为两部分, head代表未反转的部分, prev代表反转后的部分
- 每次将head的第一个元素取出, 放到prev的前面
- 直到head为空, prev即为反转后的链表
```JAVA
public ListNode reverseList(ListNode cur) {
  ListNode prev = null; // 反转后的head
  while (cur != null) {
      ListNode after = cur.next;

      // 链接prev和head
      cur.next = prev; 

      // 更新
      prev = cur;
      cur = after;
  }
  return prev;
}
```

prev不为null
- 不停的把cur.next插入到prev.next的前面
```java
prev = someNode;
ListNode cur = prev.next;
for (int i = 0; i < k; i++) {
    ListNode next = cur.next;
    cur.next = next.next;
    next.next = prev.next;
    prev.next = next;
}
```

特殊反转:
1. 将链表分为两部分
2. 分别处理两部分
3. 合并两部分

> **Question**
>
>
> ⭐️ 基础 BFS
>
> - 24 两两反转
> - 206 反转
> - 234 利用反转判断 palindrome
> - 86 split & reverse & merge
> - 143 split & reverse & merge
>
> ⭐️⭐️⭐️⭐️ 反转某部分
>
> - 25 ⭐️⭐️⭐️⭐️ 升级版24
> - 92 ⭐️⭐️⭐️⭐️⭐️⭐️ 反转一部分
> - 328 类似 143


## Map 存储信息

> **Question**
>
> 138 复制带随机指针的链表 // Map<Node, Node> map, oldNode -> newNode

## 双指针

> **Question**
>
> 82 ⭐️⭐️⭐️⭐️⭐️⭐️ - 删除重复元素
>
> Sliding Window
> 19, 61

## 环形

Floyd's cycle Detection
```java
while (fast != null && fast.next != null) {
    slow = slow.next;
    fast = fast.next.next;

    if (slow == fast) {
        // find the pointer
        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
```

> **Question**
>
> 142: linkedlist 解法
> 287: list 解法
> 708: cycle中insert


## 重排序

> **Question**
>
> 147 ⭐️⭐️⭐️⭐️
> 148 ⭐️⭐️⭐️⭐️ split by middle & sort & merge


## 益智

> **Question**
>
> 2
> 23
> 160
> 2807
