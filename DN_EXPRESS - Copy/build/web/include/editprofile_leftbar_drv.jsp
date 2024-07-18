<%-- 
    Document   : editprofile_leftbar_drv
    Created on : Jul 16, 2024, 7:20:57 PM
    Author     : dangq
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="form-container left-bar">
        <div class="avatar"> 
            <label for="image-upload">
                <img src="${info.getAccount().getAvatar()}" alt="avatar" class=" img-fluid">
            </label>
            <!--<input type="file" accept="image/*" id="image-upload" hidden >-->
        </div>
        <h4>${info.getAccount().getFirstName()} ${info.getAccount().getLastName()}</h4>
        <span>@${info.getAccount().getPhone_numb()}</span>
        <hr>
        <ul>
            <li>
                <form action="Svl_Profile_Driver" method="get">
                    <input class="link-button" type="submit" value="Edit Profile" />
                </form>
            </li>
            <!--<a href="Scr_Profile_CustomerProfile.jsp">Edit Profile</a></li>-->
            <li>
                <form action="Svl_EditProfile_ChangePass" method="get">
                    <input class="link-button" type="submit" value="Change Password" />
                </form>
            </li>
            <li>
                <form action="Driver_ViewDriverWallet_Servlet" method="get">
                    <input class="link-button" type="submit" value="Manage Wallet" />
                </form>
            </li>
        </ul>

    </div>
</html>
