<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <div class="col-md-2">
                <label for="inputUsername"> User Name :</label>
            </div>
            <div class="col-md-6">
                <input type="text" name="username" class="form-control" id="inputUsername" placeholder="Enter user name"/>
                <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
            </div>

        </div>
        <div class="form-group">
            <div class="col-md-2">
                <label for="inputPassword"> Password: </label>
            </div>
            <div class="col-md-6">
                <input type="password" class="form-control" name="password" id="inputPassword" placeholder="Password"/>
            </div>
        </div>

        <#if isRegisterForm>
        <div class="form-group">
            <div class="col-md-2">
                <label for="inputEmail"> Email: </label>
            </div>
            <div class="col-md-6">
                <input type="email" class="form-control" name="email" id="inputEmail" placeholder="some@some.com"/>
            </div>
        </div>
        </#if>

            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#if !isRegisterForm><a href="/registration" class="ml-3">Add new user</a></#if>
            <button  type="submit" class="btn btn-primary ml-3"><#if isRegisterForm>Create <#else>Sign in </#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button  type="submit" class="btn btn-primary ml-3">Sign Out</button>
    </form>
</#macro>
