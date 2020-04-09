# Dockerfile 定制镜像

Dockerfile 是一个用来构建镜像的文本文件，文本内容包含了一条条构建镜像所需的指令和说明。

## Dockerfile 定制镜像

### 1 创建 DockerFile 文件

```sh
[root@localhost home]# mkdir dockerNginx
[root@localhost home]# cd dockerNginx
[root@localhost dockerNginx]#touch Dockerfile
[root@localhost dockerNginx]#vim Dockerfile
```

其内容是：

```sh
FROM nginx
RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
```

该Dockerfile非常简单，其中的FROM、RUN都是Dockerfile的指令。

FROM指令用于指定基础镜像，RUN指令用于执行命令。

- **FROM**: 所谓定制镜像，那一定是以一个镜像为基础，在其上进行定制。就像我们之前运行了一个 `nginx` 镜像的容器，再进行修改一样，基础镜像是必须指定的。而 `FROM` 就是指定**基础镜像**，因此一个 `Dockerfile` 中 `FROM` 是必备的指令，并且必须是第一条指令。

## 2 在Dockerfile所在路径执行以下命令构建镜像

```sh
[root@localhost dockerNginx]# docker build -t nginx:my .
Sending build context to Docker daemon  2.048kB
Step 1/2 : FROM nginx
 ---> ed21b7a8aee9
Step 2/2 : RUN echo '<h1>Hello, Docker!</h1>' > /usr/share/nginx/html/index.html
 ---> Running in f04330860a32
Removing intermediate container f04330860a32
 ---> 78aef329cbce
Successfully built 78aef329cbce
Successfully tagged nginx:my
```

其中，命令最后的点（.）用于路径参数传递，表示当前路径。

> nginx:my  仓库名：标签

# 3.启动容器。

```sh
[root@localhost dockerNginx]# docker run -d -p 82:80 nginx:my
```



![image-20200401153626514.png](http://mtcarpenter.oss-cn-beijing.aliyuncs.com/2020/image-20200401153626514.png)

## Docker 常用指令

### ADD复制文件

ADD指令用于复制文件，格式为：

```sh
ADD<src>...<dest>  <源路径1>...  <目标路径>
ADD["<src>",..."<dest>"] [<源路径>...  <目标路径>]
```

从 src 目录复制文件到容器的 dest 。其中 src 可以是 Dockerfile 所在目录的相对路径，也可以是一个 URL，还可以是一个压缩包。

src 必须在构建的上下文内，不能使用例如 ADD../somethine/something 这样的命令，因为 docker build 命令首先会将上下文路径和其子目录发送到 docker daemon。

- 如果 src 是一个URL，同时dest不以斜杠结尾，dest 将被视为文件，src 对应内容文件将被下载到dest。
- 如果 src 是一个URL，同时dest以斜杠结尾，dest 将被视为目录，src 对应内容将被下载到dest目录。
- 如果 src 是一个目录，那么整个目录下的内容将被复制，包括文件系统元数据。
- 如果文件是可识别的压缩包格式，则 docker 会自动解压。

### ARG设置构建参数

ARG指令用于设置构建参数，类似于ENV。和ENV不同的是，ARG设置的是构建时的环境变量，在容器运行时是不会存在这些变量的。
格式为：

```sh
ARG<name>[=<defaul t value>] # ARG <参数名>[=<默认值>]
```

### CMD容器启动命令

CMD 指令用于为执行容器提供默认值。每个 Dockerfile 只有一个 CMD 命令，如果指定了多个 CMD 命令，那么只有最后一条会被执行，如果启动容器时指定了运行的命令，则会覆盖 CMD 指定的命令。

类似于 RUN 指令，用于运行程序，但二者运行的时间点不同:

- CMD 在docker run 时运行。
- RUN 是在 docker build。

**作用**：为启动的容器指定默认要运行的程序，程序运行结束，容器也就结束。CMD 指令指定的程序可被 docker run 命令行参数中指定要运行的程序所覆盖。

**注意**：如果 Dockerfile 中如果存在多个 CMD 指令，仅最后一个生效。

```sh
CMD <shell 命令> 
CMD ["<可执行文件或命令>","<param1>","<param2>",...] 
CMD ["<param1>","<param2>",...]  # 该写法是为 ENTRYPOINT 指令指定的程序提供默认参数
```

支持3种格式：

- CMD["executable","param1","param2"]（推荐使用）

- CMD["param1","param2"]（为ENTRYPOINT指令提供预设参数）

- CMD command param1 param2（在shell中执行）

### COPY复制文件
  复制文件，格式为：

```sh
  COPY<src>...<dest>  # <源路径1>...  <目标路径>
  COPY["<src>",..."<dest>"] # ["<源路径1>",...  "<目标路径>"]
```

  复制本地端的 src 到容器的 dest。COPY 指令和 ADD 指令类似，COPY 不支持 URL 和压缩包。

### ENTRYPOINT入口点
格式为：

```sh
ENTRYPOINT["executable","param1","param2"]
ENTRYPOINT command param1 param2
```

ENTRYPOINT和CMD指令的目的一样，都是指定Docker容器启动时执行的命令，可多次设置，但只有最后一个有效。

### ENV设置环境变量
ENV指令用于设置环境变量，格式为：

```sh
ENV<key><value>
ENV<key>=<value>...
```

示例为：

```sh
ENV JAVA_HOME /path/to/java
```

### EXPOSE声明暴露的端口

EXPOSE指令用于声明在运行时容器提供服务的端口，格式为：

```sh
EXPOSE<port> [<port>...] # EXPOSE <端口1> [<端口2>...]
```

需要注意的是，这只是一个声明，运行时并不会因为该声明就打开相应端口。该指令的作用主要是帮助镜像使用者理解该镜像服务的守护端口；其次是当运行时使用随机映射时，会自动映射EXPOSE的端口。示例：

### FROM指定基础镜像

使用FROM指令指定基础镜像，FROM指令有点像Java里面的extends关键字。需要注意的是，FROM指令必须指定且需要写在其他指令之前。FROM指令后的所有指令都依赖于该指令所指定的镜像。
支持3种格式：

```sh
FROM<image>
FROM<image>:<tag>
FROM<image>@<digest>
```

### LABEL为镜像添加元数据
LABEL指令用于为镜像添加元数据。格式为：

```sh
LABEL<key>=<value><key>=<value><key>=<value>...
```

使用“"”和“\”转换命令行

### MAINTAINER指定维护者的信息
MAINTAINER指令用于指定维护者的信息，用于为Dockerfile署名。
格式为：

```sh
MAINTAINER <name> # MAINTAINER author<123@163.com>
```

### RUN执行命令
该指令支持两种格式：

- RUN<command>

- RUN["executable","param1","param2"]

RUN<command>在shell终端中运行，在Linux中默认是/bin/sh-c，在Windows中是  cmd/s/c，使用这种格式，就像直接在命令行中输入命令一样。  RUN["executable","param1","param2"]使用exec执行，这种方式类似于函数调用。指定其他  终端可以通过该方式操作，例如RUN["/bin/bash","-c","echo hello"]，该方式必须使用双引  号["]而不能使用单引号[']，因为该方式会被转换成一个JSON数组。

### VOLUME指定挂载点
该指令使容器中的一个目录具有持久化存储的功能，该目录可被容器本身使用，也可共享给其他容器。当容器中的应用有持久化数据的需求时可以在Dockerfile中使用该指令。格式为：

```sh
VOLUME["/data"]
```

### WORKDIR指定工作目录
格式为：

```sh
WORKDIR/path/to/workdir
```

切换目录指令，类似于cd命令，写在该指令后的RUN、CMD以及ENTRYPOINT指令都将该目录作为当前目录，并执行相应的命令。

### USER设置用户
该指令用于设置启动镜像时的用户或者UID，写在该指令后的RUN、CMD 以及 ENTRYPOINT 指令都将使用该用户执行命令。
格式为：

```sh
USER 用户名 # user  daemon
```

## 使用Dockerfile构建镜像

对常用 Dockerfile的常用指令，对 sentinel (https://github.com/alibaba/Sentinel) 控制面板进行整合。

### 1 新建目录 sentinel ，创建名为 Dockerfile 的文件，并将下载的 jar 包放入该文件夹。

```sh
[root@localhost home]# mkdir sentinel
[root@localhost home]# cd sentinel
[root@localhost sentinel]# touch Dockerfile
```

### 2 Dockerfile 中添加以下内容：

```sh
# 基于 openjdk 镜像
FROM openjdk:8-alpine
# 作者信息
MAINTAINER  mtcarpenter<dreamlixc@163.com>
# 复制文件到容器，
ADD sentinel-dashboard.jar app.jar
# 声明端口
EXPOSE 8080
# 启动命令
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

### 3 在该文件下，构建镜像

```sh
# docker build -t 仓库名称/镜像名称(:标签) Docker 的相对位置
[root@localhost sentinel]# docker build -t sentinel:my .
Sending build context to Docker daemon  21.21MB
Step 1/5 : FROM openjdk:8-alpine
 ---> a3562aa0b991
Step 2/5 : MAINTAINER  author<123@163.com>
 ---> Running in 67b1088173df
Removing intermediate container 67b1088173df
 ---> a78329902153
Step 3/5 : ADD sentinel-dashboard.jar app.jar
 ---> 2e0e40cc9ba2
Step 4/5 : EXPOSE 8080
 ---> Running in 1a90c546dee2
Removing intermediate container 1a90c546dee2
 ---> e0d92daab88f
Step 5/5 : ENTRYPOINT ["java", "-jar", "/app.jar"]
 ---> Running in df08622640e9
Removing intermediate container df08622640e9
 ---> 8cf3d447aaa8
Successfully built 8cf3d447aaa8
Successfully tagged sentinel:my

```
> 打包成功

查看打包的镜像
```sh
[root@localhost sentinel]# docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
sentinel            my                  000438df551d        15 hours ago        126MB

```

运行镜像文件：
```sh
[root@localhost sentinel]# docker run -d -p 8080:8080  sentinel:my
828dd8e799500a09e1364e1ac56a0083fd114d989cb526e77b7f93a9614042a8

```
运行效果如下：
![image-20200402090658688.png](http://mtcarpenter.oss-cn-beijing.aliyuncs.com/2020/image-20200402090658688.png)