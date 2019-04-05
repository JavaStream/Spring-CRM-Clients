<#import "parts/common.ftl" as c>

<@c.page>
<h5>${username}</h5>

${message?ifExists}

<form method="post">

    <div class="form-group">
        <div class="col-md-2">
            <label for="inputPassword"> Password: </label>
        </div>
        <div class="col-md-6">
            <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password"/>
        </div>
    </div>

        <div class="form-group">
            <div class="col-md-2">
                <label for="inputEmail"> Email: </label>
            </div>
            <div class="col-md-6">
                <input type="email" class="form-control" name="email" id="inputEmail" placeholder="some@some.com" value="${email!''}"/>
            </div>
        </div>


    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <button  type="submit" class="btn btn-primary ml-3">Save</button>
</form>

</@c.page>