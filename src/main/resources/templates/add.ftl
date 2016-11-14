<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Blog Template for Bootstrap</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
    <link href="/css/bootstrap-markdown.min.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
        <div class="col-sm-5">
            <div class="form-group">
                <div>
                    <label for="tilte">title:</label>
                    <input type="text" class="form-control" id="source_title">
                </div>
                <label for="source">source:</label>
                <div class="md-header btn-toolbar">
                    <div class="btn-group">
                        <button class="btn-default btn-sm btn" type="button" title="Bold (Ctrl+B)" tabindex="1"><span
                                class="glyphicon glyphicon-bold"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="Italic (Ctrl+I)" tabindex="2"><span
                                class="glyphicon glyphicon-italic"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="Heading (Ctrl+H)" tabindex="3"><span
                                class="glyphicon glyphicon-header"></span></button>
                    </div>
                    <div class="btn-group">
                        <button class="btn-default btn-sm btn" type="button" title="URL/Link (Ctrl+L)" tabindex="4">
                            <span class="glyphicon glyphicon-link"></span>
                        </button>
                    <#--link button modal-->
                        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                    </div>
                                    <div class="modal-body">
                                        <label for="tilte">url:</label>
                                        <input class="form-group" id="link_url" type="text"/>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-primary" id="link_save">Save</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <button class="btn-default btn-sm btn" type="button" title="Image (Ctrl+G)" tabindex="5"><span
                                class="glyphicon glyphicon-picture"></span></button>
                    <#--modal for image-->
                        <div class="modal fade" id="imageModal" tabindex="-1" role="dialog"
                             aria-labelledby="myModalLabel">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span></button>
                                    </div>
                                    <form id="form" enctype="multipart/form-data" role="form">
                                        <div class="modal-body">
                                            <div>
                                                <label for="tilte">upload file or img url:</label>
                                                <input class="form-control" id="image_link" name="image_link" type="text"/>
                                            </div>
                                            <div>
                                                <label for="tilte">title(optional):</label>
                                                <input class="form-control" name="image_title" id="image_title"
                                                       type="text" >
                                            </div>

                                            <div>
                                                <label for="exampleInputFile">image input:</label>
                                                <input type="file" name="image_file" id="image_file"/>
                                            </div>

                                            <div>
                                                <label for="image_width">image width(optional):</label>
                                                <input type="text" name="image_width" id="image_width"/>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary" id="image_save">Save</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="btn-group">
                        <button class="btn-default btn-sm btn" type="button" title="Unordered List (Ctrl+U)"
                                tabindex="6"><span class="glyphicon glyphicon-list"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="Ordered List (Ctrl+O)" tabindex="7">
                            <span class="glyphicon glyphicon-th-list"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="Code (Ctrl+K)" tabindex="8"><span
                                class="glyphicon glyphicon-asterisk"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="Quote (Ctrl+Q)" tabindex="9"><span
                                class="glyphicon glyphicon-comment"></span></button>
                        <button class="btn-default btn-sm btn" type="button" title="MORE" tabindex="10"><span
                                class="glyphicon ">M</span></button>
                    </div>
                </div>

                <textarea class="form-control" rows="20" id="source"></textarea>
            </div>
            <button type="button" class="btn btn-default navbar-btn" id="savebutton">保存</button>
        </div>

        <div class="col-sm-2">
        </div>

        <div class="col-sm-5">
            <label for="source">output:</label>
            <div id="output">

            </div>
        </div>
    </div>
</div>
</body>
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/showdown.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
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


    })
</script>
<body>
</body>

</html>