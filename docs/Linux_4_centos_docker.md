## 系统要求
Docker CE 支持 64 位版本 CentOS 7，并且要求内核版本不低于 3.10。 CentOS 7 满足最低内核的要求，但由于内核版本比较低，部分功能（如 overlay2 存储层驱动）无法使用，并且部分功能可能不太稳定。
## 卸载旧版本
```sh
sudo yum remove docker \
                  docker-client \
                  docker-client-latest \
                  docker-common \
                  docker-latest \
                  docker-latest-logrotate \
                  docker-logrotate \
                  docker-engine
```
> 官方地址：https://docs.docker.com/install/linux/docker-ce/centos/
            
已安装docker，且为最新的版本，将会出现如下提示：
```sh
已加载插件：fastestmirror
参数 docker 没有匹配
参数 docker-client 没有匹配
参数 docker-client-latest 没有匹配
参数 docker-common 没有匹配
参数 docker-latest 没有匹配
参数 docker-latest-logrotate 没有匹配
参数 docker-logrotate 没有匹配
参数 docker-engine 没有匹配
不删除任何软件包

```
## 安装Docker
首次在主机上安装 docker ，你需要设置 docker 仓库，以后就可以从这个仓库安装和更新 docker。
## 设置仓库
- 安装所需的包，yum-utils提供yum-config-manager工具，device-mapper-persistent-data和lvm2是devicemapper存储驱动所需要的。安装命令如下：
```sh
sudo yum install -y yum-utils \
  device-mapper-persistent-data \
  lvm2
```
- 通过如下的命令设置稳定的仓库
```sh
sudo yum-config-manager \
    --add-repo \
    https://mirrors.ustc.edu.cn/docker-ce/linux/centos/docker-ce.repo


# 官方源
# $ sudo yum-config-manager \
#     --add-repo \
#     https://download.docker.com/linux/centos/docker-ce.repo  
```
如果需要最新版本的 Docker CE ：
```sh
sudo yum-config-manager --enable docker-ce-edge
```
如果需要测试版本的 Docker CE ：
```sh
sudo yum-config-manager --enable docker-ce-test
```
## 安装 Docker
更新 yum 软件源缓存，通过缓存提高软件安装速度
```sh
sudo yum makecache fast
#sudo yum clean all # 清楚命令

```
安装 docker-ce
```sh
sudo yum install docker-ce
```
安装成功需要重启 docker 才能生效
## 重启 Docker CE
```sh
 sudo systemctl enable docker
 sudo systemctl start docker
```
## 建立 docker 用户组 

默认情况下，docker 命令会使用 Unix socket 与 Docker 引擎通讯。而只有 root 用户和 docker 组的用户才可以访问 Docker 引擎的 Unix socket。出于安全考虑，一般 Linux 系统上不会直接使用 root 用户。因此，更好地做法是将需要使用 docker 的用户加入 docker 用户组。
建立 docker 组：
```sh
sudo groupadd docker
```
将当前用户加入 docker 组：
```sh
sudo usermod -aG docker $USER
```
> $USER表示当前用户。 当前是登录 docker 用户进行 docker-ce 的安装。
添加完成之后可以进行重新登录即可。

## 测试 docekr 安装是否正确
测试 hello-world 镜像
``` sh
[docker@localhost ~]#  docker pull hello-world
# 测试
[docker@localhost ~]# docker run hello-world

Hello from Docker!
This message shows that your installation appears to be working correctly.

To generate this message, Docker took the following steps:
 1. The Docker client contacted the Docker daemon.
 2. The Docker daemon pulled the "hello-world" image from the Docker Hub.
    (amd64)
 3. The Docker daemon created a new container from that image which runs the
    executable that produces the output you are currently reading.
 4. The Docker daemon streamed that output to the Docker client, which sent it
    to your terminal.

To try something more ambitious, you can run an Ubuntu container with:
 $ docker run -it ubuntu bash

Share images, automate workflows, and more with a free Docker ID:
 https://hub.docker.com/

For more examples and ideas, visit:
 https://docs.docker.com/get-started/

```
>出现 Hello from Docker! 表示安装成功

## 卸载 docker 
```sh
sudo yum remove docker-ce
```
只是删除 docker 软件，镜像、容器、自定义配置文件依然存在，需要彻底删除需要手动执行命令：
```sh
sudo rm -rf /var/lib/docker
```

## 镜像加速
如果使用默认的 docker 镜像一般会很慢或者无法下载对镜像进行下载。

```sh
vim /etc/docker/daemon.json
```
修改配置：
```sh
{
  "registry-mirrors": ["http://hub-mirror.c.163.com"]
}
```
> docker国内镜像主要有：
- docker中国区官方镜像 https://registry.docker-cn.com
- 网易蜂巢 http://hub-mirror.c.163.com
- 阿里镜像 https://xxxx.mirror.aliyuncs.com // 需要注册，根据帐号修改xxxx内容
- DaoCloud http://xxxx.m.daocloud.ip //需要注册，根据帐号修改xxxx内容
Docker的卸载
