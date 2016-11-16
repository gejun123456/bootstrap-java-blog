<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>brucege's blog</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
    <link href="/css/search.css" rel="stylesheet">
    <link href="/css/markdown.css" rel="stylesheet">
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
            <h2>Query result is empty</h2>
        <#else>
        <h1>There are ${resultlen} result</h1>
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
<#include "footer.ftl">
<script type="text/javascript">
    function validate() {
        //If the form value is "" (nothing)
        if (document.getElementById("query").value == "") {
            return false; //Stop the form from submitting
        }
        return true;
    }
</script>
<script src="/js/jquery-3.1.1.min.js"></script>
</body>
</html>