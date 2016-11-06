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
    <div class="blog-header">
        <h1 class="blog-title">The Bruce ge Blog</h1>
    </div>

    <div class="row">
        <div class="col-sm-8 blog-main">
            <div id="blogdata">
            <#--adddata to this position-->
            <div class="blog-post">
                <h2 class="blog-post-title">${vo.title}</h2>
                <p class="blog-post-meta">${vo.addtime}</p>
            ${vo.content}
        </div>
    </div>
    <script src="/js/jquery-3.1.1.min.js"></script>
</body>
</html>