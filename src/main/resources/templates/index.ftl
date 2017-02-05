<html>
<head>
<#include "head_header.ftl">
</head>
<body>
<#include "header.ftl">
<div class="container">

    <div class="row">
        <div class="col-sm-12 col-md-12 blog-main">
        <#--<div>-->
        <#--<h1><a href="/">&lt;Bruce ge&gt</a></h1>-->
        <#--</div>-->
        <#--adddata to this position-->
        <#list contents as co>
            <div class="blog-post">
                <div id="blogTitle">
                    <h2><a href="/getArticle/${co.id}"><img width="32" src="/static/img/folded-newspaper%20(1).png">${co.title}</a></h2>
                Post By Bruce <img src="/static/img/calendar%20(1).png"> ${co.startDate} <img src="/static/img/price-tag.png">language
                </div>
            <#--<p class="blog-post-meta">-->
            <#--<#if admin??>-->
            <#--<a href="/edit/${co.id}">[<@spring.message "edit"/>]</a>-->
            <#--<a href="/delete/${co.id}">[<@spring.message "delete"/>]</a>-->
            <#--</#if>-->
            <#--</p>-->
                <div id="blogContent">
                ${co.content}
                </div>
            </div>
        </#list>
            <nav>
                <ul class="pager">
                <#if previousLink??>
                    <li id="previous"><a href="${previousLink}"><@spring.message "Previous"/></a></li>
                </#if>
                <#if nextLink??>
                    <li id="next"><a href="${nextLink}"><@spring.message "Next"/></a></li>
                </#if>
                </ul>
            </nav>
        </div>


    </div>
</div>
</body>
<#include "footerjs.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        $("#_indexLink").parent().addClass("active");
    });
</script>

</html>
