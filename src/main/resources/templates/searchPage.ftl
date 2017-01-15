<html>
<head>
<#include "head_header.ftl">
    <link href="/static/css/markdown.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-8 blog-main">
        <#--<div>-->
        <#--<h1><a href="/">&lt;Bruce ge&gt</a></h1>-->
        <#--</div>-->
        <#--adddata to this position-->
            <br>
        <#if !hasContent>
            <h2><@spring.message "searchResultEmpty"/></h2>
        <#else>
        <h1><@spring.message "presearch"/> ${resultlen} <@spring.message "suffixsearch"/></h1>
        <br>
        <#list result as co>
            <h1><a href="${co.link}">${co.marktitle}</a> </h1>
            <p>${co.markContent}</p>
        <br>
        </#list>
        </#if>

        </div>
    </div>
</div>

<script type="text/javascript">
    function validate() {
        //If the form value is "" (nothing)
        if (document.getElementById("query").value == "") {
            return false; //Stop the form from submitting
        }
        return true;
    }
</script>
<#include "footerjs.ftl">
</body>
</html>