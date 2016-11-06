<html>
<title> this is my blog</title>
<body>
<h1> welcome to myblog</h1>
<script src="/js/jquery-3.1.1.min.js"></script>
<li id="a1"></li>
<li id="a2"></li>
<li id="a3"></li>
<li id="a4"></li>

<#--shall add with navigationBar which is from the request size.-->
</li>
<script>
    $(document).ready(function () {
        var pagesize=10;
        //get from server.
        $.ajax({
            url:"/getPage",
            data:{
                page:1,
                pagesize:pagesize
            },
            type:"GET",
            dataType:"json",
        }).done(function (json) {
            console.log(json.length);
            $("#a1").html(json[0].title)
        }).fail(function (xhr, status, errorThrown) {
            alert("sorry, there was a probelm")
        });
    })
</script>
</body>
</html>