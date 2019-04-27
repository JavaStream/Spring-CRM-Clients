<#include "security.ftl">

<div class="card-columns">
<#list clients as client>
    <div class="card my-3">
        <div>
            <#if client.filename??>
                <img src="/images/${client.filename}" class="card-img-top">
            </#if>
        </div>
        <div class="m-2">
            <i>${client.description}</i><br>
            <b>#${client.managerName}</b>
        </div>
        <div class="card-footer text-muted">
        <a href="/user-clients/${client.manager.id}">${client.managerName}</a>
        <#if client.manager.id == currentUserId>
            <a class="btn btn-primary" href="/user-clients/${client.manager.id}?client=${client.id}">Edit</a>
        </#if>
        </div>
    </div>
<#else> No Client
</#list>
</div>