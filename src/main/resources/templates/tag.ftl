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
            <#list tags as tag>
            <div style="margin: 40px;">
                <img src="/static/img/price-tag.png"><a id="tag${tag.id}">${tag.tagName}</a>
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
