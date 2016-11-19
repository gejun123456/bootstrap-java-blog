<html>
<head>
<#include "head_header.ftl">
    <link href="/static/static/css/markdown.css" rel="stylesheet">
    <link href="/static/static/css/comment.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="col-sm-8 blog-main">
        <div id="blogdata">
        <#--adddata to this position-->
            <div class="blog-post">
                <h2 class="blog-post-title">${vo.title}</h2>
                <p class="blog-post-meta">${vo.addtime}</p>
            ${vo.content}
            </div>
        </div>
        <div>
        <#if comment??>

            <form id="commentform" class="form-horizontal " action="/comment/${vo.id}">
                <div class="form-group">
                    <label for="exampleInputEmail1"><@spring.message "Comment"/>:</label>
                    <input type="text" name="name" class="form-control" style="width:30%; margin-bottom: 10px;"
                           placeholder="<@spring.message "commentname"/>"
                           required="true"/>
                        <textarea name="content" class="form-control" rows="4" required="true"
                                  placeholder="<@spring.message "commentContent"/>"></textarea>

                    <button type="submit" class="btn btn-primary" style="margin-top: 10px;"><@spring.message "addCommentButton"/></button>
                </div>
            </form>

            <div id="firstcomment"></div>
            <#list commentVos as commentVo>
                <div class="row">
                    <div class="col-sm-1">
                        <div class="thumbnail">
                            <img class="img-responsive user-photo"
                                 src="http://www.cnbeta.com/assets/default/images/anonymous.gif">
                        </div><!-- /thumbnail -->
                    </div><!-- /col-sm-1 -->

                    <div class="col-sm-7">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <strong>${commentVo.name}</strong> <span
                                    class="text-muted">${commentVo.ago}</span>
                                <img src="/img/reply.png" class="reply " tabindex="${commentVo?index}"></img>
                            </div>
                            <div class="panel-body">
                            ${commentVo.comment}
                            </div><!-- /panel-body -->
                            <div>
                                <form class="reply-form" id="form_${commentVo?index}" style="display: none;"
                                      action="/reply">
                                    <input type='hidden' name='replyCommentId' value="${commentVo.id}">
                                    <input type='hidden' name='articleId' value="${vo.id}">
                                    <input type='text' class='form-control' style="width:30%;margin-bottom: 10px;"
                                           name='name'
                                           placeholder='<@spring.message "commentname"/>' required="true">
                                    <input type='text' class='form-control' name='content' placeholder='<@spring.message "commentContent"/>'
                                           required="true">
                                    <button type='submit' class="btn btn-primary " ><@spring.message "commentReply"/></button>
                                </form>
                            </div>
                        </div><!-- /panel panel-default -->
                    </div><!-- /col-sm-5 -->

                </div>

            <#--here is hidden form when press with reply will display-->

            </#list>
        </#if>
        </div>
    </div>

</div>
<#include "footer.ftl">
<script src="//cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".reply").click(function (e) {
            $(".reply").show();
            $(".reply-form").hide();
            var s = $(this).attr("tabindex");
            $(this).hide();
//            $("#form_"+s+" input[name=content]").val("nimei");
            $("#form_" + s).show();
        })
        //first get data from server to render the view.
        //check if exist. then get data from server.
//        function reply(e) {
//            console.log(e.val());
//            var replyId = e.val();
//            var articleId = $("#com").val();
////            在评论下面添加一个输入框
//            e.remove();
//            $(".reply-form").remove();
//            console.log("nimei");
//            $("#parent" + replyId).append("<form class='reply-form'> " +
//                    "<input type='hidden'  name='replyCommentId' value='" + replyId + "'>" +
//                    "<input type='hidden'  name='articleId' value='" + articleId + "'>" +
//                    "<input type='text' class='form-control' name='name' placeholder='name'>" +
//                    "<input type='text' class='form-control' name='content' placeholder='comment'>" +
//                    "<button type='submit'>save</button></form>");
//            $(".reply-form").submit(function (e) {
//                var form = $(this);
//                $.ajax({
//                    type: 'POST',
//                    url: '/reply',
//                    data: form.serialize(),
//
//                    success: function (response) {
//                        console.log(response);
//                    }
//                })
//            })
//        }

//        if ($("#com").length) {
//            var id = $("#com").val();
//            $.ajax({
//                type: 'POST',
//                url: '/getComment/' + id,
//                success: function (response) {
//                    if (response != 'error') {
//                        for (var i = 0; i < response.length; i++) {
//                            console.log(response[i]);
//                            var d = response[i];
//                            if (d['parentId'] <= 0) {
//                                $("#firstcomment").append("<div id=\"parent" + d["id"] + "\"><p> name:" + d['name'] + "   comment:" +
//                                        d['comment'] + "</div>" + "<div><button value=\"" + d["id"] + "\"class='reply'>reply</button>" +
//                                        "</div>"
//                                )
//                            } else {
//                                var parentid = "parent" + d['parentId'];
//                                $("#" + parentid).append("<div id=\"parent" + d["id"] + "\"><p> name:" + d['name'] + "    comment:" + d['comment'] + "</div>")
//                            }
//                        }
//                        $(".reply").click(function () {
//                            reply($(this));
//                        });
//                    } else {
//                        alert('nimabi buxing');
//                    }
//                }
//            });
//        }


    })
</script>
</body>
</html>