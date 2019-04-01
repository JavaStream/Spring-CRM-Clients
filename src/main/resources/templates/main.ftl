<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout />
        <span><a href="/user">User list</a></span>
    </div>
    <div>
        <form method="post">
            <input type="text" name="name" placeholder="Введите название клиента">
            <input type="text" name="description" placeholder="Краткое описание">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Добавить</button>
        </form>
    </div>

    <div>
        <form method="get" action="/main">
            <input type="text" name="filter">
            <button type="submit">Найти</button>
        </form>
    </div>


    <div>Список клиентов </div>
<#list clients as client>
        <div>
            <b>${client.id}</b>
            <span>${client.name}</span>
            <i>${client.description}</i>
            <b>${client.managerName}</b>
        </div>
</#list>
</@c.page>