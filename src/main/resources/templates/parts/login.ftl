<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group">
            <div class="col-md-2">
                <label for="inputUsername"> User Name :</label>
            </div>
            <div class="col-md-6">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid', ' ')}"
                value="<#if user??>${user.username}</#if>" id="inputUsername" placeholder="Enter user name"/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>

        </div>
        <div class="form-group">
            <div class="col-md-2">
                <label for="inputPassword"> Password: </label>
            </div>
            <div class="col-md-6">
                <input type="password" class="form-control ${(passwordError??)?string('is-invalid', ' ')}" name="password" id="inputPassword" placeholder="Password"/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                    ${passwordError}
                    </div>
                </#if>
            </div>
        </div>

        <#if isRegisterForm>
            <div class="form-group">
                <div class="col-md-2">
                    <label for="inputPassword2"> Password Confirmation: </label>
                </div>
                <div class="col-md-6">
                    <input type="password2" class="form-control ${(password2Error??)?string('is-invalid', ' ')}" name="password2" id="inputPassword2" placeholder="Retype password"/>
                    <#if password2Error??>
                        <div class="invalid-feedback">
                        ${password2Error}
                        </div>
                    </#if>
                </div>
            </div>

        <div class="form-group">
            <div class="col-md-2">
                <label for="inputEmail"> Email: </label>
            </div>
            <div class="col-md-6">
                <input type="email" class="form-control ${(emailError??)?string('is-invalid', ' ')}"
                       value="<#if user??>${user.email}</#if>" name="email" id="inputEmail" placeholder="some@some.com"/>
                <#if emailError??>
                    <div class="invalid-feedback">
                    ${emailError}
                    </div>
                </#if>
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
