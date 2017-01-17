<html>
<head>
<#--<#include "head_header.ftl">-->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300i,400" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/static/css/admin.css"/>
</head>
<body>
<div id="header">
    <div class="logo">
        admin panel
    </div>
    <span class="mypanel">
        <button><img src="/static/img/logout.png"/><span>logout</span></button>
        <button><img src="/static/img/email.png"/></button>

    </span>
</div>

<div id="sidebar">
    <ul>
        <li><a href="#"><img src="/static/img/dashboard.png"/>DashBoard</a> </li>
        <li><a href="/add"><img src="/static/img/add_content.png"/>addContent</a></li>
        <li><a href="#"><img src="/static/img/edit-content.png"/>editContent</a></li>
        <li><a href="#"><img src="/static/img/tag.png"/>tags</a></li>
        <li><a href="#"><img src="/static/img/rubbish-bin.png"/>deleted</a></li>
    </ul>
</div>

<div id="content">
    <div class="dashboard">
        <center>good to see you, bro</center>
    </div>
</div>
</body>
<#--<#include "footerjs.ftl">-->
</html>