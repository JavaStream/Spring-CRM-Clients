<a class="btn btn-primary" data-toggle="collapse" href="#collapseClient" role="button" aria-expanded="false" aria-controls="collapseClient">
    Client Editor
</a>

<div class="collapse <#if client??>show</#if>" id="collapseClient">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(nameError??)?string('is-invalid', ' ')}"
                       value="<#if client??>${client.name}</#if>" name="name" placeholder="Введите название клиента">
            <#if nameError??>
                <div class="invalid-feedback">
                ${nameError}
                </div>
            </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', ' ')}"
                       value="<#if client??>${client.description}</#if>" name="description" placeholder="Краткое описание">
            <#if descriptionError??>
                <div class="invalid-feedback">
                ${descriptionError}
                </div>
            </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <input type="hidden" name="id" value="<#if client??>${client.id}</#if>"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save client</button>
            </div>
        </form>
    </div>
</div>