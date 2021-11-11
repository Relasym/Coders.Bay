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

                    $pdo = new PDO("mysql:host=localhost;dbname=thomas", "thomas", "php2");

                    if (isset($_POST['edit'])) {
                        $statement = $pdo->prepare("SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID=?");
                        if ($statement->execute(array($_POST['edit']))) {
                            $data = $statement->fetch();
                            // var_dump($data);
                        }
                    } else {
                        var_dump($_POST);

                        if (
                            isset($_POST['update'])
                            && isset($_POST['customername'])
                            && isset($_POST['customercontact'])
                            && isset($_POST['customerphone'])
                            && isset($_POST['customeraddress'])
                        ) {
                            var_dump($_POST);
                            $statement = $pdo->prepare("UPDATE CUSTOMERS SET
                            name=?,
                            contact=?,
                            phone=?,
                            address=?
                            WHERE customer_id=?");
                            $updatedata = array($_POST['customername'], $_POST['customercontact'], $_POST['customerphone'], $_POST['customeraddress'], $_POST['customerid']);
                            if ($statement->execute($updatedata)) {
                                $data = $statement->fetch();
                                header("Location: data.php");
                                // var_dump($data);
                            } else {
                                echo $statement->queryString;
                                echo "<br>";
                                echo $statement->errorInfo()[2];
                            }
                        } else {
                            header("Location: data.php");
                        }
                    }

                    ?>

                    <h3>Edit existing Customer</h3>
                    <form class="insertform" method="POST" action="edit.php">
                        <p>
                            <label for="customername">ID:</label>
                            <label><?php echo $data['customer_id']; ?></label>
                            <input type="hidden" name=customerid value='<?php echo $data['customer_id']; ?>'>
                        </p>
                        <p>
                            <label for="customername">Name:</label>
                            <input type="text" name="customername" Value='<?php echo $data['name']; ?>' id="uname">
                        </p>
                        <p>
                            <label for="customercontact">Contact:</label>
                            <input type="text" name="customercontact" Value='<?php echo $data['contact']; ?>' id="upass">
                        </p>
                        <p>
                            <label for="customerphone">Phone:</label>
                            <input type="text" name="customerphone" Value='<?php echo $data['phone']; ?>' id="upass">
                        </p>
                        <p>
                            <label for="customeraddress">Address</label>
                            <input type="text" name="customeraddress" Value='<?php echo $data['address']; ?>' id="upass">
                        </p>
                        <p>
                            <input type="submit" name="update" value="Update">
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