<html>
<form class="cmxform" id="commentForm" method="get" action="">
    <fieldset>
        <legend>Please provide your name, email address (won't be published) and a comment</legend>
        <p>
            <label for="cname">Name (required, at least 2 characters)</label>
            <input id="cname" name="name" minlength="2" type="text" required>
        </p>
        <p>
            <label for="cemail">E-Mail (required)</label>
            <input id="cemail" type="email" name="email" required>
        </p>
        <p>
            <label for="curl">URL (optional)</label>
            <input id="curl" type="url" name="url">
        </p>
        <p>
            <label for="ccomment">Your comment (required)</label>
            <textarea id="ccomment" name="comment" required></textarea>
        </p>
        <p>
            <input class="submit" type="submit" value="Submit">
        </p>
    </fieldset>
</form>

<#include "footerjs.ftl">
<script src="//cdn.bootcss.com/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<script>

    $("#commentForm").validate({
        submitHandler: function(form) {
            form.submit();
        }
    });
    $("#commentForm").submit(function (e) {
        e.preventDefault();
        console.log("nimei");
    })
</script>

</html>