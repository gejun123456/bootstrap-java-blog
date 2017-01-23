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
            <div id="blogdata">
            <#--adddata to this position-->
            ${aboutContent}

            <#if edit??>
                <a href="/editAbout">edit</a>
            </#if>
            <#--test build.-->
            </div>
</body>
        <#include "footerjs.ftl">
</html>