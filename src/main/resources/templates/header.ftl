<nav class="navbar navbar-default">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Brand</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="/"><@spring.message "home"/> <span class="sr-only">(current)</span></a></li>
                <li><a href="/archive"><@spring.message "archives"/></a></li>
                <li><a href="/about"><@spring.message "about"/></a></li>
            </ul>
            <form class="navbar-form navbar-right" id="searchForm" action="/search" onsubmit="return validate();">
                <div class="form-group">
                    <input type="text" name="query" class="form-control" placeholder="<@spring.message "Search"/>">
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="/admin"><@spring.message "admin"/></a></li>
                <li>
                    <span class="dropdown" ">
                    <button class="dropdown-toggle" style="padding: 10px;border: none;background-color: inherit"
                            type="button" id="dropdownMenu1"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    <#assign thelocale><@spring.message code=".locale"/></#assign>
                    <#if thelocale == "zh_CN">
                        <img src="https://www.v2ex.com/static/img/lang_zhcn_32.png"/>
                    <#elseif thelocale =="en_US">
                        <img src="https://www.v2ex.com/static/img/lang_enus_32.png"/>
                    <#elseif thelocale == "zh_TW">
                        语言
                    <#else>
                        <img src="https://www.v2ex.com/static/img/lang_enus_32.png"/>
                    </#if>
                        <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                        <li><a href="/changeLanguage?language=en_US"><img
                                src="https://www.v2ex.com/static/img/lang_enus_32.png"/></a></li>
                        <li><a href="/changeLanguage?language=zh_CN"><img
                                src="https://www.v2ex.com/static/img/lang_zhcn_32.png"/></a></li>
                        <li><a href="/changeLanguage?language=zh_TW">繁体</a></li>
                    </ul>
                    </span>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>


<#--<div class="blog-masthead">-->
    <#--<div class="container">-->
        <#--<div class="row">-->
            <#--<div class="col-md-7">-->
                <#--<nav>-->
                    <#--<a class="blog-nav-item" href="/"><@spring.message "home"/></a>-->
                    <#--<a class="blog-nav-item" id="archives" href="/archive"><@spring.message "archives"/></a>-->
                    <#--<a class="blog-nav-item" id="about" href="/about"><@spring.message "about"/></a>-->
                <#--&lt;#&ndash;<a class="blog-nav-right" id="login" href="/login">write</a>&ndash;&gt;-->
                <#--</nav>-->
            <#--</div>-->
            <#--<div class="col-md-5">-->
                <#--<div class="row">-->
                <#--&lt;#&ndash;<div class="col-md-3">&ndash;&gt;-->
                <#--<#if login??>-->
                    <#--<a class="blog-nav-item" id="my" href="/admin"><@spring.message "manage"/></a>-->
                    <#--<a class="blog-nav-item" id="logout" href="/logout"><@spring.message "logout"/></a>-->
                <#--<#else>-->
                    <#--<a class="blog-nav-item" id="login" href="/loginPage"><@spring.message "manage"/></a>-->
                <#--</#if>-->
                <#--&lt;#&ndash;</div>&ndash;&gt;-->

                <#--&lt;#&ndash;<div class="col-md-6">&ndash;&gt;-->
                    <#--<form class="blog-header-form" action="/search"-->
                    <#--">-->
                    <#--<input type="text" id="query" name="query" class="form-control"-->
                           <#--placeholder="<@spring.message "Search"/>"/>-->
                    <#--</form>-->
                <#--&lt;#&ndash;</div>&ndash;&gt;-->

                <#--&lt;#&ndash;<div class="col-md-3">&ndash;&gt;-->

                <#--</div>-->
            <#--</div>-->
        <#--</div>-->
    <#--</div>-->
<#--</div>-->
<br>
