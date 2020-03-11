# 【Linux 开发者学习篇】Linux 中的 Vim 使用
所有的 Unix Like 系统都会内建 vi 文书编辑器，其他的文书编辑器则不一定会存在。
但是目前我们使用比较多的是 vim 编辑器。
vim 具有程序编辑的能力，可以主动的以字体颜色辨别语法的正确性，方便程序设计。

## 什么是 vim？

Vim是从 vi 发展出来的一个文本编辑器。代码补完、编译及错误跳转等方便编程的功能特别丰富，在程序员中被广泛使用。

简单的来说， vi 是老式的字处理器，不过功能已经很齐全了，但是还是有可以进步的地方。 vim 则可以说是程序开发者的一项很好用的工具。

vim 键盘图：

![image](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/vi-vim-cheat-sheet-sch.gif)

## vim 的使用

**安装 vim**

```
[root@localhost ~]# yum install -y vim-enhanced
```

**卸载 vim**

```
[root@localhost ~]# yum remove -y vim*
```

## Vim 命令

**vim一共有4个模式 **：

- 正常模式 (Normal-mode) 
- 插入模式 (Insert-mode)
- 命令模式 (Command-mode)
- 可视模式 (Visual-mode)

**正常模式（Normal-mode）**

正常模式是使用vim打开文件时的默认模式，无论在哪种模式下，按下Esc键就会进入正常模式。在这个模式下，可以移动光标，删除某个字符，删除某行，复制多行，粘贴多行。

![image-20200310123929659](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200310123929659.png)

启动 vim 后默认位于正常模式。不论位于什么模式，按下<Esc>键(有时需要按两下）都会进入正常模式。

- 命令操作
  - `yy `:复制当前行
   - `nyy`: 复制从当前行开始后的n行
   - `p`:粘贴已复制的内容
   - `dd `:删除当前行
   - `ndd `:删除从当前行开始后的n行

**插入模式/编辑模式**

在正常模式中按下i, I, a, A,r, R等键（后面系列文章会详细介绍），会进入插入模式，一般选择按 i 即可。现在只用记住按i键会进行插入模式。插入模式中，击键时会写入相应的字符，左下角会显示插入或者 Insert 关键字。

![image-20200310124444356](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200310124444356.png)

- **命令操作**
  - i 小写字母i，在光标位置插入
  - a 小写字母a，在光标的下一个位置插入
  - I 大写字母I，在光标所在行的第一个非空格处插入
  - A 大写字母A，在光标所在行的最后一个字符处插入
  - o 小写字母o，在光标所在行的下一行处插入新行
  - O 大写字母O，在光标所在行的上一行处插入新行
  - r 小写字母r，替换光标所在处的字符一次
  - R 大写字母R，持续替换光标所在处的字符，直到按下ESC

-  **移动光标的方法**
  - `h 或 向左箭头键(←)`:光标向左移动一个字符
  - `j 或 向下箭头键(↓)`:光标向下移动一个字符
  - `k 或 向上箭头键(↑)`:光标向上移动一个字符
  - `l 或 向右箭头键(→)`:光标向右移动一个字符

**命令模式**

在正常模式中，按下：（冒号）键，会进入命令模式。在命令模式中可以执行一些输入并执行一些vim或插件提供的指令，就像在shell里一样。这些指令包括设置环境、文件操作、调用某个功能等等。

![image-20200310125406229](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200310125406229.png)

- 命令操作（按 : 进入命令模式）
  - `:/xyz` :搜索字符串xyz
  - `:%s/x/y` :将每行中第一个x替换成y
  - `:%s/x/y/g`: 将每行中所有的x替换成y
  - `:n,ms/x/y` :将第n-m行的x替换成y
  - `:!ifconfig`: 执行命令ifconfig
  - `:w` :保存文件
  - `:w! `:强制保存文件（前提是用户有修改文件访问权限的权限）
  - `:q `:退出缓冲区
  - `:q! `:强制退出缓冲区而不保存
  - `:wq`: 保存文件并退出缓冲区
  - `:ZZ  `:保存文件并且退出
  - `:wq!`: 强制保存文件并退出缓冲区（前提是用户有修改文件访问权限的权限）
  - `:w <filename> `:另存为名为filename文件
  - `:n1,n2 w <filename>`: 将n1行到n2行的数据另存为名为filename文件
  - `: x `:如果文件有更改，则保存后退出。否则直接退出。

**可视模式**

在正常模式按下v, V, <Ctrl>+v，可以进入可视模式。可视模式中的操作有点像拿鼠标进行操作，选择文本的时候有一种鼠标选择的即视感，有时候会很方便。

![image-20200310125546543](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200310125546543.png)


## 文章参考

https://www.cnblogs.com/zzqcn/p/4619012.html

https://www.runoob.com/linux/linux-vim.html

## 本章代码

- Github：[https://github.com/mtcarpenter/spring-data-chapter](https://github.com/mtcarpenter/spring-data-chapter)