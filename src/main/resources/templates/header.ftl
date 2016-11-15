<div class="blog-masthead">
    <div class="container">
        <div class="row">
            <div class="col-md-8">
                <nav>
                    <a class="blog-nav-item" href="/">Home</a>
                    <a class="blog-nav-item" id="archives" href="/archive">archives</a>
                    <a class="blog-nav-item" id="about" href="/about">about</a>
                <#--<a class="blog-nav-right" id="login" href="/login">write</a>-->
                </nav>
            </div>
            <div class="col-md-4">
                    <#if login??>
                    <a class="blog-nav-item" id="my" href="/add">add</a>
                    <#else>
                    <a class="blog-nav-item" id="login" href="/loginPage">login</a>
                    </#if>
                    <form class="blog-header-form" action="/search" onsubmit="return validate();">
                        <input type="text" id="query" name="query" class="form-control" placeholder="Search"/>
                    </form>
            </div>
        </div>
    </div>
</div>
<br>
