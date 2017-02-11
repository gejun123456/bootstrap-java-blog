<html>
<head>
<#include "head_header.ftl">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-12 col-md-12 blog-main">
            <div id="blogdata">
            <#--adddata to this position-->
            <#list tagContentVos as tagVo>
                <div style="margin: 40px;">
                    <img src="/static/img/calendar%20(1).png"> ${tagVo.addTime}<a href="${tagVo.contentUrl}"> >> <img src="/static/img/folded-newspaper%20(1).png"> ${tagVo.contentTitle} </a>
                </div>
            </#list>

            <#--test build.-->
            </div>
</body>
<#include "footerjs.ftl">

<script type="text/javascript">
    $(document).ready(function () {
        $("#_tagLink").parent().addClass("active");
    });
</script>
</html>
