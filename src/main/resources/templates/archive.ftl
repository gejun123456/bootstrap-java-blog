<html>
<head>
<#include "head_header.ftl">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-8 blog-main">
        <#list years as year>
            <div>
                <h2>${year}</h2>
            </div>
            <#list archiveMap[year] as yearac>
                <p>${yearac.adddate} >> <a href="${yearac.link}">${yearac.title}</a>
                    <#if admin??>
                        <a href="/edit/${yearac.id}">[<@spring.message "edit"/>]</a>
                        <a href="/delete/${yearac.id}">[<@spring.message "delete"/>]</a>
                    </#if>
                </p>
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
