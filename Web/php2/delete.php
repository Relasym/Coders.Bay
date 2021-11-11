<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <title>Logout</title>
</head>

<body>
    <?php
    session_start();
    var_dump($_POST);
    if(isset($_POST['delete'])) {
        var_dump($_POST);
        $pdo = new PDO("mysql:host=localhost;dbname=thomas", "thomas", "php2");
        $statement = $pdo->prepare("DELETE FROM CUSTOMERS WHERE customer_id=?");
        $statement->execute(array($_POST['delete']));
        header("Location: data.php");
    }
    ?>
</body>

</html>