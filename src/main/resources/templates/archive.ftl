<html>
<head>
<#include "head_header.ftl">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 blog-main">
        <#list years as year>
            <div>
                <h2>${year}</h2>
            </div>
            <#list archiveMap[year] as yearac>
            <div>
            <img src="/static/img/calendar%20(1).png"> ${yearac.adddate} >> <a href="${yearac.link}"><img src="/static/img/folded-newspaper%20(1).png"> ${yearac.title}</a>
                <#if admin??>
                    <a href="/edit/${yearac.id}"><button class="btn btn-warning"> <@spring.message "edit"/></button></a>
                    <a href="/delete/${yearac.id}"><button class="btn btn-danger"> <@spring.message "delete"/></button></a>
                </#if>
            <div>
            </#list>
        </#list>
        </div>
        </div>
        </div>
</body>
<#include "footerjs.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        $("#_archiveLink").parent().addClass("active");
    });
</script>
</html>
