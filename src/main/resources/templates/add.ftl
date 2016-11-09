<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Blog Template for Bootstrap</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/bootstrap-markdown.min.css" rel="stylesheet">
</head>
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
                        <button class="btn-default btn-sm btn" type="button" title="URL/Link (Ctrl+L)" tabindex="4"><span class="glyphicon glyphicon-link"></span>
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

<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/showdown.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function () {
        var converter = new showdown.Converter();
        $("#source").keyup(function () {
            refresh()
        })

        function refresh() {
            var text = $("#source").val();
            var html = converter.makeHtml(text);
            html = "<h2>" + $("#source_title").val() + "</h2>" + html;
            $("#output").html(html)
        }

        $("#source_title").keyup(function () {
            refresh()
        })

        $("#source").keydown(function (e) {
            var keyCode = e.keyCode || e.which;
            if (keyCode == 9) {
                e.preventDefault();
                var start = $(this).get(0).selectionStart;
                var end = $(this).get(0).selectionEnd;

                // set textarea value to: text before caret + tab + text after caret
                $(this).val($(this).val().substring(0, start)
                        + "\t"
                        + $(this).val().substring(end));

                // put caret at right position again
                $(this).get(0).selectionStart =
                        $(this).get(0).selectionEnd = start + 1;
            }
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
                type: "GET",
                dataType: "json",
            }).done(function (json) {
                console.log(json);
                window.location.href = "/";
            })
        })

        function dealWithB(com, start, end) {
            if (start == end) {
                com.val(com.val().substring(0, start) + "****" + com.val().substring(end));
                com.prop("selectionStart", start + 2);
                com.prop("selectionEnd", start + 2);
            } else {
                com.val(com.val().substring(0, start) + "**" + com.val().substring(start, end) + "**" + com.val().substring(end));
                com.prop("selectionStart", start + 2);
                com.prop("selectionEnd", end + 2);
            }
        }

        function dealwithI(com, start, end) {
            if (start == end) {
                com.val(com.val().substring(0, start) + "**" + com.val().substring(end));
                com.prop("selectionStart", start + 1);
                com.prop("selectionEnd", start + 1);
            } else {
                com.val(com.val().substring(0, start) + "*" + com.val().substring(start, end) + "*" + com.val().substring(end));
                com.prop("selectionStart", start + 1);
                com.prop("selectionEnd", end + 1);
            }
        }

        function dealwithHead(com, start, end) {
            if (start == end) {
                com.val(com.val().substring(0, start) + "######" + com.val().substring(end));
                com.prop("selectionStart", start + 3);
                com.prop("selectionEnd", start + 3);
            } else {
                com.val(com.val().substring(0, start) + "###" + com.val().substring(start, end) + "###" + com.val().substring(end));
                com.prop("selectionStart", start + 3);
                com.prop("selectionEnd", end + 3);
            }
        }

        $("#link_url").keydown(function (e) {
            if (event.keyCode == 13) {
                dealWithLink();
            }
        })

        $("#link_save").click(function () {
            dealWithLink();
        })

        $('#myModal').on('hidden.bs.modal', function (e) {
            var com = $('#source');
            com.focus();
        })


        $('#myModal').on('hide.bs.modal', function (e) {
            var com = $('#source');
            com.focus();
        })

        $('#myModal').on('shown.bs.modal', function (e) {
            $("#link_url").val("http://");
            $("#link_url").focus();
            $("#link_url").prop("selectionStart",8);
        })


        function dealWithLink() {
            var url = $("#link_url").val();
            $("#myModal").modal('hide');
            var com = $('#source');
            var start = com.prop("selectionStart");
            var end = com.prop("selectionEnd");
            var text = '[' + url + ']' + '(' + url + ')';
            var c = text.length;
            com.val(com.val().substring(0, end) + text + com.val().substring(end));
            com.prop("selectionStart", end);
            com.prop("selectionEnd", end + c);
            refresh();
        }

        $(".btn-default.btn-sm.btn").click(function () {
            var message = $(this).attr('tabindex');
            var com = $('#source');
            var start = com.prop("selectionStart");
            var end = com.prop("selectionEnd");
            if (message == 1) {
                //the rest is much the same.
                dealWithB(com, start, end);
            } else if (message == 2) {
                dealwithI(com, start, end);
            } else if (message == 3) {
                dealwithHead(com, start, end);
            } else if (message == 4) {
                //the link //need to output the modal.
//                dealWithLink(com,start,end);
                $('#myModal').modal({
                    keyboard: true
                })
                return;
            }
            com.focus();
            refresh();
        })
    })
</script>
<body>
</body>

</html>