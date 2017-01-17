# [bootstrap-java-blog](https://brucege.com) 

[![Build Status](https://travis-ci.org/gejun123456/bootstrap-java-blog.svg?branch=master)](https://travis-ci.org/gejun123456/bootstrap-java-blog)
<h1>基于 bootstrap + java + lucene</h1>  

特点
---
- bootstrap简单的风格，拥有良好的设备兼容性
- 实时预览markdown
- 完整的搜索功能

运行要求
----
- git
- jdk8
- mysql

安装
---
- 安装mysql
- 运行 git clone https://github.com/gejun123456/bootstrap-java-blog.git  
- 在 `src\main\resources\application.properties` 配置数据库
- cd bootstrap-java-blog
- gradlew clean build -x test
- java -jar build/libs/bootstrap.javablog.jar
- 打开localhost:8080 查看是否运行正确

快速开始
----
- 注册一个用户(第一个注册的用户将会是管理员)
- 随意进行操作吧！

To Do list
----------
- 美化页面
- 加上tag

自定义
---
- 可以部署在不同的端口 例如 java -jar target/attchment.jar --server.port=9090
- 在文件 `src\main\resources\templates\about.ftl` 编辑自己的关于

截图
---
![base](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/gif/base.gif)
![add](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/gif/add.gif)


讨论
---
email:gejun123456@gmail.com
qq:1098632410@qq.com