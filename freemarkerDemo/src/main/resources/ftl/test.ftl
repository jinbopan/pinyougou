<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Freemarker 测试</title>
    <#include "head.ftl">
</head>
<body>
<#-- 这是 freemarker 注释，不会输出到文件中 -->
<h1>${name}；${message?string}</h1>
<br/>
<hr/>
<br/>
<#--这 是一个注释-->
<#assign username="ssb">
${username}

<br/>
<hr/>
<br/>
<#assign student={"name":"tom","address":"小花园"}>
${student.name}住在${student.address}
<br/>
<hr/>
<br/>
<#assign student='{"name":"小军","address":"小花园"}'?eval>
${student.name}住在${student.address}
<br/>
<hr/>
<br/>
<#assign boolean=true>
<#if boolean>
这是一个真
<#else>
这是一个假
</#if>
<br/>
<hr/>
<br/>
<#list list as item>
    ${item.name}性别是
    <#assign bool=item.message>
    <#if bool>
男人
    <#else >
女人
    </#if>
</#list>
${list?size}条记录
<br/>
<hr/>
<br/>
${datetime?time}<br/>
${datetime?date}<br/>
${datetime?string("yyyy-MM-dd HH:mm:ss zzzz")}<br/>
${.now}
<br/>
<hr/>
<br/>
${number?c}
<br/>
<hr/>
<br/>
${aaa!"aaa为空时显示"}
变量bbb存在返回true,不存在为false:${bbb???string}
${name???string}
<br/>
<hr/>
<br/>
<br/>
<hr/>
<br/>
<br/>
<hr/>
<br/>
<br/>
<hr/>
<br/>
</body>
</html>