<#import "parts/common.ftl" as c>

<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" placeholder="Search by Client">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>


<#include "parts/clientEdit.ftl" />
<#include "parts/clientList.ftl" />
</@c.page>