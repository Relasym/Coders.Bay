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

$db = new mysqli("localhost", "thomas", "php2", "thomas");

mysqli_report(MYSQLI_REPORT_ERROR | MYSQLI_REPORT_STRICT);

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
                } else {
                    echo "<h3>Please Login!</h3>";
                }
                ?>
                <!-- <button id=toggle>Toggle</button> -->

                
            </div>
            <div class="contentdiv" id=datacontentdiv>
                <h2>Your Customers</h2>
                <?php
                $sql = "SELECT * FROM CUSTOMERS WHERE owner=?";
                $statement = $db->prepare($sql);
                $statement->bind_param('i', $_SESSION['user_id']);
                $statement->execute();
                $result = $statement->get_result();
                echo "<table border='0'>";
                echo "<tr>";
                echo "<th>Customer ID</th>";
                echo "<th>Customer Name</th>";
                echo "<th>Contact</th>";
                echo "<th>Phone</th>";
                echo "<th>Address</th>";
                echo "<th>Owner</th>";
                echo "<th>Created</th>";
                echo "<th>Last Edited</th>";
                echo "<th>Edit</th>";
                echo "<th>Delete</th>";
                echo "</tr>";
                $i=1;
                while ($row = $result->fetch_assoc()) {
                    echo "<tr>";
                    echo "<td>".$row['customer_id']."</td>";
                    echo "<td>".$row['name']."</td>";
                    echo "<td>".$row['contact']."</td>";
                    echo "<td>".$row['phone']."</td>";
                    echo "<td>".$row['address']."</td>";
                    echo "<td>".$row['owner']."</td>";
                    echo "<td>".$row['created']."</td>";
                    echo "<td>".$row['edited']."</td>";
                    echo "<td><form method='POST' action='edit.php'><button name='edit' value='".$row['customer_id']."'class='editbutton' id=editbutton".$i.">Edit</button></form></td>";
                    echo "<td><form method='POST' action='delete.php'><button name='delete' value='".$row['customer_id']."'class='deletebutton' id=deletebutton".$i.">Delete</button></form></td>";
                    echo "</tr>";
                    $i++;
                }
                echo "</table>";


        

                // echo "Test";
                // echo $_SESSION['user_id'];
                // var_dump($_SESSION);

                ?>
                <button id=newcustomerbutton>Add New Customer</button>

            </div>
        </div>


    </main>
    <footer>
        <div>customer management system 1.03 all rights reserved</div>
    </footer>
    <script src="index.js"></script>
</body>

</html>