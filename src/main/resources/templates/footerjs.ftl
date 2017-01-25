<script src="/static/js/jquery-3.1.1.min.js"></script>
<script src="/static/js/bootstrap.js"></script>
<script src="/static/js/jquery.validate.min.js"></script>
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

<script type="text/javascript">
    function validate() {
        //If the form value is "" (nothing)
        if (document.getElementById("query").value == "") {
            return false; //Stop the form from submitting
        }
        return true;
    }


    $('#searchForm input').keydown(function(e) {
        if (e.keyCode == 13) {
            $('#searchForm').submit();
        }
    });

</script>

<#--<script>-->
<#--$(document).ready(function () {-->
<#--console.log(messageStrings.userNameExist);-->
<#--});-->
<#--</script>-->

