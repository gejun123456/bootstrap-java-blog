<div class="blog-masthead">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <nav>
                    <a class="blog-nav-item" href="/"><@spring.message "home"/></a>
                    <a class="blog-nav-item" id="archives" href="/archive"><@spring.message "archives"/></a>
                    <a class="blog-nav-item" id="about" href="/about"><@spring.message "about"/></a>
                <#--<a class="blog-nav-right" id="login" href="/login">write</a>-->
                </nav>
            </div>
            <div class="col-md-4">
            <#if login??>
                <a class="blog-nav-item" id="my" href="/add"><@spring.message "add"/></a>
                <a class="blog-nav-item" id="logout" href="/logout"><@spring.message "logout"/></a>
            <#else>
                <a class="blog-nav-item" id="login" href="/loginPage"><@spring.message "login"/></a>
            </#if>
                <form class="blog-header-form" action="/search" onsubmit="return validate();">
                    <input type="text" id="query" name="query" class="form-control" placeholder="<@spring.message "Search"/>"/>
                </form>
            </div>
        </div>
    </div>
</div>
<br>
