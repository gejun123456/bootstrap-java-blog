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
            <div>
            <#if comment??>
                <form id="commentform" action="/comment/${vo.id}">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" style="width:200;" placeholder="name" required="true"/>
                        <textarea name="content" class="form-control" rows="3" required="true" placeholder="please input comment"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default">Send</button>
                </form>
                <div>
                    <#list commentList as comm>
                        <p>name:${comm.name}</p>
                        <p>comment:${comm.comment}</p>
                        <p>time:${comm.ago}</p>
                    </#list>
                </div>
            </#if>
            </div>
        </div>

    </div>
</div>
<#include "footer.ftl">
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/jquery.validate.min.js"></script>

</body>
</html>