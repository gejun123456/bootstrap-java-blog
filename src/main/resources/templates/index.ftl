<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Blog Template for Bootstrap</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
    <link href="/css/search.css" rel="stylesheet">
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
        <#list contents as co>
            <div class="blog-post">
                <h2 class="blog-post-title">${co.title}</h2>
                <p class="blog-post-meta">${co.startDate}</p>${co.content}
            </div>
        </#list>
            <nav>
                <ul class="pager">
                <#if previousLink??>
                    <li id="previous"><a href="${previousLink}">Previous</a></li>
                </#if>
                <#if nextLink??>
                    <li id="next"><a href="${nextLink}">Next</a></li>
                </#if>
                </ul>
            </nav>
        </div>
        <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
            <div class="container">
                <div class="row">
                    <div class="search">
                        <form action="/search" onsubmit="return validate();">
                            <input type="text" id="query" name="query" class="form-control input-sm" placeholder="Search"/>
                        </form>
                    </div>
                </div>
            </div>
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