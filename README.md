# [bootstrap-java-blog](https://brucege.com) 

[![Build Status](https://travis-ci.org/gejun123456/bootstrap-java-blog.svg?branch=master)](https://travis-ci.org/gejun123456/bootstrap-java-blog)
<h1>base on bootstrap and java</h1>  

[中文说明](https://github.com/gejun123456/bootstrap-java-blog/blob/master/doc/chinesereadme.md)

feature
------------
- bootstrap simple style, good device compatibility
- just in time support for markdown to add or edit.
- full search ability.
- nice for mobile add or edit.

Requirements
------------
- git
- jdk8
- maven
- mysql

That's all, base package of java web environment!

Installation
------------
- get your mysql installed.
- run the ddl in mysql from [https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql](https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql "https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql") to your database.
- git clone https://github.com/gejun123456/bootstrap-java-blog.git  
- config your database and language in `src\main\resources\application.properties`
- cd bootstrap-java-blog
- mvn clean package
- java -jar target/attchment-1.0.jar
- open localhost:8080 to see if works right.

Quick Start
-------------
- register a user (the first user you create will be admin)
- do everything in the next.

To Do List
----------

- to see if need add comment
- to see if need close register after admin create.

Custom
--------
- you can use a different port like java -jar target/attchment.jar --server.port=9090
- edit your own about in `src\main\resources\templates\about.ftl`

Screenshots
-----------
![index](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/index.png)
![register](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/register.png)

![search](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/search.png)

![add_content](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/add_content.png)
![archive](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/archive.png)


Discussion
-----------
email:gejun123456@gmail.com
qq:1098632410@qq.com