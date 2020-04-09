# Docker 常用命令操作

## 搜索镜像
docker search : 主要从Docker 仓库查找镜像
其命令格式为：

```sh
docker search [OPTIONS] TERM
```
### OPTIONS说明：
| 参数        | 默认值 | 描述                       |
| ----------- | ------ | -------------------------- |
| --filter,-f |        | 根据指定条件过滤           |
| --limit     | 25     | 搜索结果的最大数           |
| --no-trunc  | false  | 不截断输出，显示完整的输出 |

> 已废弃暂未列举

### 案例上手

``` sh
[root@localhost ~]# docker search  nginx
# [root@localhost ~]# docker search --limit 5 nginx 
```

![image-20200401115313472.png](http://mtcarpenter.oss-cn-beijing.aliyuncs.com/2020/image-20200401115313472.png)

该表格包含5列，含义如下。

- NAME：镜像仓库名称。
- DESCRIPTION：镜像仓库描述。
- STARS：镜像仓库收藏数，表示该镜像仓库的受欢迎程度，类似于GitHub的Stars。
- OFFICAL：表示是否为官方仓库，该列标记为[OK]的镜像均由各软件的官方项目组创建和维护。由结果可知，java这个镜像仓库是官方仓库，而其他仓库都不是官方镜像仓库。
- AUTOMATED：表示是否是自动构建的镜像仓库。

## 拉取镜像

docker pull : 从镜像仓库中拉取或者更新指定镜像
其命令格式为：

```sh
docker pull [OPTIONS] NAME[:TAG|@DIGEST]
```

### OPTIONS说明：

| 参数                    | 默认值 | 描述           |
| ----------------------- | ------ | -------------- |
| --all-tags,-a           | false  | 下载所有的标签 |
| --disable-content-trust | true   | 忽略镜像的校验 |

### 案例上手

```sh
docker pull nginx #  ：后面跟标签，省略表示 最后一个版本
docker pull nginx:latest  # :latest 可以省略
```

## 列出镜像

docker images: 主要列出已下载的镜像
其命令格式为：

```sh
docker images [OPTIONS] [REPOSITORY[:TAG]]
```

### OPTIONS说明：

| 参数        | 默认值 | 描述                                                         |
| ----------- | ------ | ------------------------------------------------------------ |
| --all,-a    | false  | 列出本地所有的镜像（包括中间镜像层，默认情况下，过滤中间影响层） |
| --digests   | false  | 显示摘要信息                                                 |
| --filter,-f |        | 显示满足条件的镜像                                           |
| --format    |        | 指定返回值的模板文件                                         |
| --no-trunc  |        | 显示完整的镜像信息                                           |
| -q          |        | 只显示镜像ID                                                 |

### 案例上手

``` sh
[root@localhost ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
hello-world         latest              fce289e99eb9        15 months ago       1.84kB
[root@localhost ~]#
```

该表格包含了5列，含义如下:

- REPOSITORY：镜像所属仓库名称。
- TAG：镜像标签。默认是latest，表示最新。
- IMAGE ID：镜像ID，表示镜像唯一标识。
- CREATED：镜像创建时间。
- SIZE：镜像大小。

## 删除本地镜像

docker rmi: 删除本地一个或多少镜像
其命令格式为：

```sh
docker rmi [OPTIONS] IMAGE [IMAGE...]
```

### OPTIONS说明：

| 参数       | 默认值 | 描述                             |
| ---------- | ------ | -------------------------------- |
| --force,-f | false  | 强制删除                         |
| --no-prune | false  | 不移除该进项的过程镜像，默认移除 |

### 案例上手

``` sh
# 删除指定名称的镜像
[root@localhost ~]# docker rmi  hello-world
```

```sh
# 删除所有的镜像
[root@localhost ~]# docker rmi  -f $(docker images)
```

## 容器创建新镜像

**docker commit :**从容器创建一个新的镜像。
其命令格式为：

```sh
docker commit [OPTIONS] CONTAINER [REPOSITORY[:TAG]]
```

### OPTIONS说明：

| 参数         | 默认值 | 描述                         |
| ------------ | ------ | ---------------------------- |
| --author,-a  |        | 提交的镜像作者               |
| --change,-c  |        | 使用Dockerfile指令来创建镜像 |
| --message,-m |        | 提交的说明文字               |
| --pause,-p   |        | 在commit时，将容器暂停       |

### 案例上手

``` sh
[root@localhost ~]#  docker run  -d -p 80:80 nginx
62f5ca172d40adbd52e85eee0f2a8b4022ed5ca89fc69ff1630d928cffcc7594
[root@localhost ~]# docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                NAMES
62f5ca172d40        nginx               "nginx -g 'daemon of…"   6 seconds ago       Up 4 seconds        0.0.0.0:80->80/tcp   stoic_gauss
[root@localhost ~]# docker commit  -a  "lxc"     -m  "修改信息"      62f5ca172d40   nginx:v1
sha256:f3d16e35a9190a51edef21d2d98c807e48ee9a3ea71ed8edf3ea79ed868fd571
[root@localhost ~]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
nginx               v1                  f3d16e35a919        5 seconds ago       127MB
nginx               latest              ed21b7a8aee9        27 hours ago        127MB
hello-world         latest              fce289e99eb9        15 months ago       1.84kB
[root@localhost ~]#
```

