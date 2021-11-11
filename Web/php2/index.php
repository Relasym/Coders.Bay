<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Login</title>
</head>

<?php

$db = new mysqli("localhost", "thomas", "php2", "thomas");

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

session_start();

if (isset($_POST['login']) && !empty($_POST['uname']) && !empty($_POST['upassword'])) {
    $sql = "SELECT user_id, password FROM USERS WHERE name=?";
    $statement = $db->prepare($sql);
    $statement->bind_param('s', $_POST['uname']);
    $statement->execute();
    $result = $statement->get_result();
    $row = $result->fetch_assoc();
    if (!empty($row)) {
        $pw = $row['password'];
        if ($pw == $_POST['upassword']) {
            $_SESSION['user'] = $_POST['uname'];
            $_SESSION['user_id'] = $row['user_id'];
        } else {
            // echo "Wrong Password";
        }
        // echo "Entered Password: " . $_POST['upassword'];
        // echo "<br>";
        // echo "DB Password: " . $pw;
    } else {
        echo "User not found";
    }
}


if (isset($_SESSION['user'])) {
    header("Location: data.php");
}
?>

<body>
    <?php



    // $sql = "DROP TABLE CUSTOMERS";
    // $statement= $db->prepare($sql);
    // $statement->execute();

    // $sql = "DROP TABLE USERS";
    // $statement= $db->prepare($sql);
    // $statement->execute();



    // $sql = "CREATE TABLE USERS (user_id int AUTO_INCREMENT PRIMARY KEY, name varchar(30) NOT NULL, password varchar(30), address varchar(100))";
    // $statement= $db->prepare($sql);
    // $statement->execute();

    // $sql = "CREATE TABLE CUSTOMERS (customer_id int AUTO_INCREMENT PRIMARY KEY, name varchar(30) NOT NULL, contact varchar(30) NOT NULL, phone varchar(30), address varchar(100), owner int, created timestamp DEFAULT CURRENT_TIMESTAMP, edited timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, FOREIGN KEY (owner) REFERENCES USERS(user_id) ON DELETE CASCADE)";
    // $statement= $db->prepare($sql);
    // $statement->execute();


    // $sql = "INSERT INTO USERS (name) VALUES ('Testname')";
    // $statement= $db->prepare($sql);
    // $statement->execute();

    // $sql = "SELECT * FROM USERS";
    // $statement = $db->prepare($sql);
    // $statement->execute();
    // $result = $statement->get_result();

    // $sql = "INSERT INTO USERS (name,password,address) VALUES ('Tom Turbo', 'Tricks', 'Schönbrunn')";
    // $statement = $db->prepare($sql);
    // $statement->execute();
    // $result = $statement->get_result();

    // while ($row = $result->fetch_assoc()) {
    //     echo $row['name'] . "<br>";
    // }



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
                    <h3>Login</h3>
                    <form class="loginform" method="POST" action="index.php">
                        <p>
                            <label for="uname">Username:</label>
                            <input type="text" name="uname" id="uname">
                        </p>
                        <p>
                            <label for="upass">Password:</label>
                            <input type="password" name="upassword" id="upass">
                        </p>
                        <p>
                            <input type="submit" name="login" value="Login">
                            <button type="button" class="registerbutton">Register</button>
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
    <?php var_dump($_SESSION) ?>
</body>

</html>