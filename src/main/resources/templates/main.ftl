<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" placeholder="Search by Client">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>


<a class="btn btn-primary" data-toggle="collapse" href="#collapseClient" role="button" aria-expanded="false" aria-controls="collapseClient">
    Add new Client
</a>

<div class="collapse" id="collapseClient">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control" name="name" placeholder="Введите название клиента">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" name="description" placeholder="Краткое описание">
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>


<div class="card-columns">
    <#list clients as client>
    <div class="card my-3">
        <div>
            <#if client.filename??>
                <img src="/images/${client.filename}" class="card-img-top">
            </#if>
        </div>
        <div class="m-2">
            <i>${client.description}</i>
            <b>${client.managerName}</b>
        </div>
        <div class="card-footer text-muted">
        ${client.name}
        </div>
    </div>
    <#else> No Client
    </#list>
</div>
</@c.page>