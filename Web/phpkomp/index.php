<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>

<body>
    <?php
    echo "<h3>Arrays</h3>";
    $people = array(
        array("Fritz", "Phantom", 70),
        array("Lilo", "Knickerbocker", 37),
        array("Thomas", "Debreziner", 58),
        array("Tom", "Turbo", 27)
    );
    echo "<table>";
    echo "<tr><th>Vorname</th><th>Nachname</th><th>Alter</th></tr>";
    foreach ($people as $person) {
        echo "<tr>";
        foreach ($person as $data) {
            echo "<td>" . $data . "</td>";
        }
        echo "</tr>";
    }
    echo "</table>";
    echo "<br>";

    $colors = array(
        "Fritz" => "Lila",
        "Lilo" => "Pink",
        "Thomas" => "Gelb",
        "Tom" => "Rot"
    );
    echo "<table>";
    echo "<tr><th>Vorname</th><th>Lieblingsfarbe</th></tr>";
    foreach ($colors as $person => $color) {
        echo "<tr>";
        echo "<td>" . $person . "</td>";
        echo "<td>" . $color . "</td>";
        echo "</tr>";
    }
    echo "</table>";
    echo "<br>";

    echo "<h3>Sorting</h3>";
    $tom_mag = array("Schmieröl", "DeBreziner", "Essiggurkerl");
    $tricks = array(6, 89, 23, 1, 7, 8, 19);

    printarray($tom_mag);
    asort($tom_mag);
    printarray($tom_mag);
    echo "<br>";
    printarray($tricks);
    arsort($tricks);
    printarray($tricks);
    echo "<br>";

    $colors = array("Fritz" => "Lila", "Lilo" => "Pink", "Thomas" => "Gelb", "Tom" => "Rot");
    printassociativearray($colors);
    ksort($colors);
    printassociativearray($colors);
    arsort($colors);
    printassociativearray($colors);
    echo "<br>";

    echo "<h3>Strings</h3>";
    $knickerbocker = "Die Knickerbockerbande, das sind wir!";
    echo "'" . $knickerbocker . "' hat Länge: " . strlen($knickerbocker);
    echo "<br>";
    $turbo = "Tom Turbo mag Schmieröl";
    $search = "Schmieröl";
    echo "'" . $search . "' beginnt bei Index " . strpos($turbo, $search) . " in '" . $turbo . "'";
    echo "<br>";
    $trick = "Turbo Durchblick";
    echo "'" . $trick . "' umgekehrt ist '" . strrev($trick) . "'";

    echo "<h3>Files</h3>";
    echo "*crickets*";
    $file = fopen("tricklist.txt", "w");
    fputs($file, "i. Turbo Kleber\n");
    fputs($file, "ii. Turbo Bratpfannentrick\n");
    fputs($file, "iii. Knoblauch Stinkbombe\n");
    fclose($file);
    

    function printarray($array)
    {
        foreach ($array as $arrayitem) {
            echo $arrayitem . "  ";
        }
        echo "<br>";
    }
    function printassociativearray($array)
    {
        foreach ($array as $arraykey => $arrayvalue) {
            echo $arraykey . " : " . $arrayvalue . "  ";
        }
        echo "<br>";
    }
    ?>

</body>

</html>