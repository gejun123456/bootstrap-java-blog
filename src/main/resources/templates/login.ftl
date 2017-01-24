<html>
<head>
<#include "head_header.ftl">
    <link href="/static/css/login.css" rel="stylesheet">
    <link href="/static/css/markdown.css" rel="stylesheet">
    <link href="/static/css/font-awesome.css" rel="stylesheet">
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

                                    <div id="login_error_username" class="alert alert-danger  collapse"
                                         role="alert">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "password"/>"
                                           required="true">

                                    <div id="login_error_password" class="alert alert-danger  collapse"
                                         role="alert">
                                    </div>
                                </div>
                            <#--the default is remember-->
                                <div class="form-group text-center">
                                    <input type="checkbox" tabindex="3" class="" name="remember" id="remember">
                                    <label for="remember"> <@spring.message "remember"/></label>
                                </div>
                            <#--<#if logindd??>-->
                            <#--<div>-->
                            <#--&lt;#&ndash;need to fix with&ndash;&gt;-->
                            <#--<p class="text-danger text-center">${logindd}</p>-->
                            <#--</div>-->

                            <#--</#if>-->

                                <div id="login_error_validate" class="alert alert-danger  collapse"
                                     role="alert">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span></button>
                                    <span id="login_error_validate_text"></span>
                                </div>


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
                                <#--<i class="glyphicon glyphicon-user"></i>-->
                                    <input type="text" name="username" id="signup_username" tabindex="1"
                                           class="form-control" placeholder="<@spring.message "username"/>" value=""
                                           required="true">
                                    <div id="register_error_username"
                                         class="alert alert-danger  collapse"
                                         role="alert">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="password" name="password" id="signup_password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "password"/>"
                                           required="true">

                                    <div id="register_error_password"
                                         class="alert alert-danger  collapse"
                                         role="alert">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <input type="email" name="email" id="signup_password" tabindex="2"
                                           class="form-control" placeholder="<@spring.message "emailPlaceHolder"/>"
                                           required="true" minlength="5" maxlength="50">

                                    <div id="register_error_email" class="alert alert-danger  collapse"
                                         role="alert">
                                    </div>
                                </div>

                                <div id="register_error_validate" class="alert alert-danger  collapse"
                                     role="alert">
                                </div>

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
                    alert("success");
                    window.location.href = "/";
                },
                error: function (response) {
                    var errorButton = "<button type=\"button\" class=\"close\" onclick=\"$('.alert').hide()\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
                    if (response.status == 400) {
                        var errorVm = jQuery.parseJSON(response.responseText);
                        if (errorVm.message == "error.validation") {
                            for (var i in errorVm.fieldErrors) {
                                var fieldError = errorVm.fieldErrors[i];
                                var fieldErrorDivId = "#register_error_" + fieldError.field;
                                var errorDiv = $(fieldErrorDivId);
                                $(fieldErrorDivId).html(errorButton + fieldError.defaultMessage);
                                errorDiv.show();
                                setTimeout(function () {
                                    errorDiv.hide();
                                }, 4000);
                            }
                        } else if (errorVm.message == "error.userAlreadyExist") {
                            $("#register_error_validate").html(errorButton + geti18n(errorVm.description))
                            $("#register_error_validate").show();
                            setTimeout(function () {
                                $("#register_error_validate").hide();
                            }, 4000);
                        }
                    } else if (response.status = 500) {
                        $("#register_error_validate").html(errorButton + geti18n("systemError"));
                        $("#register_error_validate").show();
                        setTimeout(function () {
                            $("#register_error_validate").hide();
                        }, 4000);
                    }
                    //todo when param bind fail.
                }
            })
        })


        $("#login-form").submit(function (e) {
            e.preventDefault();
            $("#login-form :submit").attr("disabled", true);
            if (!$("#login-form").valid()) {
                return;
            }
            $.ajax({
                type: 'POST',
                data: $("#login-form").serialize(),
                url: '/login',
                success: function (response) {
                    console.log(response);
                    $("#login-form :submit").removeAttr("disabled");
                },
                error: function (response) {
                    var errorButton = "<button type=\"button\" class=\"close\" onclick=\"$('.alert').hide()\" aria-label=\"Close\"><span aria-hidden=\"true\">&times;</span></button>";
                    $("#login-form :submit").removeAttr("disabled");
                    if (response.status == 400) {
                        console.log(response.responseText);
                        var errorVm = jQuery.parseJSON(response.responseText);
                        if (errorVm.message == "error.validation") {
                            for (var i in errorVm.fieldErrors) {
                                var fieldError = errorVm.fieldErrors[i];
                                var fieldErrorDivId = "#login_error_" + fieldError.field;
                                var errorDiv = $(fieldErrorDivId);
                                $(fieldErrorDivId).html(errorButton + fieldError.defaultMessage);
                                errorDiv.show();
                                setTimeout(function () {
                                    errorDiv.hide();
                                }, 4000);
                            }
                        } else if (errorVm.message == "error.userNotExist") {
                            $("#login_error_validate").html(errorButton + geti18n("userNotExist"));
                            $("#login_error_validate").show();
                            setTimeout(function () {
                                $("#login_error_validate").hide();
                            }, 4000);
                        }
                    } else if (response.status = 500) {
                        $("#login_error_validate").html(errorButton + geti18n("systemError"));
                        $("#login_error_validate").show();
                        setTimeout(function () {
                            $("#login_error_validate").hide();
                        }, 4000);
                    }
                    //todo when param bind fail.
                }
            })
        })

        $("#register-form").validate();
        $("#login-form").validate();
    })


</script>


</html>
