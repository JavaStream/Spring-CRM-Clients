<#import "parts/common.ftl" as c>

<@c.page>
<#if isCurrentUser>
    <#include "parts/clientEdit.ftl" />
</#if>

    <#include "parts/clientList.ftl" />

</@c.page>