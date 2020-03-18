# 【Linux 开发者学习篇】用户管理、文件操作、权限
[toc]

## 文件目录

### ls 目标列表

**ls命令**用来显示目标列表，在Linux中是使用率较高的命令。ls命令的输出信息可以进行彩色加亮显示，以分区不同类型的文件。

- 基本语法

```sh
ls [选项] [目录或者文件]
```

- 常用选项

  `-l`:列出文件的详细信息

  `-a`:显示所有档案及目录（ls内定将档案名或目录名称为“.”的视为影藏，不会列出）

- 案例

```sh
# 显示当前目录下非影藏文件与目录
[root@localhost ~]# ls
anaconda-ks.cfg
# 输出长格式列表
[root@localhost ~]# ls -l
总用量 4
-rw-------. 1 root root 1269 3月   9 22:07 anaconda-ks.cfg
# 显示当前目录下包括影藏文件在内的所有文件列表
[root@localhost ~]# ls -a
.   anaconda-ks.cfg  .bash_logout   .bashrc  .tcshrc
..  .bash_history    .bash_profile  .cshrc   .viminfo

```

### mkdir 创建目录

**mkdir命令**用来创建目录。该命令创建由[dirname](https://philipding.github.io/linux/#/dirname)命名的目录。

- 基本语法

```sh 
mkdir [选项] 创建目录
```

- 常用选项

  `-p` : 可以创建多级目录。若所要建立目录的上层目录目前尚未建立，则会一并建立上层目录

- 案例

```sh
# 在 home 文件下创建文件夹
[root@localhost ~]# mkdir /home/study
[root@localhost ~]# cd /home
[root@localhost home]# ls
study  
# 在 home 文件下 创建多级新目录 
[root@localhost home]# mkdir -p  learn/study
[root@localhost home]# ls
learn  study  
[root@localhost home]# cd learn/study
[root@localhost study]# ll
总用量 0
[root@localhost study]#

```

### cd 切换目录

**cd命令**用来切换工作目录。

- 基本语法

```sh
cd  [参数]
```

- 常用选项

  `cd `: 进入用户主目录

  `cd ~`:进入用户主目录

  `cd -`:返回进入此目录之前所在的目录

  `cd ..`:返回上级目录（若当前目录为“/“，则执行完后还在“/"；".."为上级目录的意思）

  `cd ../..`:返回上两级目录

  `cd !$`:把上个命令的参数作为 cd 参数使用

touch

### touch 创建一个新的空的文件

**touch命令**有两个功能：一是用于把已存在文件的时间标签更新为系统当前的时间（默认方式），它们的数据将原封不动地保留下来；二是用来创建新的空文件。

- 基本语法

```sh
touch 文件名称
```

- 常用选项
- 案例

```sh
# 创建文件 s1.txt
[root@localhost study]# touch s1.txt
[root@localhost study]# ls
s1.txt
# 创建多个文件 s2.txt s3.txt
[root@localhost study]# touch s2.txt s3.txt
[root@localhost study]# ls
s1.txt  s2.txt  s3.txt
```



### echo 输出指定的字符串或者变量

**echo命令**用于在shell中打印shell变量的值，或者直接输出指定的字符串。

- 基本语法

```sh
echo [选项] [输出内容]
```

- 常用选项
- 案例

```sh
# 输出内容 1234 到 s4.txt
[root@localhost study]# echo 1234 > s4.txt
[root@localhost study]# ls
s1.txt  s2.txt  s3.txt  s4.txt
```

### cat 显示文件内容 

**cat命令**连接文件并打印到标准输出设备上，cat经常用来显示文件的内容。

- 基本语法

```sh
cat [选项] 文件
```

- 常用选项

  `-n或-number`：有1开始对所有输出的行数编号。

- 案例

```
# 带行号显示文件内容
[root@localhost study]# cat -n s4.txt
     1  123
     2  456
     3  789   
# 分页显示
[root@localhost study]# cat -n s4.txt | more
     1  123
     2  456
     3  789   
```

### cp 复制文件或目录

**cp命令**用来将一个或多个源文件或者目录复制到指定的目的文件或目录。

- 基本语法

```sh
cp  [选项]  目标文件或目录
```

- 常用选项

  `-f`：强行复制文件或目录，不论目标文件或目录是否已存在

  `-r`：递归处理，将指定目录下的所有文件与子目录一并处理

- 案例

```sh
# 将 s4.txt 赋值到 cptest 中
[root@localhost study]# cp s4.txt cptest
[root@localhost study]# cd cptest
[root@localhost cptest]# ll
总用量 4
-rw-r--r--. 1 root root 12 3月  17 15:13 s4.txt
[root@localhost cptest]#
```

### rm 删除文件

**rm命令**可以删除一个目录中的一个或多个文件或目录，也可以将某个目录及其下属的所有文件及其子目录均删除掉。	

- 基本语法

```sh
rm  [选项]  删除文件或者目录
```

- 常用选项

  `-f`：强制删除文件或目录 

  `-r`：递归处理，将指定目录下的所有文件与子目录一并处理

- 案例

```sh
# 删除目录
[root@localhost study]# rm s3.txt
rm：是否删除普通空文件 "s3.txt"？
# 删除文件
[root@localhost study]# rm -r cptest
rm：是否进入目录"cptest"? 
rm：是否删除普通文件 "cptest/s4.txt"？
# 直接删除 无提示
[root@localhost study]# rm  -rf  s3.txt
# 删除当前目录下的所有文件及目录
[root@localhost study]# rm  -rf  *
```

### mv 移动文件或目录

**mv命令**用来对文件或目录重新命名，或者将文件从一个目录移到另一个目录中。

> 注意事项：mv 与 cp 的结果不同，mv 好像文件“搬家”，文件个数并未增加。而cp对文件进行复制，文件个数增加了。

- 基本语法

```sh
# 文件或目录重新命名 
mv oldFile.txt newFile.txt
# 移动文件 
mv movefile  target
```

- 常用选项
- 案例

```sh
# 文件或目录重新命名 修改test 为 test1
[root@localhost study]# mv test test1
# 移动文件   s1.txt 到 cptset 中
[root@localhost study]#mv s1.txt cptest
```

### find 搜索文件

**find命令**用来在指定目录下查找文件。

- 基本语法

```sh
 find [搜索范围] [选项]
```

- 常用选项

  `-name`:指定字符串作为寻找文件或目录的范本样式。

- 案例

```sh
# 列出当前目录及子目录下所有文件和文件夹
[root@localhost study]# find .
.
./s2.txt
./s4.txt
./cptest
./cptest/s4.txt
./cptest/s1.txt
# 当前目录及子目录下所有文件和文件夹 查询所有.txt 文件
[root@localhost study]# find . -name "*.txt"
./s2.txt
./s4.txt
./cptest/s4.txt
./cptest/s1.txt
[root@localhost study]# find . -name "s1.txt"
./cptest/s1.txt
```

### pwd 显示当前工作目录

**pwd命令**以绝对路径的方式显示用户当前工作目录。

- 基本语法

```sh
 pwd 
```

- 常用选项
- 案例

```sh
[root@localhost study]# pwd
/home/study
```



## 压缩解压缩

### tar 解压文件

**tar命令**可以为linux的文件和目录创建档案。

- 基本语法

```sh
 tar [选项] *.tar.gz
```

- 常用选项

  `-c`:产生.tar打包文件

  `-v`:显示详细信息

  `-f`：指定压缩后的文件名

  `-z`:打包同时压缩

  `-x`:解包`.tar`文件

- 案例

```sh
# 压缩 s2.txt s4.txt 文件 到 s2s4.tar.gz
[root@localhost study]# tar -zcvf s2s4.tar.gz s2.txt s4.txt
s2.txt
s4.txt
# 解压
[root@localhost cptest]# tar -zxvf  s2s4.tar.gz
s2.txt
s4.txt
```

## 用户管理

### useradd 创建账号

**useradd命令**用于Linux中创建的新的系统用户。useradd可用来建立用户帐号。

- 基本语法

```sh
useradd  [选项] 用户名
```

- 常用选项

  ` `: 无参数,直接用户名。

  `-u`: (UID号)
  `-p `:(口令)
  `-g`: (指定用户所属的群组)
  `-s`: (指定用户登入后所使用的shell)
  `-d`: (<登入目录>：指定用户登入时的启始目录)

- 案例

```sh
#1 普通用户 useradd 新建用户 自动生成与账号名一致的文件 
[root@localhost ~]# useradd test
#2 指定目录 : useradd -d 目录路径 用户名
[root@localhost ~]# useradd /home/test test
#2 指定组 : useradd -g 用户组名 用户名
[root@localhost ~]# useradd -g testgroup test
```

### userdel 指令

**userdel命令**用于删除给定的用户，以及与用户相关的文件。

- 基本语法

```sh
userdel [选项] 用户名
```

- 常用选项

  `-f`:强制删除用户，即使用户当前已登录

  `-r`:删除用户的同时，删除与用户相关的所有文件。

- 案例

```sh
# userdel 用户名         删除用户,保留家目录
[root@localhost ~]# userdel test
# userdel -r 用户名      删除用户,不保留家目录
[root@localhost ~]# useradd -r test
```

### passwd 修改用户密码、过期时间、认证信息等

**passwd命令**用于设置用户的认证信息，包括用户密码、密码过期时间等。

- 基本语法

```sh
passwd [选项] 用户名
```

- 常用选项

  `-l`:锁定用户，禁止其登录

  `-u`:解除锁定，允许用户登录

  `-d`:使该用户可用空密码登录系统

  `-e`:强制用户在下次登录时修改密码

  `-s`:显示用户的密码是否被锁定，以及密码所采用的加密算法名称

- 案例

```sh
[root@localhost home]# passwd test01
更改用户 test01 的密码 。
新的 密码：
无效的密码： 密码少于 8 个字符
重新输入新的 密码：
passwd：所有的身份验证令牌已经成功更新。
```

### usermod 指令

**usermod命令**用于修改用户的基本信息。usermod命令不允许你改变正在线上的使用者帐号名称。

- 基本语法

```sh
usermod [选项] 组名 用户名
```

- 常用选项

  `-u`:<uid> 　修改用户ID。

  `-d`:更改目录

  `-g`: 修改用户所属的群组。

  `-L`:锁定用户密码，使密码无效

  `-l`：<帐号名称> 　修改用户帐号名称。

  `-e`:<有效期限> 　修改帐号的有效期限。

- 案例

```sh
# 更改到newtest
[root@localhost ~]# usermod -d /home/newtest test
#修改 tset 账号为newtest
[root@localhost ~]# usermod -l newtest test
```

### groupadd 创建工作组

**groupadd命令**用于创建一个新的工作组，新工作组的信息将被添加到系统文件中。

- 基本语法

```sh
  groupadd [选项] 组名 # 新建
  groupdel 组名 # 删除
```

- 常用选项

  `-g`：指定新建工作组的id 

- 案例

```sh
[root@localhost home]# groupadd -g grouptest
```

### 用户和组状态

- 基本语法

```sh
su 用户名(切换用户账户)
id 用户名(显示用户的UID，GID)
whoami (显示当前用户名称)
groups (显示用户所属组)
```

- 常用选项
- 案例

```sh
# id 用户名
[root@localhost ~]# id test01
uid=1000(test01) gid=1006(test01) 组=1006(test01)
```

## 查看文件和操作目录的权限

`ls –al` 使用 ls 不带参数只显示文件名称，通过 `ls –al` 可以显示文件或者目录的权限信息。

### 文档类型

- `d` 表示目录
- `l` 表示软连接
- `–` 表示文件
- `c` 表示串行端口字符设备文件
- `b` 表示可供存储的块设备文件
- 余下的字符 3 个字符为一组。`r` 只读，`w` 可写，`x` 可执行，`-` 表示无此权限

```
[root@localhost home]# ls -al
总用量 24
drwxr-xr-x.  9 root   root     153 3月  18 15:21 .
drwxr-xr-x.  3 root   root      19 3月  17 14:09 learn
-rw-r--r--.  1 tt01   test       0 3月  18 15:21 new.txt
drwx------.  2 test   test      62 3月  18 14:43 test
drwx------.  2 tt01   tt        95 3月  18 15:24 tt01
```

```
-rw-r--r--.  1 tt01   test       0 3月  18 15:21 new.txt
```

> `-`：普通文件
> `rw-`：说明用户 test 有读写权限，没有运行权限
> `r--`：表示用户组 tt01只有读权限，没有写和运行的权限
> `r--`：其他用户只有读权限，没有写权限和运行的权限

### chown 改变某个文件或目录

**chown命令**改变某个文件或目录的所有者和所属的组，该命令可以向某个用户授权，使该用户变成指定文件的所有者或者改变文件所属的组。

- 基本语法

```sh
chown [参数] 所有者:所属组 文件或目录名称
chown [-R] 用户名称 文件或者目录
chown [-R] 用户名称 用户组名称 文件或目录
```

> **-R：** 进行递归式的权限更改，将目录下的所有文件、子目录更新为指定用户组权限

- 常用选项
- 案例

```sh
# 所属 test 用户组
[root@localhost home]# chown test new.txt
```

### chmod 改变访问权限

**chmod命令**用来变更文件或目录的权限。

- 基本语法

```sh
 chmod [参数] 权限 文件或目录名称
```

- 常用选项

  `u`：用户 user

  `g`：用户组 group

  `o`：表示其他用户

  `a`：示所有用户是系统默认的

  - #### 操作符号

    `+`：表示添加某个权限

    `-`：表示取消某个权限

    `=`：赋予给定的权限，取消文档以前的所有权限

- 案例

```sh
# 用户 u 权限 g+r 用户组 R 加入权限，o+r其他用户加入 R 权限
chmod u=rwx,g+r,o+r test.txt 
```

### 数字设定法

数字设定法中数字表示的含义。

- 0 表示没有任何权限
- 1 表示有可执行权限 = `x`
- 2 表示有可写权限 = `w`
- 4 表示有可读权限 = `r`

| r w x | r – x | rw-   | r - x  |
| :---- | :---- | ----- | :----- |
| 4 2 1 | 4 - 1 | 4 2   | 4 - 1  |
| user  | group | group | others |

- 若要 rwx 属性则 4+2+1=7
- 若要 rw- 属性则 4+2=6
- 若要 r-x 属性则 4+1=5

```sh
[root@localhost home]# ls -al
-rw-r--r--.  1 tt01   test       0 3月  18 15:21 new.txt
[root@localhost home]# chmod 777 new.txt
[root@localhost home]# ls -al
-rwxrwxrwx.  1 tt01   test       0 3月  18 15:21 new.txt
[root@localhost home]# chmod 760 new.txt
[root@localhost home]# ls -al
-rwxrw----.  1 tt01   test       0 3月  18 15:21 new.txt 
```

## 本章代码

- Github：[https://github.com/mtcarpenter/spring-data-chapter](https://github.com/mtcarpenter/spring-data-chapter)