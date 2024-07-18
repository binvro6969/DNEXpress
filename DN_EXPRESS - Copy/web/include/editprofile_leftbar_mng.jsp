<%-- 
    Document   : editprofile_leftbar_mng
    Created on : Jul 16, 2024, 7:26:27 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="form-container left-bar">
        <div class="avatar"> 
            <label for="image-upload">
                <img src="${info.getAvatar()}" alt="avatar" class=" img-fluid">
            </label>
            <!--<input type="file" accept="image/*" id="image-upload" hidden >-->
        </div>
        <h4>${info.getFirstName()} ${info.getLastName()}</h4>
        <span>@${info.getPhone_numb()}</span>
        <hr>
        <ul>
            <li>
                <form action="Svl_Profile_Manager" method="get">
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
