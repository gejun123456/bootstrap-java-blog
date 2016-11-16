# [bootstrap-java-blog](https://brucege.com) 

[![Build Status](https://travis-ci.org/gejun123456/my-java-blog.svg?branch=master)](https://travis-ci.org/gejun123456/my-java-blog)
<h1>base on bootstrap and java</h1>  
Requirements
------------
- git
- jdk8
- maven
- mysql

That's all, base package of java web environment!

Installation
------------
- install mysql set a user with username='root' password='root'
- run the ddl in mysql from [https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql](https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql "https://github.com/gejun123456/bootstrap-java-blog/blob/master/ddl.sql")
- git clone https://github.com/gejun123456/bootstrap-java-blog.git  
- cd bootstrap-java-blog
- mvn clean install
- java -jar target/attchment.jar
- open localhost:8080 to see if works right.

Quick Start
-------------
- register a user (the first user you create will be admin)
- do everything in the next.

To Do List
----------

- add multi language support
- to see if need add comment
- to see if need close register after admin create.

Custom
--------
- you can use a different port like java -jar target/attchment.jar --server.port=9090

Screenshots
-----------
![index](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/index.png)
![register](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/register.png)

![search](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/search.png)

![add_content](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/add_content.png)
![archive](https://github.com/gejun123456/bootstrap-java-blog/blob/master/screencut/archive.png)