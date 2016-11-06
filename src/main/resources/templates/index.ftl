<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Blog Template for Bootstrap</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-8 blog-main">
            <#--<div>-->
                <#--<h1><a href="/">&lt;Bruce ge&gt</a></h1>-->
            <#--</div>-->
            <div id="blogdata">
            <#--adddata to this position-->
            <#list contents as co>
            <div class="blog-post">
                <h2 class="blog-post-title">${co.title}</h2>
                <p class="blog-post-meta">${co.startDate}</p>
            ${co.content}
                <br>
            </#list>
            </div>

                <nav>
                    <ul class="pager">
                    <#if previousLink??>
                        <li id="previous"><a href="${previousLink}">Previous</a></li>
                    </#if>
                    <#if nextLink??>
                        <li id="next"><a href="${nextLink}">Next</li>
                    </#if>
                    </ul>
                </nav>
            </div>

            <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
                <div class="sidebar-module">
                </div>
            </div>
        </div>
    </div>
    <script src="/js/jquery-3.1.1.min.js"></script>
</body>
</html>