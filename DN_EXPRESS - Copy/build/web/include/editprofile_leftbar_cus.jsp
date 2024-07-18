<%-- 
    Document   : editprofile_leftbar_cus
    Created on : Jul 16, 2024, 7:23:51 PM
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
                        <img id="pic" src="${info.getAvatar()}" alt="avatar" class="img-fluid">
                    </label>
                    <input type="file" accept="image/*" id="image-upload" name="file" hidden onchange="showImage(event)">
                    <button type="submit" class="apply-button btn btn-primary fw-bold px-3 py-2" id="apply-button">Apply</button>
                </div>

            </form>
        </div>
        <h4>${info.getFirstName()} ${info.getLastName()}</h4>
        <c:choose>
            <c:when test="${empty info.phone_numb}">
                <span>@username</span>
            </c:when>
            <c:otherwise>
                <span>@${info.phone_numb}</span>
            </c:otherwise>
        </c:choose>

        <hr>
        <ul>
            <li>
                <form action="Svl_Profile_Customer" method="get">
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
