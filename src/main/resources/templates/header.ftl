<div class="blog-masthead">
    <div class="container">
        <div class="row">
            <div class="col-md-7">
                <nav>
                    <a class="blog-nav-item" href="/"><@spring.message "home"/></a>
                    <a class="blog-nav-item" id="archives" href="/archive"><@spring.message "archives"/></a>
                    <a class="blog-nav-item" id="about" href="/about"><@spring.message "about"/></a>
                <#--<a class="blog-nav-right" id="login" href="/login">write</a>-->
                </nav>
            </div>
            <div class="col-md-5">
                <div class="row">
                <#--<div class="col-md-3">-->
                <#if login??>
                    <a class="blog-nav-item" id="my" href="/admin"><@spring.message "manage"/></a>
                    <a class="blog-nav-item" id="logout" href="/logout"><@spring.message "logout"/></a>
                <#else>
                    <a class="blog-nav-item" id="login" href="/loginPage"><@spring.message "manage"/></a>
                </#if>
                <#--</div>-->

                <#--<div class="col-md-6">-->
                    <form class="blog-header-form" action="/search" onsubmit="return validate();">
                        <input type="text" id="query" name="query" class="form-control"
                               placeholder="<@spring.message "Search"/>"/>
                    </form>
                <#--</div>-->

                <#--<div class="col-md-3">-->
                    <span class="dropdown">
                            <button class="btn btn-default dropdown-toggle nopadding" type="button" id="dropdownMenu1"
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
                </div>
            </div>
        </div>
    </div>
</div>
<br>
