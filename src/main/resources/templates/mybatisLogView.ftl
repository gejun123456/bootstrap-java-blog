<html>
<body>
nimeiya
<#list contents as content>
    <li>className is:${content.className} messages are:${content.messages} userIp are:${content.userIp} createTime is:${content.createTime}</li>
</#list>
<#if previousLink??>
<button><a href="${previousLink}">previous page</a></button>
</#if>

<#if nextLink??>
<button><a href="${nextLink}">next page</a></button>
</#if>
</body>
</html>
