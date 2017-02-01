<html>
<head>
<#--<#include "head_header.ftl">-->


<#include "header_admin.ftl">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300i,400" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/static/css/admin.css"/>

</head>
<body>
<div id="header">
    <div class="logo">
        admin panel
    </div>
    <span class="mypanel">
        <button><a href="/"><img src="/static/img/home.png"</a><span>home</span></button>
        <button><a href="/logout"> <img src="/static/img/logout.png"/><span>logout</span></a></button>
        <button><img src="/static/img/message-closed-envelope.png"/><span>message</span></button>
    </span>
</div>

<div id="sidebar">
    <ul>
        <li><a href="#dashboard" id="dashBoardLink"><img src="/static/img/dashboard.png"/><span>DashBoard</span></a>
        </li>
        <li><a href="#addContent" id="addContentLink"><img
                src="/static/img/add_content.png"/><span>addContent</span></a></li>
        <li><a href="#editContent" id="editContentLink"><img
                src="/static/img/edit-content.png"/><span>editContent</span></a></li>
        <li><a href="#tag" id="tagLink"><img src="/static/img/tag.png"/><span>tags</span></a></li>
        <li><a href="#deleted" id="deleteContentLink"><img src="/static/img/rubbish-bin.png"/><span>deleted</span></a>
        </li>
    </ul>
</div>

<div id="content">
    <div id="dashboardContent" class="rightContent collapse">
        <center>good to see you, bro</center>
    </div>
    <div id="addContent" class="rightContent collapse">
        <div id="addContentHeader">
            add content to blog
        </div>
        <div id="sourceContent">
            <form id="blogForm" action="#">
                <div class="form-group">
                    <label> Title</label>
                    <input type="text" id="sourceContentTitle" class="form-control" placeholder="blog tilte"
                           id="sourceContentId" name="title" required>
                </div>
                <div class="form-group">
                    <label> Blog Content</label>
                    <div>
                    <#--todo  maybe can create by javascript, instead of bind it to the place-->
                    <#include "markdown_btngroup.ftl">
                        <textarea class="form-control" id="sourceContentValue" placeholder="blogContent"
                                  name="value" required></textarea>
                    </div>
                </div>

                <div>
                    <button id="sourceContentButton" type="submit" class="btn btn-default">submit</button>
                </div>
            </form>
        </div>

    <#--<div class="form-group">-->
    <#--<label>markdownText</label>-->
    <#--</div>-->
        <div id="markdownContent" class="">
        <#--"But I must explain to you how all this mistaken idea of denouncing pleasure and praising pain was born and-->
            <#--I will give you a complete account of the system, and expound the actual teachings of the great explorer of-->
            <#--the truth, the master-builder of human happiness. No one rejects, dislikes, or avoids pleasure itself,-->
            <#--because it is pleasure, but because those who do not know how to pursue pleasure rationally encounter-->
            <#--consequences that are extremely painful. Nor again is there anyone who loves or pursues or desires to obtain-->
            <#--pain of itself, because it is pain, but because occasionally circumstances occur in which toil and pain can-->
            <#--procure him some great pleasure. To take a trivial example, which of us ever undertakes laborious physical-->
            <#--exercise, except to obtain some advantage from it? But who has any right to find fault with a man who-->
            <#--chooses to enjoy a pleasure that has no annoying consequences, or one who avoids a pain that produces no-->
            <#--resultant pleasure?"-->
        </div>
    </div>

    <div id="tagContent" class="rightContent collapse" style="margin-left: 260px;">
    <#--display in it-->



        <div id="tagSideBar">
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#tagModal">addTag
            </button>
            <div id="myAllTags">

            </div>
        </div>


        <div id="tagContentHeader">
            <center>here are my all tags</center>
        </div>


    </div>

    <div class="modal fade" id="editTagModal" tabindex="6" role="dialog" aria-labelledby="editTagModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="editTagModalLabel">Edit Tag</h4>
                </div>

                <div class="modal-body">
                <#--<form>-->
                    <div class="form-group">
                        <input type="hidden" id="editTagId">
                        <label for="tagName">Tag Name</label>
                        <input type="text" id="editTagName" class="form-control" placeholder="new tag name">
                    </div>
                <#--</form>-->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="saveEditTag">Save changes</button>
                </div>

            </div>
        </div>
    </div>

    <div class="modal fade" id="tagModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">Add New Tag</h4>
                </div>

                <div class="modal-body">
                <#--<form>-->
                    <div class="form-group">
                        <label for="tagName">Tag Name</label>
                        <input type="text" id="newTagName" class="form-control" placeholder="tag name">
                    </div>
                <#--</form>-->
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="saveTag">Save changes</button>
                </div>

            </div>
        </div>
    </div>


<div id="editContent" class="rightContent collapse">
    This are places you can edit content.
</div>


<div id="deleteContent" class="rightContent collapse">
    This are delete content.
</div>




<#include "markdown_modal.ftl">
</div>
</div>
</body>
<#include "footerjs.ftl">
<script src="//cdn.bootcss.com/showdown/1.5.0/showdown.min.js"></script>
<script src="//cdn.bootcss.com/autosize.js/3.0.18/autosize.min.js"></script>
<script src="/static/js/xss.js"></script>
<script src="/static/js/admin/markdown.js"></script>
<script src="/static/js/bootbox.min.js"></script>

<script type="text/javascript">

    $(document).ready(function () {


        var url = window.location.href;
        var number = url.indexOf('#');
        if (number == -1) {
            //do for it.
            $("#dashboardContent").show();
        }


        var hash = url.substring(number + 1);

        if (hash == "addContent") {
            $("#addContent").show();
        } else if (hash == "dashboard") {
            $("#dashboardContent").show();
        } else if (hash == "tag") {
            //request rest to get all the tag to view.
            loadTags();
            $("#tagContent").show();
        } else if (hash == "editContent") {
            $("#editContent").show();
        } else if (hash == "deleted") {
            $("#deleteContent").show();
        }
        $("#blogForm").validate();
        start($("#sourceContentTitle"), $("#sourceContentValue"), $("#markdownContent"));

        $("#blogForm").submit(function (e) {
            e.preventDefault();
            if (!$("#blogForm").valid()) {
                return;
            }
            var realSourceContent = $("#sourceContentValue").val().replace("<!-more->", "");
            var markDownHtml = filterXSS(converter.makeHtml(realSourceContent));
            var indexHtml = markDownHtml;
            var index = $("#sourceContentValue").val().indexOf("<!-more->");
            var sourceContent = $("#sourceContentValue").val();
            if (index != -1) {
                indexHtml = filterXSS(converter.makeHtml($("#sourceContentValue").val().substring(0, index)));
            }
            //todo need to validate the length of them.
            var data = {
                'title': filterXSS($("#sourceContentTitle").val()),
                'sourceContent': sourceContent,
                'sourceHtml': markDownHtml,
                'indexHtml': indexHtml
            };
            $.ajax({
                type: 'POST',
                data: JSON.stringify(data),
//                dataType: 'json',
                contentType: 'application/json;charset=utf-8',
                url: '/addContent',
                success: function (response) {
                    bootbox.alert(geti18n("success"));
                },
                error: function (response) {
                    console.log(response);
                    if (response.status == 403) {
                        window.location.href = "/loginPage";
                    } else if (response.status == 400) {
                        console.log(response);
                    }
                }
            })
        })

        $("#tagLink").click(function () {
            $(".rightContent").hide();
            loadTags();
            $("#tagContent").show();
        })

        $("#dashBoardLink").click(function () {
            $(".rightContent").hide();
            $("#dashboardContent").show();
        })

        $("#editContentLink").click(function () {
            $(".rightContent").hide();
            $("#editContent").show();
        })

        $("#addContentLink").click(function () {
            $(".rightContent").hide();
            $("#addContent").show();
        })

        $("#deleteContentLink").click(function () {
            $(".rightContent").hide();
            $("#deleteContent").show();
        })


        $("#saveTag").click(function () {
//            console.log($("#newTagName").val());
            //send vlaue through ajax
            $("#tagModal").modal('hide');
            var data = {
                name: $("#newTagName").val()
            }
            $.ajax({
                type: 'GET',
                data: data,
                url: '/tag/add',
                success: function (response) {
                    console.log(response);
                    loadTags();
                },
                error: function (response) {
                    console.log(response);
                }
            })
        })

        $("#saveEditTag").click(function (e) {
            console.log($("#editTagId").val());
            console.log($("#editTagName").val());
            var data = {
                tagId: $("#editTagId").val(),
                newTagName: $("#editTagName").val()
            }

            $("#editTagModal").modal('hide');
            $.ajax({
                type: 'POST',
                data: data,
                url: '/tag/edit',
                success: function (response) {
                    loadTags();
                },
                error: function (response) {
                    console.log(response);
                    alert("fail");

                }
            })
        })
    })

    function loadTags() {
        $("#tagContentHeader").html("hello you guys")
        $.ajax({
            type: 'Post',
            url: '/getTags',
            success: function (response) {
                var tagContent = "";
                for (var i in response) {
                    tagContent += "<div style='margin: 20px;background-color:#ecf0f1'>";
                    tagContent += "<span style='width: 500px; display: inline-block; font-size: 2.0em;'>" + response[i].id + response[i].tagName + "</span>"
                    tagContent += "<button type='button' class='btn btn-info' onclick='showEditTagModal(" + response[i].id + ")'>edit</button>"
                    tagContent += "<button type='button' class='btn btn-danger' onclick='deleteTag(" + response[i].id + ")'>delete</button>"
                    tagContent += "</div>"
                }
                $("#tagContentHeader").html(tagContent);

            },
            error: function (response) {
                console.log(response);
            }

        })

    }

    function showEditTagModal(tagId) {
//        console.log(e);
        $("#editTagId").val(tagId);
        $('#editTagModal').modal({
            keyboard: false
        })
    }


    function deleteTag(tagId) {
        bootbox.confirm("Do you really want to delete the tag!", function (result) {
            if (result == false) {
                return;
            }
            console.log(tagId);
            $.ajax({
                type: 'POST',
                url: '/tag/delete',
                data: {
                    tagId: tagId
                },
                success: function (response) {
                    loadTags();
                    console.log(response);
                },
                error: function (response) {
                    alert("fail");
                    console.log(response);
                }
            })
        })
    }

</script>
</html>
