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
                        <label for="tilte"><@spring.message "fileOrImageUrl"/></label>
                        <input class="form-control" id="image_link" name="image_link"
                               type="text"/>
                    </div>
                    <div>
                        <label for="tilte"><@spring.message "optionaltitile"/></label>
                        <input class="form-control" name="image_title" id="image_title"
                               type="text">
                    </div>

                    <div>
                        <label for="exampleInputFile"><@spring.message "imageinput"/></label>
                        <input type="file" class="form-control" name="image_file" id="image_file"/>
                    </div>

                    <div>
                        <label for="image_width"><@spring.message "optionaImagewidth"/></label>
                        <input type="text" class="form-control" name="image_width" id="image_width"/>
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