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
                    <a href="${tagVo.contentUrl}">${tagVo.contentTitle} ${tagVo.addTime}</a>
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
