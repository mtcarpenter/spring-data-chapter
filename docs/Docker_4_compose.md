# Docker Compose 编排微服务

`Docker Compose` 是 Docker 官方编排（Orchestration）项目之一，负责快速的部署分布式应用。

使用Dockerfile（或Maven）构建镜像，然后使用docker命令操作容器，例如docker run、docker kill等。实际场景应用系统一般包含若干个微服务，每个微服务一般都会部署多个实例。如果每个微服务都要手动启停，那么效率之低、维护量之大可想而知。

##  Docker Compose 简介

`Compose` 项目是 Docker 官方的开源项目，负责实现对 Docker 容器集群的快速编排。从功能上看，跟 `OpenStack` 中的 `Heat` 十分类似。

其代码目前在 https://github.com/docker/compose 上开源。

`Compose` 定位是 「定义和运行多个 Docker 容器的应用（Defining and running multi-container Docker applications）」，其前身是开源项目 Fig。

通过第一部分中的介绍，我们知道使用一个 `Dockerfile` 模板文件，可以让用户很方便的定义一个单独的应用容器。然而，在日常工作中，经常会碰到需要多个容器相互配合来完成某项任务的情况。例如要实现一个 Web 项目，除了 Web 服务容器本身，往往还需要再加上后端的数据库服务容器，甚至还包括负载均衡容器等。

`Compose` 恰好满足了这样的需求。它允许用户通过一个单独的 `docker-compose.yml` 模板文件（YAML 格式）来定义一组相关联的应用容器为一个项目（project）。

`Compose` 中有两个重要的概念：

- 服务 (`service`)：一个应用的容器，实际上可以包括若干运行相同镜像的容器实例。
- 项目 (`project`)：由一组关联的应用容器组成的一个完整业务单元，在 `docker-compose.yml` 文件中定义。

`Compose` 的默认管理对象是项目，通过子命令对项目中的一组容器进行便捷地生命周期管理。

`Compose` 项目由 Python 编写，实现上调用了 Docker 服务提供的 API 来对容器进行管理。因此，只要所操作的平台支持 Docker API，就可以在其上利用 `Compose` 来进行编排管理。

## 安装 Compose

### 1. 通过以下命令自动下载并安装适应系统版本的Compose：

```sh
sudo curl -L https://github.com/docker/compose/releases/download/1.17.1/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
```

### 2.为安装脚本添加执行权限：

```sh
chmod +x /usr/local/bin/docker-compose
```

### 3.测试 Compose 是否安装成功

```sh
docker-compose --version
```

## Docker-compose 基本使用

使用Compose大致有3个步骤：

- 使用Dockerfile（或其他方式）定义应用程序环境，以便在任何地方重现该环境。
- 在docker-compose.yml文件中定义组成应用程序的服务，以便各个服务在一个隔离的
  环境中一起运行。
- 运行docker-composeup命令，启动并运行整个应用程序。

在上一个章节 sentinel 文件下，  通过 Dockerfile 运行容器，再次升级，如下

###  新建目录 sentinel ，创建名为 Dockerfile 的文件，并将下载的 jar 包放入该文件夹。

```sh
[root@localhost home]# mkdir sentinel
[root@localhost home]# cd sentinel
[root@localhost sentinel]# touch Dockerfile
```

### Dockerfile 中添加以下内容：

```dockerfile
# 基于 openjdk 镜像
FROM openjdk:8-alpine
# 作者信息
MAINTAINER  mtcarpenter<dreamlixc@163.com>
# 复制文件到容器，
ADD sentinel-dashboard.jar app.jar
# 启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### sentinel 文件下 创建 docker-compose.yml，在其中添加如下内容：

```sh
version: '3'
services:
  sentinel: # 指定服务器名称
    build: . # 指定Docker 所在路径
    ports:
      - "8080:8080"  # 指定端口映射，类似 docker run -p
```

### 在docker-compose.yml所在路径执行以下命令：

```sh
docker-compose up # docker-compose up -d 后台运行
```

运行成功 访问 `ip:8080` ,出现 sentinel 控制台。

##  docker-compose.yml  模板文件常用命令说明

docker-compose.yml是Compose的默认模板文件。该文件有多种格式，例如Version 1file format、Version 2 file format、Version 2.1 file format、Version 3 file format等。其中，Version 1 file format将逐步被弃用，Version 2.x及Version 3.x基本兼容。

### build：配置构建时的选项，Compose会利用它自动构建镜像。build的值可以是一个路径

```dockerfile
version: '3'
services:
  sentinel: # 指定服务器名称
    build: . # 指定Docker 所在路径
```

也可以是一个对象

```dockerfile
version: "3"
services:
  sentinel:
    build:
      context: ./dir
      dockerfile: Dockerfile-alternate
      args:
        buildno: 1
      labels:
        - "com.example.description=Accounting webapp"
        - "com.example.department=Finance"
        - "com.example.label-with-empty-value"
      target: prod
```

- context：上下文路径。
- dockerfile：指定构建镜像的 Dockerfile 文件名。
- args：添加构建参数，这是只能在构建过程中访问的环境变量。
- labels：设置构建镜像的标签。
- target：多层构建，可以指定构建哪一层。

### command：覆盖容器启动后默认执行的命令

```dockerfile
command: bundle  exec  thin -p  3000
```

也可以是一个list，类似Dockerfile中的CMD指令，格式如下：

```dockerfile
command: ["bundle", "exec", "thin", "-p", "3000"]
```

### expose：暴露端口，只将端口暴露给连接的服务，而不暴露给宿主机。

```yaml
expose:
 - "3000"
 - "8080"
```

### external_links 链接到 docker-compose.yml 外部的容器，甚至并非 Compose 管理的外部容器。

> 注意：不建议使用该指令。

```yaml
external_links:
 - redis_1
 - project_db_1:mysql
 - project_db_1:postgresql
```

### image：指定镜像名称或镜像ID，如果本地不存在该镜像，Compose会尝试下载该镜像。

```yaml
  redis:
    image: "redis:alpine"
```

## dokcer-compose 命令

docker-compose命令也有很多选项。下面来详细探讨docker-compose的常用命令。

### build

构建或重新构建服务。服务被构建后将以project_service的形式标记，例如 composetest_db 。

### help

查看指定命令的帮助文档，该命令非常实用。docker-compose所有命令的帮助文档都可通过该命令查看。

示例：

```sh
[root@localhost sentinel]#docker-compose help command
[root@localhost sentinel]#docker-compose help build # 查看build 相关命令
```

### kill 

通过发送SIGKILL信号停止指定服务的容器。

示例：

```sh
[root@localhost sentinel]#docker-compose kill sentinel
```

### logs

查看服务的日志输出。

示例：

```sh
[root@localhost sentinel]#docker-compose logs
```

### port

打印绑定的公共端口。

示例：

```sh
[root@localhost sentinel]# docker-compose port sentinel 8080
0.0.0.0:8080
```

这样就可输出sentinel服务8080端口所绑定的公共端口。

### ps

列出所有容器。

示例：

```sh
[root@localhost sentinel]# docker-compose ps
       Name                Command         State           Ports
-------------------------------------------------------------------------
sentinel_sentinel_1   java -jar /app.jar   Up      0.0.0.0:8080->8080/tcp
[root@localhost sentinel]# docker-compose ps sentinel

```

### pull 

下载服务镜像。

### rm

rm：删除指定服务的容器。

示例:

```sh
[root@localhost sentinel]# docker-compose rm sentinel
```

### run

在一个服务上执行一个命令。

示例：

```sh
[root@localhost sentinel]# docker-compose run web bash
```

这样即可启动一个web服务，同时执行bash命令。

### start

启动指定服务已存在的容器。

示例：

```sh
[root@localhost sentinel]# docker-compose start sentinel
```

### stop

停止已运行的容器。

示例：

```sh
[root@localhost sentinel]# docker-compose stop sentinel
```

停止后，可使用docker-compose start再次启动这些容器。

### up

构建、创建、重新创建、启动，连接服务的相关容器。所有连接的服务都会启动，除非它们已经运行。
docker-compose up命令会聚合所有容器的输出，当命令退出时，所有容器都会停止。
使用docker-compose up-d可在后台启动并运行所有容器。

选项：

- `-d` 在后台运行服务容器。
- `--no-color` 不使用颜色来区分不同的服务的控制台输出。
- `--no-deps` 不启动服务所链接的容器。
- `--force-recreate` 强制重新创建容器，不能与 `--no-recreate` 同时使用。
- `--no-recreate` 如果容器已经存在了，则不重新创建，不能与 `--force-recreate` 同时使用。
- `--no-build` 不自动构建缺失的服务镜像。
- `-t, --timeout TIMEOUT` 停止容器时候的超时（默认为 10 秒）。

### 





https://docs.docker.com/compose/compose-file/

https://www.runoob.com/docker/docker-compose.html