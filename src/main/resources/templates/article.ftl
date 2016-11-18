<html>
<head>
<#include "head_header.ftl">
    <link href="/css/markdown.css" rel="stylesheet">
</head>
<body>
<#include "header.ftl">
<div class="container">
    <div class="row">
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
                <input type="hidden" id="com" value="${vo.id}"/>
                <form id="commentform" action="/comment/${vo.id}">
                    <div class="form-group">
                        <input type="text" name="name" class="form-control" style="width:200;" placeholder="name"
                               required="true"/>
                        <textarea name="content" class="form-control" rows="3" required="true"
                                  placeholder="please input comment"></textarea>
                    </div>
                    <button type="submit" class="btn btn-default">Send</button>
                </form>
                <div id="firstcomment"></div>
            </#if>
            </div>
        </div>

    </div>
</div>
<#include "footer.ftl">
<script src="/js/jquery-3.1.1.min.js"></script>
<script src="/js/jquery.validate.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        //first get data from server to render the view.
        //check if exist. then get data from server.
        if ($("#com").length) {
            var id = $("#com").val();
            $.ajax({
                type: 'POST',
                url: '/getComment/' + id,
                success: function (response) {
                    if (response != 'error') {
                        for (var i = 0; i < response.length; i++) {
                            console.log(response[i]);
                            var d = response[i];
                            if (d['parentId'] <= 0) {
                                $("#firstcomment").append("<div id=\"parent" + d["id"] + "\"><p> name:" + d['name'] + "   comment:" +
                                        d['comment'] + "</div>" + "<div><button value=\"" + d["id"] + "\"class='reply'>reply</button>" +
                                        "</div>"
                                )
                            } else {
                                var parentid = "parent" + d['parentId'];
                                $("#" + parentid).append("<div id=\"parent" + d["id"] + "\"><p> name:" + d['name'] + "    comment:" + d['comment'] + "</div>")
                            }
                        }
                        $(".reply").click(function (e) {
                            console.log("nimei");
                        });
                    } else {
                        alert('nimabi buxing');
                    }
                }
            });
        }


    })
</script>
</body>
</html>