

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users Page</title>
        <link type="text/css" rel="stylesheet" href="styles.css">
    </head>
    <body>
        <div class="addBox">
            <h2>Add User</h2>
            <form action="user" method="POST">
                <input type="email" name="addEmail" placeholder="Email" value="${addEmail}">
                <br><br>
                <input type="radio" name="addActive" value="true">Active<br>
                <input type="radio" name="addActive" value="false">Not Active
                <br><br>
                <input type="text" name="addFirstName" placeholder="First Name" value="${addFirstName}">
                <br><br>
                <input type="text" name="addLastName" placeholder="Last Name" value="${addLastname}">
                <br><br>
                <input type="password" name="addPassword" placeholder="Password" value="${addPassword}">
                <br><br>
                <select name="addRole">
                    <option>Choose a role</option>
                     <option> system admin </option>
                        <option> regular user </option>
                        <option> company admin </option>
                        
                        <option ${addRole == "${role.roleName}"?"selected":""} value="${role.roleName}">${role.roleName}</option>
                    
                </select>
                <br><br>
                <input type="submit" value="Add User">
                <input type="hidden" name="action" value="add">
            </form>
        </div>
        <div class="manageBox">
            <h2>Manage Users</h2>
            <table>
                <tr>
                    <th>Email</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Role</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.email}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.role}</td>
                        <td><a href="user?action=edit&amp;email=${user.email}">Edit</a></td>
                        <td><a href="user?action=delete&amp;email=${user.email}">Delete</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="editBox">
            <h2>Edit Users</h2>
            <form action="user" method="POST">
                <input type="email" name="editEmail" placeholder="Email" value="${editEmail}">
                <br><br>
                <input type="radio" name="editActive" value="true">Active<br>
                <input type="radio" name="editActive" value="false">Not Active
                <br><br>
                <input type="text" name="editFirstName" placeholder="First Name" value="${editFirstName}">
                <br><br>
                <input type="text" name="editLastName" placeholder="Last Name" value="${editLastName}">
                <br><br>
                <select  name="EditRole">
                    <option>Choose a Role</option>
                    <c:forEach var="role" items="${roles}">
                        <option> system admin </option>
                        <option> regular user </option>
                        <option> company admin </option>
                        <option ${editRole == "${role.roleName}"?"selected":""} value="${role.roleName}">${role.roleName}</option>
                    </c:forEach>
                </select>
                <br><br>
                <input type="submit" value="Update User">
                <input type="hidden" name="action" value="edit">
            </form>
        </div>
    </body>
</html>
