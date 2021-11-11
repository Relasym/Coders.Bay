<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Register</title>
</head>
<?php
if (isset($_SESSION['user'])) {
    header("Location: data.php");
}
?>

<body>
    <?php
    $db = new mysqli("localhost", "thomas", "php2", "thomas");

    mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

    session_start();


    if (isset($_POST['register']) && !empty($_POST['uname']) && !empty($_POST['upassword1']) && !empty($_POST['upassword2']) && !empty($_POST['address'])) {
        if ($_POST['upassword1'] == $_POST['upassword2']) {
            $sql = "INSERT INTO USERS (name,password,address) VALUES (?,?,?)";
            $statement = $db->prepare($sql);
            $statement->bind_param('sss', $_POST['uname'], $_POST['upassword1'], $_POST['address']);
            $statement->execute();
        } else {
            echo "Passwords do not match";
        }
    }

    ?>

    <header>
        <h1>Customer management system</h1>

    </header>
    <main>
        <div class="maindiv">
            <div class="sidebar">
                <?php
                if (isset($_SESSION['user'])) {
                    echo "<h3>Current User: </h3>";
                    echo "<div>" . $_SESSION['user'] . "</div>";
                    echo "<button class='logoutbutton'>Logout</button>";
                } else {
                    echo "<h3>Please Login!</h3>";
                }
                ?>

            </div>
            <div class="contentdiv">
                <div id="loginform">
                    <h3>Register</h3>
                    <form class="loginform" method="POST" action="register.php">
                        <p>
                            <label for="uname">Username:</label>
                            <input type="text" name="uname" id="uname">
                        </p>
                        <p>
                            <label for="upass1">Password:</label>
                            <input type="password" name="upassword1" id="upass1">
                        </p>
                        <p>
                            <label for="upass2">Password Again:</label>
                            <input type="password" name="upassword2" id="upass1">
                        </p>
                        <p>
                            <label for="address">Address:</label>
                            <input type="text" name="address" id="address">
                        </p>
                        <p>
                            <input type="submit" name="register" value="Register">
                            <button type="button" class="loginbutton">Login</button>
                        </p>
                    </form>
                </div>
            </div>
        </div>


    </main>
    <footer>
        <div>customer management system 1.03 all rights reserved</div>
    </footer>
    <script src="index.js"></script>
</body>

</html>