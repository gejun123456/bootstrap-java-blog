<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    var locale = getCookie("LOCALE_KEY");
    //    when cookie not contain use the default lang
    if (locale != null) {
        locale = locale.replace(/\"/g, "");
    } else {
        if(navigator.language!=undefined) {
            locale = navigator.language;
        } else if (navigator.languages != undefined) {
            locale = navigator.languages[0];
        }
        if (locale != null) {
            locale = locale.replace(/-/g, "_");
        }
    }
    $.holdReady(true);
    if (locale == "zh_CN") {
        $.getScript("/static/js/i18n/zh_CN.js", ready());
    } else if (locale == "en_US") {
        $.getScript("/static/js/i18n/en_US.js", ready());
    } else if (locale == "zh_TW" || locale == "zh_HK") {
        $.getScript("/static/js/i18n/zh_TW.js", ready());
    } else {
        $.getScript("/static/js/i18n/en_US.js", ready());
    }

    function getCookie(name) {
        var arr = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
        if (arr != null) {
            return unescape(arr[2]);
        } else {
            return null;
        }
    }

    function ready() {
        $.holdReady(false);
    }


    function geti18n(name) {
        var i18name =
                messageStrings[name];
        if (i18name != null) {
            return i18name;
        } else {
//            console.log("can't find i18n for " + name);
            return name;
        }
    }
</script>

<#--<script>-->
<#--$(document).ready(function () {-->
<#--console.log(messageStrings.userNameExist);-->
<#--});-->
<#--</script>-->

