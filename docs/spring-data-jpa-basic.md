# 【Spring Data 系列学习】Spring Data JPA 基础查询

前面的章节简单讲解了 [了解 Spring Data JPA 、 Jpa 和 Hibernate ]()，本章节开始通过案例上手 Spring boot Jpa 。

## spring data

Spring Data 库的核心接口是 `Repository`。首先需要定义实体类的接口，接口必须继承 repository 并且输入实体类型和 ID 类型，如果需要用到 CRUD 方法，可以使用 `CrudRepository` 来替代 `Repository` 。除了 `CrudRepository` 还有 `PagingAndSortingRepository `、`JpaRepository` 等。

我们用工具 IntelliJ IDEA，打开类 `Repository.class`，默认快捷键 CTRL+H ，如图显示：

![image-20200228154704224](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200228154704224.png)



从上图我们可以看出继承关系，点击 `JpaRepository` 打开Navigate→File Structure，或者点击最左边的 Structure，可以查看此类的结构以及有哪些方法和继承类。

![image-20200228155457866](https://mtcarpenter.oss-cn-beijing.aliyuncs.com/images/image-20200228155457866.png)

- `CrudRepository `：基本的 CRUD 方法。

- `PagingAndSortingRepository `：继承 `CrudRepository`，并增加分页功能。

- `JpaRepository` ：继承 `PagingAndSortingRepository ` 和 `QueryByExampleExecutor`。

## 快速上手

创建一个 Spring boot 项目，创建项⽬在 Chapter1项⽬ pom.xml 加入需要使⽤的依赖，如下所示：

```xml
<!--   引入  jpa-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<!--mysql 驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```

### 配置文件application.properties

```properties
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai
spring.datasource.username=root
spring.datasource.password=123456

# 打印 sql 语句
spring.jpa.show-sql= true
# 自动创建表
spring.jpa.properties.hibernate.hbm2ddl.auto=create
# 默认创建的mysql表为  MyISAM 引擎修改为InnoDB问题
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL55Dialect
```

> spring.jpa.properties.hibernate.hbm2ddl.auto 参数说明：
>
> - create：每次运行会先删除对应的表，通过实体类重新生成表。请勿再生产环境。
> - create-drop：sessionFactory 关闭时会清请空表中的数据。
> - update：运行时如果没有表会自动创建表，只会更新表内数据不会请空数据。（推荐使用）
> -  validate：运行时验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。

### 实体类映射数据库表

**user 实体类**

```java
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = -390763540622907853L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer age;

    // 省略构造器 set/get		

}
```

`@Entity` 定义对象将会成为被JPA管理的实体，将映射到指定的数据库表。

`@Id` 定义属性为数据库的主键，一个实体里面必须有一个。

`@GeneratedValue(strategy = GenerationType.IDENTITY)` 自增长 ID 策略

**继承 CrudRepository**

```java
public interface UserCrudRepository extends CrudRepository<User, Long> {
}

```

> CrudRepository<实体类，主键>

**CURD 测试类**

> 路径：src/test/java/com/mtcarpenter/chapter1/repository/UserCrudRepositoryTest.java

```java
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCrudRepositoryTest {

    /**
     * ⽇志对象
     */
    private Logger logger = LoggerFactory.getLogger(UserCrudRepositoryTest.class);

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Test
    public void save() {
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("小米", 9)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("张三", 16)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("三哥", 12)));
        logger.info("新增数据 result = {}", userCrudRepository.save(new User("米二", 8)));
    }

    @Test
    public void edit() {
        logger.info("编辑用户 result = {}", userCrudRepository.save(new User(3L, "三三", 16)));
    }

    @Test
    public void delete() {
        userCrudRepository.deleteById(3L);
    }

    @Test
    public void findById() {
        logger.info("通过 id 查询 result = {}",userCrudRepository.findById(1L));
    }


    @Test
    public void findAll(){
        logger.info("查询所有记录   result = {}",userCrudRepository.findAll());
    }


}

```

输出日志：

```java
Hibernate: insert into user (age, name) values (?, ?)
2020-02-29 13:18:36.809  INFO 48452 --- [           main] c.m.c.repository.UserCrudRepositoryTest  : 新增数据 result = User{id=1, name='小米', age=9}
Hibernate: insert into user (age, name) values (?, ?)
2020-02-29 13:18:36.828  INFO 48452 --- [           main] c.m.c.repository.UserCrudRepositoryTest  : 新增数据 result = User{id=2, name='张三', age=16}
Hibernate: insert into user (age, name) values (?, ?)
2020-02-29 13:18:36.848  INFO 48452 --- [           main] c.m.c.repository.UserCrudRepositoryTest  : 新增数据 result = User{id=3, name='三哥', age=12}
Hibernate: insert into user (age, name) values (?, ?)
2020-02-29 13:18:36.861  INFO 48452 --- [           main] c.m.c.repository.UserCrudRepositoryTest  : 新增数据 result = User{id=4, name='米二', age=8}

```

`spring.jpa.show-sql= true` 开启之后会再控制台输出 SQL 语句，在日常测试环境也能提高一定的开发效率。

>  源码地址 github：https://github.com/mtcarpenter/spring-data-chapter/tree/master/chapter1