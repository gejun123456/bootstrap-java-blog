<html>
<head>
<#include "head_header.ftl">
    <link href="/static/css/login.css" rel="stylesheet">
    <link href="/static/css/markdown.css" rel="stylesheet">
</head>
<div class="container">
<#--todo shall fix the page.-->
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="#" class="active" id="login-form-link"><@spring.message "login"/></a>

                        </div>

                        <div class="col-xs-6">
                            <a href="#" id="register-form-link"><@spring.message "register"/></a>

                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <form id="login-form" action="/login" method="post" role="form">
                                <div class="form-group">
                                    <input type="text" name="username" id="username" tabindex="1" class="form-control"
                                           placeholder="<@spring.message "username"/>" value="" required="true">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "password"/>"
                                           required="true">
                                </div>
                            <#--the default is remember-->
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> <@spring.message "remember"/></label>
                                </div>
                            <#if logindd??>
                                <div>
                                <#--need to fix with-->
                                    <p class="text-danger text-center">${logindd}</p>
                                </div>

                            </#if>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="login-submit" tabindex="4"
                                                   class="form-control btn btn-login"
                                                   value="<@spring.message "login"/>">
                                        </div>
                                    </div>
                                </div>
                            <#--<div class="form-group">-->
                            <#--<div class="row">-->
                            <#--<div class="col-lg-12">-->
                            <#--<div class="text-center">-->
                            <#--<a href="http://phpoll.com/recover" tabindex="5" class="forgot-password">Forgot Password?</a>-->
                            <#--</div>-->
                            <#--</div>-->
                            <#--</div>-->
                            <#--</div>-->
                            </form>

                            <form id="register-form" action="/register" method="post" role="form"
                                  style="display: none;">
                                <div class="form-group">
                                    <input type="text" name="username" id="signup_username" tabindex="1"
                                           class="form-control" placeholder="<@spring.message "username"/>" value=""
                                           required="true">
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="signup_password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "password"/>"
                                           required="true">
                                </div>

                                <div class="form-group">
                                    <input type="email" name="email" id="signup_password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "email"/>"
                                           required="true" minlength="5">
                                </div>
                            <#--todo can implement more like mobile phone number-->
                            <#--the default is remember-->
                            <#--<div class="form-group text-center">-->
                            <#--<input type="checkbox" tabindex="3" class="" username="remember" id="remember">-->
                            <#--<label for="remember"> Remember Me</label>-->
                            <#--</div>-->
                                <p class="text-center text-danger" id="register-warn" style="display: none"></p>
                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-6 col-sm-offset-3">
                                            <input type="submit" name="login-submit" id="register-submit" tabindex="4"
                                                   class="form-control btn btn-login"
                                                   value="<@spring.message "register"/>">
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<body>
</body>
<#include "footerjs.ftl">
<script type="text/javascript">
    $(document).ready(function () {
        $('#login-form-link').click(function (e) {
            $("#register-form").hide();
            $("#login-form").show();
            $('#register-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });
        $('#register-form-link').click(function (e) {
            $("#login-form").hide();
            $("#register-form").show();
            $('#login-form-link').removeClass('active');
            $(this).addClass('active');
            e.preventDefault();
        });

        $("#register-form").submit(function (e) {
            e.preventDefault();
            if (!$("#register-form").valid()) {
                return;
            }
            $.ajax({
                type: 'POST',
                data: $("#register-form").serialize(),
                url: '/register',
                success: function (response) {
                    if (response.code != 200) {
                        $("#register-warn").html(geti18n(response.msg));
                        $("#register-warn").show();
                    } else {
                        window.location.href = "/";
                    }
                }
            })
        })


        $("#register-form").validate();
        $("#login-form").validate();
    })


</script>




</html>