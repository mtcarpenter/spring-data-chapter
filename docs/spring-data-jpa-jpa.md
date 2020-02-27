# 【Spring Data 系列学习】了解 Spring Data JPA 、 Jpa 和 Hibernate 

在开始学习 Spring Data JPA 之前，首先讨论下 Spring Data Jpa、JPA 和 Hibernate 之前的关系。

## JPA

JPA 是 Java Persistence API  的简称，中文名 Java 持久层 API，是 JDK 5.0 注解或 XML 描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。

Sun 引入新的 JPA ORM 规范出于两个原因：其一，简化现有 Java EE 和 Java SE 应用开发工作；其二，Sun 希望整合 ORM 技术，实现天下归一。

JPA 由 EJB 3.0 软件专家组开发，作为 JSR-220 实现的一部分。但它又不限于 EJB 3.0，你可以在 Web 应用、甚至桌面应用中使用。JPA 的宗旨是为 POJO 提供持久化标准规范，由此可见，经过这几年的实践探索，能够脱离容器独立运行，方便开发和测试的理念已经深入人心了。Hibernate 3.2+ 、TopLink 10.1.3以及 OpenJPA 都提供了 JPA 的实现。

JPA 的总体思想和现有 Hibernate、TopLink、JDO 等 ORM 框架大体一致。总的来说，JPA 包括以下 3 方面的技术：

- ORM映射元数据 : JPA 支持 XML 和 JDK 5.0 注解两种元数据的形式，元数据描述对象和表之间的映射关系，框架据此将实体对象持久化到数据库表中；

- API:用来操作实体对象，执行 CRUD 操作，框架在后台替代我们完成所有的事情，开发者从繁琐的 JDBC 和SQL代码中解脱出来。

- 查询语言: 这是持久化操作中很重要的一个方面，通过面向对象而非面向数据库的查询语言查询数据，避免程序的SQL 语句紧密耦合。

## Hibernate 

Hibernate 是一个开放源代码的对象关系映射框架，对 JDBC 进行了非常轻量级的对象封装，使得 Java 程序员可以随心所欲地使用对象编程思维来操纵数据库，并且对象有自己的生命周期，着力对象与对象之间的关系，有自己的HQL 查询语言，所以数据库移植性很好。Hibernate是完备的 ORM 框架，是符合 JPA 规范的。Hibernate 有自己的缓存机制。从上手的角度来说比较难，比较适合企业级的应用系统开发。

## JPA和Hibernate的关系？

- 1.JPA 是 hibernate 的一个抽象。

  > JPA 是规范：JPA 本质上就是一种 ORM 规范,不是 ORM 框架 —— 因为 JPA 并未提供 ORM 实现他只是制定了一些规范,提供了一些编程的 API 接口,但具体实现则由 ORM 厂商提供实现 。
  >
  > Hibernate 是实现：Hibernate 除了作为 ORM 框架之外,它也是一种 JPA 实现。	

- 2.从功能上来说，JPA 是 Hibernate 功能的一个子集。

## Spring Data JPA 

可以理解为 JPA 规范的再次封装抽象，底层还是使用了Hibernate 的 JPA 技术实现，引用 JPQL（Java Persistence Query Language）查询语言，属于Spring整个生态体系的一部分。随着Spring Boot和Spring Cloud 在市场上的流行，Spring Data JPA也逐渐进入大家的视野，它们组成有机的整体，使用起来比较方便，加快了开发的效率，使开发者不需要关心和配置更多的东西，完全可以沉浸在Spring 的完整生态标准实现下。JPA上手简单，开发效率高，对对象的支持比较好，又有很大的灵活性，市场的认可度越来越高。

## Spring Data JPA和JPA的关系？ 

Spring Data JPA 是在 JPA 规范的基础下提供了 Repository 层的实现，但是使用哪一款 ORM 需要你自己去决定；相比我们更为熟悉的 Hibernate 和 MyBatis， Spring Data JPA 可以看做更高层次的抽象。 Spring 在做持久化这一块的工作，开发了 Spring-data-xxx 这一系列包，如： Spring Data JPA， Spring Data Redis， Spring Data Mongodb 等等，这些都是 Spring 提供的基于 JPA 和其他一些 NOSQL 的 Repository。

文章参考:

Spring Data JPA 从入门到精通书籍

https://spring.io/projects/spring-data

https://www.oschina.net/p/spring-data

https://www.ibm.com/developerworks/cn/opensource/os-cn-spring-jpa/






