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
            ${aboutContent}
            <#--test build.-->
            </div>
</body>
        <#include "footerjs.ftl">

<script type="text/javascript">
    $(document).ready(function () {
        $("#_aboutLink").parent().addClass("active");
    });
</script>
</html>
