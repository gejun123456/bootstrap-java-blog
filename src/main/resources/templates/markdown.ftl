<div class="container">
    <div class="row">
        <div class="col-sm-5">
            <div class="form-group">
                <div>
                    <label for="tilte"><@spring.message "title"/>:</label>
                <#if title??>
                    <input type="text" class="form-control" id="source_title" value="${title}">
                <#else>
                    <input type="text" class="form-control" id="source_title">
                </#if>
                </div>
                <label for="source">markdown:</label>
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
                                        <button type="button" class="btn btn-primary"
                                                id="link_save"><@spring.message "save"/></button>
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
                                                <label for="tilte"><@spring.message "fileOrImageUrl"/>:</label>
                                                <input class="form-control" id="image_link" name="image_link"
                                                       type="text"/>
                                            </div>
                                            <div>
                                                <label for="tilte"><@spring.message "optionaltitile"/>:</label>
                                                <input class="form-control" name="image_title" id="image_title"
                                                       type="text">
                                            </div>

                                            <div>
                                                <label for="exampleInputFile"><@spring.message "imageinput"/>:</label>
                                                <input type="file" name="image_file" id="image_file"/>
                                            </div>

                                            <div>
                                                <label for="image_width"><@spring.message "optionaImagewidth"/>:</label>
                                                <input type="text" name="image_width" id="image_width"/>
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-primary"
                                                    id="image_save"><@spring.message "save"/></button>
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
                        <button class="btn-default btn-sm btn" type="button" title="More (Ctrl+M)" tabindex="10"><span
                                class="glyphicon ">M</span></button>
                    </div>
                </div>
            <#if source_content??>
                <textarea class="form-control" rows="20" id="source">${source_content}</textarea>
            <#else >
                <textarea class="form-control" rows="20" id="source"></textarea>
            </#if>
            </div>

            <button type="button" class="btn btn-default navbar-btn"
                    id="savebutton"><@spring.message "save"/></button>
        </div>

        <div class="col-sm-2">
        </div>

        <div class="col-sm-5">
            <label for="source"><@spring.message "output"/>:</label>
            <div id="output">

            </div>
        </div>
    </div>
</div>