<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Data</title>
</head>
<?php
session_start();
if (!isset($_SESSION['user'])) {
    header("Location: index.php");
}





?>

<body>

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
                    echo "<button class='databutton'>Data View</button>";
                } else {
                    echo "<h3>Please Login!</h3>";
                }
                ?>
                <!-- <button id=toggle>Toggle</button> -->


            </div>
            <div class="contentdiv" id=datacontentdiv>
                <div id="insertform">
                    <?php


                    if (
                        isset($_POST['add'])
                        && isset($_POST['customername'])
                        && isset($_POST['customercontact'])
                        && isset($_POST['customerphone'])
                        && isset($_POST['customeraddress'])
                    ) {
                        $pdo = new PDO("mysql:host=localhost;dbname=thomas", "thomas", "php2");
                        $statement = $pdo->prepare("INSERT INTO CUSTOMERS (name,contact,phone,address,owner) VALUES (?,?,?,?,?)");
                        $data = array($_POST['customername'], $_POST['customercontact'], $_POST['customerphone'], $_POST['customeraddress'], $_SESSION['user_id']);
                        if (!$statement->execute($data)) {
                            echo $statement->queryString;
                            echo "<br>";
                            echo $statement->errorInfo()[2];
                        } else {
                            echo "<h3>User Added!</h3>";
                        }
                    }







                    ?>


                    <h3>Insert new Customer</h3>
                    <form class="insertform" method="POST" action="insert.php">
                        <p>
                            <label for="customername">Name:</label>
                            <input type="text" name="customername" id="uname">
                        </p>
                        <p>
                            <label for="customercontact">Contact:</label>
                            <input type="text" name="customercontact" id="upass">
                        </p>
                        <p>
                            <label for="customerphone">Phone:</label>
                            <input type="text" name="customerphone" id="upass">
                        </p>
                        <p>
                            <label for="customeraddress">Address</label>
                            <input type="text" name="customeraddress" id="upass">
                        </p>
                        <p>
                            <input type="submit" name="add" value="Add to Database">
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