## a frame

> a frame create by sven

### 主要技术栈
1. springboot
2. mybatis
3. shiro
4. layui
5. thymeleaf
6. tk-mybatis


### 相关结构说明

#### dao
mapper接口类继承通用mapper、和处理多表关联相关操作

dao接口类负责主要接口暴露和供service操作

dao实现类中关联mapper，操作数据库

lib

```$xslt
mvn install:install-file -DgroupId=org.apache.poi -DartifactId=poi-ooxml -Dversion=4.1.1 -Dpackaging=jar -Dfile=./poi-ooxml-4.1.1.jar

mvn install:install-file -DgroupId=org.apache.poi -DartifactId=poi-ooxml-schemas -Dversion=4.1.1 -Dpackaging=jar -Dfile=./poi-ooxml-schemas-4.1.1.jar

mvn install:install-file -DgroupId=org.apache.xmlbeans -DartifactId=xmlbeans -Dversion=3.1.0 -Dpackaging=jar -Dfile=./xmlbeans-3.1.0.jar

mvn install:install-file -DgroupId=com.github.virtuald -DartifactId=curvesapi -Dversion=1.06 -Dpackaging=jar -Dfile=./curvesapi-1.06.jar

```