<html>
<head>
<#include "head_header.ftl">
    <link href="/css/markdown.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
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
        </div>
    </div>
</div>
<#include "footer.ftl">
<script src="/js/jquery-3.1.1.min.js"></script>
</body>
</html>