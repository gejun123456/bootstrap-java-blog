<html>
<head>
<#include "head_header.ftl">
    <link href="/css/bootstrap-markdown.min.css" rel="stylesheet">
    <link href="/css/markdown.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<#include "markdown.ftl">
</body>
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/showdown/1.5.0/showdown.min.js"></script>
<script src="//cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<#--<script src="/js/bootstrap.min.js"></script>-->
<script src="//cdn.bootcss.com/autosize.js/3.0.18/autosize.min.js"></script>
<script src="/js/mymarkdown.js"></script>

<script>
    $(document).ready(function () {

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


        $("#savebutton").click(function () {
            var title = $("#source_title").val();
            var text = $("#source").val();
            console.log(title)
            console.log(text);
//            send ajax request to save the content.
            $.ajax({
                url: "/addContent",
                data: {
                    title: title,
                    sourceContent: text
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
<body>
</body>

</html>