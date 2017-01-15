<html>
<head>
<#include "head_header.ftl">
    <link href="/static/css/bootstrap-markdown.min.css" rel="stylesheet">
    <link href="/static/css/markdown.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<input type="hidden" id="source_id" value="${source_id}">
<#include "markdown.ftl">
</body>
<#include "footerjs.ftl">
<#--<script src="/js/showdown.min.js"></script>-->
<script src="//cdn.bootcss.com/showdown/1.5.0/showdown.min.js"></script>
<script src="//cdn.bootcss.com/autosize.js/3.0.18/autosize.min.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<script src="/static/js/mymarkdown.js"></script>
<script>
    $(document).ready(function () {
        refresh();
        $("#markdownform").validate();
        $('#form').submit(function (e) {
            if ($("#image_file").get(0).files.length == 0) {
                //shall use with value of url
                dealWithImage($("#image_link").val());
                e.preventDefault();
                return;
            }
            var form = $(this);
            var formdata = false;
            if (window.FormData) {
                formdata = new FormData(form[0]);
            }
            var formAction = form.attr('action');
            $.ajax({
                type: 'POST',
                url: '/uploadImage',
                cache: false,
                data: formdata ? formdata : form.serialize(),
                contentType: false,
                processData: false,

                success: function (response) {
                    if (response != 'error') {
                        console.log(response);
                        dealWithImage(response);
                    } else {
                        alert('nimabi buxing');
                    }
                }
            });
            e.preventDefault();
        })

        $("#markdownform").submit(function (e) {
            e.preventDefault();
            var validate = $("#markdownform").valid();
            if (!validate) {
                return;
            }
            var title = $("#source_title").val();
            var text = $("#source").val();
            console.log(title)
            console.log(text);
//            send ajax request to save the content.
            $.ajax({
                url: "/editContent",
                data: {
                    title: title,
                    sourceContent: text,
                    id: $("#source_id").val()
                },
                type: "POST",
                dataType: "json",
            }).done(function (json) {
                console.log(json);
                window.location.href = "/";
            })
        })
    })
</script>


</html>