<%-- 
    Document   : editprofile_leftbar_sta
    Created on : Jul 16, 2024, 7:25:09 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="form-container left-bar">
        <div class="avatar">

            <form action="Svl_EditProfile_UploadImg" method="post" enctype="multipart/form-data">
                <div class="upload">
                    <label for="image-upload">
                        <img id="pic" src="${info.getAccount().getAvatar()}" alt="avatar" class="img-fluid">
                    </label>
                    <input type="file" accept="image/*" id="image-upload" name="file" hidden onchange="showImage(event)">
                    <button type="submit" class="apply-button btn btn-primary fw-bold px-3 py-2" id="apply-button">Apply</button>
                </div>

            </form>
        </div>
        <h4>${info.getAccount().getFirstName()} ${info.getAccount().getLastName()}</h4>
        <span>@${info.getAccount().getPhone_numb()}</span>
        <hr>
        <ul>
            <li>
                <form action="Svl_Profile_Staff" method="get">
                    <input class="link-button" type="submit" value="Edit Profile" />
                </form>
            </li>
            <!--<a href="Scr_Profile_CustomerProfile.jsp">Edit Profile</a></li>-->
            <li>
                <form action="Svl_EditProfile_ChangePass" method="get">
                    <input class="link-button" type="submit" value="Change Password" />
                </form>
            </li>
        </ul>

    </div>
</html>
