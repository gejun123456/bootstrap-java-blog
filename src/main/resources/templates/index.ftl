<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Blog Template for Bootstrap</title>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/blog.css" rel="stylesheet">
</head>
<body>
    <div class="blog-masthead">
        <div class="container">
            <nav class="blog-nav">
                <a class="blog-nav-item active" href="#">Home</a>
                <a class="blog-nav-item" href="#">New features</a>
                <a class="blog-nav-item" href="#">Press</a>
                <a class="blog-nav-item" href="#">about</a>
            </nav>
        </div>
    </div>

    <div class="container">
        <div class="blog-header">
            <h1 class="blog-title">The Bruce ge Blog</h1>
        </div>

        <div class="row">
            <div class="col-sm-8 blog-main">
                <nav>
                    <ul class="pager">
                        <li><a href="#">Previous</a> </li>
                        <li><a href="#">Next</a> </li>
                    </ul>
                </nav>
            </div>

            <div class="col-sm-3 col-sm-offset-1 blog-sidebar">
                <div class="sidebar-module sidebar-module-inset">
                    <h4>about</h4>
                    <p>I'm bruce ge.</p>
                </div>
                <div class="sidebar-module">
                    <h4>Archives</h4>
                    <ol class="list-unstyled">
                        <li><a href="#">March 2014</a> </li>
                    </ol>
                </div>
            </div>
        </div>
    </div>


    <script src="/js/jquery-3.1.1.min.js"></script>

<#--shall add with navigationBar which is from the request size.-->
</li>
<script>
    $(document).ready(function () {
        var pagesize=5;
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